package org.firstinspires.ftc.teamcode;

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









