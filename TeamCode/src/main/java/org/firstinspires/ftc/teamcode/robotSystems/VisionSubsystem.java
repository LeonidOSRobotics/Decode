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

    RobotHardware hardware;
    LLResult result;
    private static final double M_TO_IN = 39.3701;
    private static final double DESIRED_DISTANCE = 50.0;


    public VisionSubsystem(RobotHardware hardware) {
        this.hardware = hardware;
        hardware.getCamera().pipelineSwitch(0);
        hardware.getCamera().setPollRateHz(100);
        hardware.getCamera().start();
        getLatestTag();
    }

    private void getLatestTag(){
        result = hardware.getCamera().getLatestResult();
    }

    public int getTagID() {
        getLatestTag();
        if(result.getFiducialResults().isEmpty()){
            return -1;
        }
        return result.getFiducialResults().get(0).getFiducialId(); //desired.getFiducialId();
    }

    /**
     * Uses the camera to calculate and return the position (mm and degrees)
     * of the robot relative to the Apriltag in view.
     * @Precondition: An AprilTag is visible
     * @return The alignment of the robot {distance, strafe, heading}
     */
    public double[] getAlignmentError(){

        getLatestTag();
        LLResultTypes.FiducialResult tag = result.getFiducialResults().get(0);
        double zMeters   = tag.getCameraPoseTargetSpace().getPosition().z;
        double xMeters   = tag.getCameraPoseTargetSpace().getPosition().x;
        double rangeIn   = zMeters * M_TO_IN;
        double lateralIn = xMeters * M_TO_IN;
        double rangeError   = rangeIn - DESIRED_DISTANCE;
        double headingError = tag.getTargetXDegrees();
        double[] alignmentError = {rangeError, lateralIn, headingError};
        return alignmentError;

    }



}
