package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robotSystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.robotSystems.RobotHardware;
import org.firstinspires.ftc.teamcode.robotSystems.VisionSubsystem;

public class AutoManager {

    static final double COUNTS_PER_MOTOR_GOBILDA    = 537.7 ;  // GoBilda Motor Encoder
    static final int WHEEL_DIAMETER_MM              = 104;     // GoBilda Mecanum Wheels
    static final double DRIVE_GEAR_REDUCTION        = 1.0 ;    // No External Gearing.
    private ElapsedTime runtime = new ElapsedTime();
    static final double COUNTS_PER_CM             = ((COUNTS_PER_MOTOR_GOBILDA * DRIVE_GEAR_REDUCTION)/
            (WHEEL_DIAMETER_MM * 3.1415)) / 10;

    private DriveSubsystem driveTrain;
    private VisionSubsystem vision;
    RobotHardware hardware;


    public AutoManager(DriveSubsystem driveTrain, RobotHardware hardware, VisionSubsystem vision) {
        this.driveTrain = driveTrain;
        this.hardware = hardware;
        this.vision = vision;
    }

    public void driveCm(double speed, double leftCm, double rightCm, double timeoutS, Telemetry telemetry) {
        resetEncoders();
        int newLeftTarget;
        int newRightTarget;

        // Determine new target position, and pass to motor controller
        newLeftTarget = hardware.getLeftFront().getCurrentPosition() + (int)(leftCm * COUNTS_PER_CM);
        newRightTarget = hardware.getRightFront().getCurrentPosition() + (int)(rightCm * COUNTS_PER_CM);
        hardware.getLeftFront().setTargetPosition(newLeftTarget);
        hardware.getRightFront().setTargetPosition(newRightTarget);
        hardware.getLeftBack().setTargetPosition(newLeftTarget);
        hardware.getRightBack().setTargetPosition(newRightTarget);

        // Turn On RUN_TO_POSITION
        hardware.getLeftFront().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.getRightFront().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.getLeftBack().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.getRightBack().setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // reset the timeout time and start motion.
        runtime.reset();
        hardware.getLeftFront().setPower(Math.abs(speed));
        hardware.getRightFront().setPower(Math.abs(speed));
        hardware.getRightBack().setPower(Math.abs(speed)); //Not sure if this will work, need to test
        hardware.getLeftBack().setPower(Math.abs(speed));  // Same as line above

        // keep looping while we are still active, and there is time left, and both motors are running.
        // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
        // its target position, the motion will stop.  This is "safer" in the event that the robot will
        // always end the motion as soon as possible.
        // However, if you require that BOTH motors have finished their moves before the robot continues
        // onto the next step, use (isBusy() || isBusy()) in the loop test.
        while (true || ((runtime.seconds() < timeoutS) &&
                (hardware.getLeftFront().isBusy() && hardware.getRightFront().isBusy()))) {

                // Data for the driver.
                telemetry.addData("Running to",  " %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Currently at",  " at %7d :%7d",
                        hardware.getLeftFront().getCurrentPosition(), hardware.getRightFront().getCurrentPosition());
                telemetry.update();
        }

        // Stop all motion;
        driveTrain.stopDriveTrain();

        // Turn off RUN_TO_POSITION
        hardware.getLeftFront().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hardware.getRightFront().setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //sleep(250);   // optional pause after each move.

    }


    public void resetEncoders(){
        hardware.getRightFront().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardware.getLeftFront().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        hardware.getRightFront().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hardware.getLeftFront().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    //Needs Testing

    /**
     * When a tage is visible this method should have the robot
     * line up with the target automatically.
     */
    public void driveToTag(){
        while (vision.getAlignmentError()[1] > 0.5 & vision.getAlignmentError()[2] > 0.5) {
            driveTrain.autoAlignment();
        }
    }


}









