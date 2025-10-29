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
    public void drive(double forward, double strafe, double rotateLeft, double rotateRight, boolean stickPressed) {
        double y = forward;
        double x = strafe;
        double rotate = rotateRight - rotateLeft;
        //Slows speed of wheels
        double dampening;
        if(stickPressed) {
            dampening = .8;
        }else{
            dampening = .6;
        }

        //Calculating the power for the wheels
        double frontLeftPower = (y + x + rotate) * dampening;
        double backLeftPower = (y - x + rotate) * dampening;
        double frontRightPower = (y - x - rotate) * dampening;
        double backRightPower = (y + x - rotate) * dampening;

        //Set Power
        hardware.getLeftBack().setPower(backLeftPower);
        hardware.getLeftFront().setPower(frontLeftPower);
        hardware.getRightBack().setPower(backRightPower);
        hardware.getRightFront().setPower(frontRightPower);
    }
    public void drive(double forward, double strafe, double rotate) {
        double y = forward;
        double x = strafe;

        //Calculating the power for the wheels
        double frontLeftPower = (y + x + rotate);
        double backLeftPower = (y - x + rotate);
        double frontRightPower = (y - x - rotate);
        double backRightPower = (y + x - rotate);

        //Set Power
        hardware.getLeftBack().setPower(backLeftPower);
        hardware.getLeftFront().setPower(frontLeftPower);
        hardware.getRightBack().setPower(backRightPower);
        hardware.getRightFront().setPower(frontRightPower);
    }

}









