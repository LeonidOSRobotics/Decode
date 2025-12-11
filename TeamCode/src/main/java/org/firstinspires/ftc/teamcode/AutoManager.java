package org.firstinspires.ftc.teamcode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robotSystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.robotSystems.ImuSubsystem;
import org.firstinspires.ftc.teamcode.robotSystems.RobotHardware;
import org.firstinspires.ftc.teamcode.robotSystems.VisionSubsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Thread.sleep;


public class AutoManager {
    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_GOBILDA = 537.7;  // GoBilda Motor Encoder
    static final double WHEEL_DIAMETER_CM = 10.4;     // GoBilda Mecanum Wheels
    static final double DRIVE_GEAR_REDUCTION = 1.0;    // No External Gearing.

    static final double COUNTS_PER_CM = (COUNTS_PER_MOTOR_GOBILDA * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_CM * 3.1415);

    private DriveSubsystem driveTrain;
    private VisionSubsystem vision;
    private ImuSubsystem imu;
    RobotHardware hardware;


    public AutoManager(DriveSubsystem driveTrain, RobotHardware hardware, VisionSubsystem vision, ImuSubsystem imu) {
        this.driveTrain = driveTrain;
        this.hardware = hardware;
        this.vision = vision;
        this.imu = imu;
    }

    public void driveCm(double speed, double leftCm, double rightCm, boolean isForward,double timeoutS) {
        resetEncoders();
        int newLeftTarget;
        int newRightTarget;

        // Determine new target position, and pass to motor controller
        newLeftTarget = hardware.getLeftFront().getCurrentPosition() + (int) (leftCm * COUNTS_PER_CM);
        newRightTarget = hardware.getRightFront().getCurrentPosition() + (int) (rightCm * COUNTS_PER_CM);
        hardware.getLeftFront().setTargetPosition(newLeftTarget);
        hardware.getRightFront().setTargetPosition(newRightTarget);

        // Turn On RUN_TO_POSITION
        hardware.getLeftFront().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.getRightFront().setMode(DcMotor.RunMode.RUN_TO_POSITION);


        // reset the timeout time and start motion.
        runtime.reset();

        double power = isForward ? Math.abs(speed) : -Math.abs(speed);
         if (isForward){
             power = Math.abs(speed);
             hardware.getLeftFront().setPower(power);
             hardware.getRightFront().setPower(power);
        }else{
             hardware.getRightBack().setPower(power);
             hardware.getLeftBack().setPower(power);

        }


        // keep looping while we are still active, and there is time left, and both motors are running.
        while (runtime.seconds() < timeoutS &&
                hardware.getLeftFront().isBusy() && hardware.getLeftFront().isBusy()) {

        }


        // Stop all motion;
        driveTrain.stopDriveTrain();

        // Turn off RUN_TO_POSITION
        hardware.getLeftFront().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hardware.getRightFront().setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //sleep(250);   // optional pause after each move.

    }

    public void strafeToPosition(double cm, double speed, double timeoutS, Telemetry telemetry) {


        int newLeftFrontTarget = (int) (hardware.getLeftFront().getCurrentPosition() + (cm  * COUNTS_PER_CM));
        int newRightFrontTarget = (int) (hardware.getRightFront().getCurrentPosition() - (cm *COUNTS_PER_CM));
        int newLeftBackTarget = (int) (hardware.getLeftBack().getCurrentPosition() - (cm * COUNTS_PER_CM));
        int newRightBackTarget = (int) (hardware.getRightBack().getCurrentPosition() + (cm * COUNTS_PER_CM));

        hardware.getLeftFront().setTargetPosition(newLeftFrontTarget);
        hardware.getLeftBack().setTargetPosition(newRightFrontTarget);
        hardware.getRightFront().setTargetPosition(newLeftBackTarget);
        hardware.getRightBack().setTargetPosition(newRightBackTarget);

        hardware.getLeftFront().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.getLeftBack().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.getRightFront().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.getRightBack().setMode(DcMotor.RunMode.RUN_TO_POSITION);


        runtime.reset();
        hardware.getLeftFront().setPower(Math.abs(speed));
        hardware.getLeftBack().setPower(Math.abs(speed));
        hardware.getRightFront().setPower(Math.abs(speed));
        hardware.getRightBack().setPower(Math.abs(speed));

        while ((runtime.seconds() < timeoutS && hardware.getLeftFront().isBusy() && hardware.getRightFront().isBusy()
                && hardware.getLeftBack().isBusy() && hardware.getRightBack().isBusy())) {

            telemetry.addData("Value:", newLeftBackTarget);
            telemetry.update();

        }
        driveTrain.stopDriveTrain();
        hardware.getLeftFront().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hardware.getRightFront().setMode(DcMotor.RunMode.RUN_USING_ENCODER);



    }

    public void resetEncoders () {
        hardware.getRightFront().setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hardware.getLeftFront().setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hardware.getRightBack().setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hardware.getLeftBack().setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hardware.getRightFront().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardware.getLeftFront().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        hardware.getRightFront().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hardware.getLeftFront().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    //Needs Testing
    /**
     * When a tag is visible this method should have the robot
     * line up with the target automatically.
     */
    public void driveToTag() {
        while (vision.getAlignmentError()[1] > 0.5 & vision.getAlignmentError()[2] > 0.5) {
            driveTrain.autoAlignment();
        }
    }

    public void turnDegrees(int degrees){
        imu.resetYaw();
        double targetRadians = degrees * (Math.PI / 180);
        double currentRadian = imu.getBotHeading();
        while(targetRadians != currentRadian){
            driveTrain.drive(0, 0, (currentRadian - targetRadians)*.05);
            currentRadian = imu.getBotHeading();
        }
        driveTrain.stopDriveTrain();
    }
    public double anglewrap(double radians){
        while (radians > Math.PI) {
            radians -= 2 * Math.PI;
        }
        while (radians < -Math.PI) {
            radians += 2 * Math.PI;
        }
        return radians;
    }
}

