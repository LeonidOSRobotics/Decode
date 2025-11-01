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


    public VisionSubsystem(RobotHardware hardware) {
        this.hardware = hardware;
        hardware.getCamera().pipelineSwitch(0);
        hardware.getCamera().setPollRateHz(100);
        hardware.getCamera().start();
        result = hardware.getCamera().getLatestResult();
    }

    public int getTagID() {
        result = hardware.getCamera().getLatestResult();
        if(result.getFiducialResults().isEmpty()){
            return -1;
        }
        return result.getFiducialResults().get(0).getFiducialId(); //desired.getFiducialId();
    }



}
