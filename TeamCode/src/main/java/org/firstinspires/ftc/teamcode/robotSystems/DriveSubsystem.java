package org.firstinspires.ftc.teamcode.robotSystems;

/*
 * This class subsystem is where the robot is told how to perform drive
 * related functions. It uses the robotHardware class to access the actual
 * hardware of the robot.
 */


public class DriveSubsystem {

    RobotHardware hardware;
    VisionSubsystem vision;

    public DriveSubsystem(RobotHardware hardware, VisionSubsystem vision) {
        this.hardware = hardware;
        this.vision = vision;
    }
    public void stopDriveTrain() {
        hardware.getLeftBack().setPower(0);
        hardware.getRightBack().setPower(0);
        hardware.getLeftFront().setPower(0);
        hardware.getRightFront().setPower(0);
    }
}









