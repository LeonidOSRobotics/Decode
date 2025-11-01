package org.firstinspires.ftc.teamcode.robotSystems;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;

import java.util.Comparator;
import java.util.List;


import com.qualcomm.robotcore.util.Range;

/*
 * This class subsystem is where the robot is told how to perform vision
 * related functions. It uses the robotHardware class to access the actual
 * hardware of the robot.
 */

public class VisionSubsystem {
    LLResult result;
    LLResultTypes.FiducialResult desired;

    RobotHardware hardware;
    private static final double MAX_AUTO_SPEED = 0.5;
    private static final double MAX_AUTO_STRAFE = 0.5;
    private static final double MAX_AUTO_TURN = 0.3;
    private static final double SPEED_GAIN = 0.02;
    private static final double STRAFE_GAIN = 0.1;
    private static final double TURN_GAIN = 0.05;

    private static final int DESIRED_TAG_ID = 20;

    public VisionSubsystem(RobotHardware hardware) {
        this.hardware = hardware;
        hardware.getCamera().pipelineSwitch(0);
        hardware.getCamera().setPollRateHz(100);
        hardware.getCamera().start();
        result = hardware.getCamera().getLatestResult();
        desired = selectDesiredTag(result.getFiducialResults());
    }

    public double calculateAutoDrive(double rangeError) {
        return Range.clip(rangeError * SPEED_GAIN, -MAX_AUTO_SPEED, MAX_AUTO_SPEED);
    }

    public double calculateAutoTurn(double headingError) {
        return Range.clip(headingError * -TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN);
    }

    public double calculateAutoStrafe(double lateralIn) {
        return Range.clip(lateralIn * STRAFE_GAIN, -MAX_AUTO_STRAFE, MAX_AUTO_STRAFE);
    }

    public LLResultTypes.FiducialResult selectDesiredTag(List<LLResultTypes.FiducialResult>list){
        if (list == null || list.isEmpty()) return null;
        if (DESIRED_TAG_ID >= 0){
            return list.stream()
                    .filter(fr -> fr.getFiducialId() == DESIRED_TAG_ID)
                    .min(Comparator.comparingDouble(fr -> Math.abs(fr.getCameraPoseTargetSpace().getPosition().z)))
                    .orElse(null);
        }
        return list.stream()
                .min(Comparator.comparingDouble(fr -> Math.abs(fr.getCameraPoseTargetSpace().getPosition().z)))
                .orElse(null);

    }
    public int getTagID() {
        result = hardware.getCamera().getLatestResult();
        if(result.getFiducialResults().isEmpty()){

            return 0;
        }
        //desired = selectDesiredTag(result.getFiducialResults());

        return result.getFiducialResults().get(0).getFiducialId(); //desired.getFiducialId();
    }



}
