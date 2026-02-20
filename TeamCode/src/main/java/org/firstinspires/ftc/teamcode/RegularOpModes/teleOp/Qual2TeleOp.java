package org.firstinspires.ftc.teamcode.RegularOpModes.teleOp;


import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.robotSystems.PinwheelSubsystem;

@TeleOp(name = "TeleOp", group = "Sensor")
public class Qual2TeleOp extends LinearOpMode {
    Robot robot = new Robot();
    private Timer intakeTimer;



    @Override
    public void runOpMode() throws InterruptedException {
        intakeTimer = new Timer();
        robot.initRobot(hardwareMap);
        intakeTimer.resetTimer();

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


            //Driving Code
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


            //Intake Code
            //if the pinwheel has no artifacts start the intake
            if(gamepad1.b) {
                robot.intake.reverse();
            }
            else if(!robot.pinwheel.allSlotsFull()){
                robot.intake.powerOn();
            }
            //Each loop check for a ball in the pinwheel
            if(intakeTimer.getElapsedTimeSeconds() > 1.5 && robot.pinwheel.checkForBall(telemetry)){
                intakeTimer.resetTimer();
                robot.pinwheel.updatePinwheelPosition();
            }



            if(gamepad1.right_bumper){
                robot.pinwheel.shootBall();
            }

            if(gamepad2.y){
                robot.shooter.setVelocity(3800);
            }


        }
    }
}
