package org.firstinspires.ftc.teamcode.RegularOpModes.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;


/*
 * This class serves as a sample teleOp the both test the ability of the
 * organization system to build and also provides a template for future op modes
 */
@Disabled
@TeleOp(name = "Old TeleOp", group = "Sensor")
public class Qual1TeleOp extends LinearOpMode {
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


                robot.shooter.powerShooter(gamepad2.left_trigger);
            if(gamepad2.a) {
                robot.shooter.powerShooter(.2);
            } else if (gamepad2.x) {
                robot.shooter.powerShooter(.35);
            }else if (gamepad2.y) {
                robot.shooter.powerShooter(.6);

            } else if (gamepad2.b) {
                robot.shooter.powerShooter(.8);
            }else{
                robot.shooter.powerOff();
            }


            if(gamepad1.start){
                robot.imu.resetYaw();
            }
            if(gamepad1.a){
                robot.intake.powerOn();
            }
            else if(gamepad1.b){
                robot.intake.reverse();
            }
                else{
                    robot.intake.powerOff();
                }

            if(gamepad2.dpad_up){
                robot.intake.powerOn();
            }else if (gamepad2.dpad_down){
                robot.intake.reverse();
            }else{
                robot.intake.powerOff();
            }
            }


    }
}