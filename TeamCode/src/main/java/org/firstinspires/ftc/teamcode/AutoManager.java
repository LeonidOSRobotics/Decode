package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class AutoManager extends Robot{

    static final double COUNTS_PER_MOTOR_GOBILDA    = 537.7 ;  // GoBilda Motor Encoder
    static final int WHEEL_DIAMETER_MM              = 104;     // GoBilda Mecanum Wheels
    static final double DRIVE_GEAR_REDUCTION        = 1.0 ;    // No External Gearing.
    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_CM             = ((COUNTS_PER_MOTOR_GOBILDA * DRIVE_GEAR_REDUCTION)/
            (WHEEL_DIAMETER_MM * 3.1415)) / 10;;


    public void driveCm(double speed, double leftCm, double rightCm, double timeoutS) {
        resetEncoders();
        int newLeftTarget;
        int newRightTarget;

        // Determine new target position, and pass to motor controller
        newLeftTarget = hardware.getLeftFront().getCurrentPosition() + (int)(leftCm * COUNTS_PER_CM);
        newRightTarget = hardware.getRightFront().getCurrentPosition() + (int)(rightCm * COUNTS_PER_CM);
        hardware.getLeftFront().setTargetPosition(newLeftTarget);
        hardware.getRightFront().setTargetPosition(newRightTarget);

        // Turn On RUN_TO_POSITION
        hardware.getLeftFront().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.getRightFront().setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // reset the timeout time and start motion.
        runtime.reset();
        hardware.getLeftFront().setPower(Math.abs(speed));
        hardware.getRightFront().setPower(Math.abs(speed));

        // keep looping while we are still active, and there is time left, and both motors are running.
        // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
        // its target position, the motion will stop.  This is "safer" in the event that the robot will
        // always end the motion as soon as possible.
        // However, if you require that BOTH motors have finished their moves before the robot continues
        // onto the next step, use (isBusy() || isBusy()) in the loop test.
        while ((runtime.seconds() < timeoutS) &&
                (hardware.getLeftFront().isBusy() && hardware.getRightFront().isBusy())) {
                // Display it for the driver.
                telemetry.addData("Running to",  " %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Currently at",  " at %7d :%7d",
                        hardware.getLeftFront().getCurrentPosition(), hardware.getRightFront().getCurrentPosition());
                telemetry.update();
        }

        // Stop all motion;
        hardware.getLeftFront().setPower(0);
        hardware.getRightFront().setPower(0);

        // Turn off RUN_TO_POSITION
        hardware.getLeftFront().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hardware.getRightFront().setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // sleep(250);   // optional pause after each move.

        }


    public void resetEncoders(){
        hardware.getRightFront().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardware.getLeftFront().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        hardware.getRightFront().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hardware.getLeftFront().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    /*
    public void driveToTag(int tagID){
        vision.getTagID(tagID);
        driveTrain.autoAlignment();

    }
    public void alignAndShoot(){
        vision.findGoal();
        robot.drive.alignToTarget(robot.vision.getTargetOffset());
        robot.shooter.fire();
}
*/
}









