package org.firstinspires.ftc.teamcode.robotSystems;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

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
    HardwareMap hwMap = null; //TODO Can we get rid of this variable, is it used outside the the constructor?
    private Limelight3A camera = null;
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightBack = null;


    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        leftBack = hwMap.get(DcMotor.class, "leftBack");
        leftFront= hwMap.get(DcMotor.class, "leftFront");
        rightFront = hwMap.get(DcMotor.class, "rightFront");
        rightBack = hwMap.get(DcMotor.class, "rightBack");

        camera = hwMap.get(Limelight3A.class, "limelight");

        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

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
}
