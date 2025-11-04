package org.firstinspires.ftc.teamcode.robotSystems;

/*
 * This class subsystem is where the robot is told how to perform drive
 * related functions. It uses the robotHardware class to access the actual
 * hardware of the robot.
 */


import com.qualcomm.robotcore.util.Range;

public class DriveSubsystem {

    RobotHardware hardware;
    VisionSubsystem vision;
    ImuSubsystem imu;


    //Variables for Autoalignment
    private static final double MAX_AUTO_SPEED = 0.5;
    private static final double MAX_AUTO_STRAFE = 0.5;
    private static final double MAX_AUTO_TURN = 0.3;
    private static final double SPEED_GAIN = 0.02;
    private static final double STRAFE_GAIN = 0.1;
    private static final double TURN_GAIN = 0.05;

    public DriveSubsystem(RobotHardware hardware, VisionSubsystem vision, ImuSubsystem imu) {
        this.hardware = hardware;
        this.vision = vision;
        this.imu = imu;
    }

    public void stopDriveTrain() {
        hardware.getLeftBack().setPower(0);
        hardware.getRightBack().setPower(0);
        hardware.getLeftFront().setPower(0);
        hardware.getRightFront().setPower(0);

    }
    public void drive(double forward, double strafe, double rotate, boolean stickPressed) {
        double y = forward;
        double x = strafe;
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

    public void fieldOrientedDrive(double forward, double strafe, double rotate) {
        double rotStrafe = strafe * Math.cos(imu.getBotHeading()) - forward * Math.sin(-imu.getBotHeading());
        double rotForward = strafe * Math.sin(-imu.getBotHeading()) + forward * Math.cos(imu.getBotHeading());
        drive(rotForward, rotStrafe, rotate);

    }
    public void drive(double forward, double strafe, double rotate) {
        drive(forward, strafe, rotate, false);
    }

    //Helper Functions for Autoalignment
    private double calculateAutoDrive(double rangeError) {
        return Range.clip(rangeError * SPEED_GAIN, -MAX_AUTO_SPEED, MAX_AUTO_SPEED);
    }

    private double calculateAutoTurn(double headingError) {
        return Range.clip(headingError * -TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN);
    }

    private double calculateAutoStrafe(double lateralError) {
        return Range.clip(lateralError * STRAFE_GAIN, -MAX_AUTO_STRAFE, MAX_AUTO_STRAFE);
    }

    /**
     * When a tag is visible causes the robot to line up with the
     * tag in regards to the robots heading and strafe
     */
    public void autoAlignment(){
        double[] alignmentError = vision.getAlignmentError();
        double turn = Range.clip(alignmentError[2] * -TURN_GAIN,   -MAX_AUTO_TURN,   MAX_AUTO_TURN);
        double strafe = Range.clip(alignmentError[1] * STRAFE_GAIN, -MAX_AUTO_STRAFE, MAX_AUTO_STRAFE);
        drive(0, strafe, turn);

        
    }




}









