package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


/*
 * This class serves as a sample teleOp the both test the ability of the
 * organization system to build and also provides a template for future op modes
 */
@TeleOp(name = "TeleOp", group = "Sensor")
public class ScrimmageTeleOp extends LinearOpMode {
    private Robot robot = new Robot();


    @Override
    public void runOpMode() throws InterruptedException {
        robot.initRobot(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            int tagID = robot.vision.getTagID();
            if ( tagID == 20) {
                robot.LED.setBlue();
            }
            else if (tagID == 24) {
                robot.LED.setRed();
            }
            else if (tagID == -1) {
                robot.LED.setOff();

            }
            telemetry.addData("ID: ", robot.vision.getTagID());
            telemetry.update();
            if (tagID != -1 && gamepad1.left_bumper) {
                robot.driveTrain.autoAlignment();
            }
            else {
                double rotate = gamepad1.left_trigger - gamepad1.right_trigger;
                robot.driveTrain.fieldOrientedDrive(-gamepad1.left_stick_y, gamepad1.left_stick_x, -rotate);
            }

            if (gamepad1.right_bumper){
                robot.shooter.powerOn();

            }
            else {
                robot.shooter.powerOff();
            }
            if(gamepad1.start){
                robot.imu.resetYaw();
            }

        }

    }
}
