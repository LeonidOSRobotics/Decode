package org.firstinspires.ftc.teamcode.RegularOpModes.teleOp;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(name = "TeleOp", group = "Sensor")
public class Qual2TeleOp extends LinearOpMode {
    Robot robot = new Robot();



    @Override
    public void runOpMode() throws InterruptedException {
        robot.initRobot(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {

            //Changing the LED lights based on certain conditions
            int tagID = robot.vision.getTagID();
            if (tagID == 20) {
                robot.LED.setBlue();
            } else if (tagID == 24) {
                robot.LED.setRed();
            } else if (robot.pinwheel.allSlotsFull()) {
                robot.LED.setLaunch();
                robot.intake.powerOff();
            } else {
                robot.LED.setOff();
            }

            if(gamepad2.b){
                robot.hardware.getPinwheelServo().setPosition(0);
                sleep(2000);
                robot.hardware.getPinwheelServo().setPosition(.5);
                sleep(2000);
                robot.hardware.getPinwheelServo().setPosition(1);
                telemetry.addData("Servo Value", robot.hardware.getPinwheelServo().getPosition());
                telemetry.update();

            }

            //Option for auto alignment or regular drive.
            if (tagID != -1 && gamepad1.left_bumper) {
                robot.driveTrain.autoAlignment();
            }
            else {
                double rotate = gamepad1.left_trigger - gamepad1.right_trigger;
                robot.driveTrain.fieldOrientedDrive(-gamepad1.left_stick_y, gamepad1.left_stick_x, -rotate);
            }

            //Resetting the robot's orientation
            if(gamepad1.start){
                robot.imu.resetYaw();
            }

            //if the pinwheel has no artifacts start the intake
            if(gamepad1.b) {
                robot.intake.reverse();
            }
            else if(!robot.pinwheel.allSlotsFull()){
                robot.intake.powerOn();
            }

            //Each loop check for a ball in the pinwheel
            robot.pinwheel.checkForBall(telemetry);
            //robot.pinwheel.updatePinwheelPosition();







        }
    }
}
