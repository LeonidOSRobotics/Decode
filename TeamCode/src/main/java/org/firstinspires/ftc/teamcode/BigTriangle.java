package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Big Triangle", group="Robot")
public class BigTriangle extends LinearOpMode {
    Robot robot = new Robot();
    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // No External Gearing.
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.141529);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;



    private ElapsedTime runtime = new ElapsedTime();

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the OpMode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.hardware.getLeftFront().getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.hardware.getRightFront().getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            robot.hardware.getLeftFront().setTargetPosition(newLeftTarget);
            robot.hardware.getRightFront().setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            robot.hardware.getLeftFront().setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.hardware.getRightFront().setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.hardware.getLeftFront().setPower(Math.abs(speed));
            robot.hardware.getRightFront().setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    ( robot.hardware.getLeftFront().isBusy() && robot.hardware.getRightFront().isBusy())) {

                // Display it for the driver.
                telemetry.addData("Running to",  " %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Currently at",  " at %7d :%7d",
                        robot.hardware.getLeftFront().getCurrentPosition(), robot.hardware.getRightFront().getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.hardware.getLeftFront().setPower(0);
            robot.hardware.getRightFront().setPower(0);

            // Turn off RUN_TO_POSITION
            robot.hardware.getLeftFront().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.hardware.getRightFront().setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);   // optional pause after each move.
        }
    }







    @Override
    public void runOpMode() throws InterruptedException {
        encoderDrive(DRIVE_SPEED, 10, 10, 3.0);


    }
}
