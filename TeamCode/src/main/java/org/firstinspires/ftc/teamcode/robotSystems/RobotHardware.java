package org.firstinspires.ftc.teamcode.robotSystems;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.IMU;

/*
 * This class is where we identify the physical components of the robot
 * Think of this as your "wiring diagram in code".
 * It knows:
 *      What components exist (motors, servos, sensors)
 *      What their names are in the configuration
 *      How to initialize them safely
 *
 * But it doesn’t know what to do with them.
 *
 */

public class RobotHardware {
    IMU imu = null;
    HardwareMap hwMap = null; //TODO Can we get rid of this variable, is it used outside the the constructor?
    private Limelight3A camera = null;
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightBack = null;
    private RevBlinkinLedDriver blinkin = null;

    public static final double M_TO_IN = 39.3701;
    public static final double DESIRED_DISTANCE_IN = 50.0;
    public static final int DESIRED_TAG_ID = 20;



    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        imu = hwMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
        imu.initialize(parameters);
        leftBack = hwMap.get(DcMotor.class, "leftBack");
        leftFront= hwMap.get(DcMotor.class, "leftFront");
        rightFront = hwMap.get(DcMotor.class, "rightFront");
        rightBack = hwMap.get(DcMotor.class, "rightBack");

        camera = hwMap.get(Limelight3A.class, "limelight");
        blinkin = hwMap.get(RevBlinkinLedDriver.class, "blinkin");

        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        imu.resetYaw();
    }

    public void startCamera(){
        if (camera != null) {
            camera.pipelineSwitch(0);
            camera.start();
        }
    }

    public void stopCamera(){
        if (camera != null) {
            camera.stop();
        }
    }

    //Getters for DriveSubsystem motors
    public DcMotor getLeftFront() {
        return leftFront;
    }

    public DcMotor getRightFront() {
        return rightFront;
    }

    public DcMotor getLeftBack() {
        return leftBack;
    }

    public DcMotor getRightBack() {
        return rightBack;
    }

    //Getters for VisionSubsystem
    public Limelight3A getCamera() {
        return camera;
    }

    public RevBlinkinLedDriver getBlinkin() {
        return blinkin;
    }

    public IMU getImu() {
        return imu;
    }
}
