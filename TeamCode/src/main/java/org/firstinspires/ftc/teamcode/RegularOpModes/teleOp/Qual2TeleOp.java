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
            } else {
                robot.LED.setOff();
            }


            //Driving Code
            double rotate = gamepad1.left_trigger - gamepad1.right_trigger;
            robot.driveTrain.fieldOrientedDrive(-gamepad1.left_stick_y, gamepad1.left_stick_x, -rotate);

            //Resetting the robot's orientation
            if(gamepad1.start){
                robot.imu.resetYaw();
            }


            //Intake Code
            //if the pinwheel has no artifacts start the intake
            if(gamepad1.a){
                robot.intake.powerOn();
            }else if(gamepad1.b) {
                robot.intake.reverse();
            }else{
                robot.intake.powerOff();
            }



            if(gamepad2.right_bumper){
                robot.pinwheel.shootBall();
            }

            if(gamepad2.y){
                robot.shooter.setVelocity(3800);
            }else if(gamepad2.x){
                robot.shooter.setVelocity(0);
            }


            if(gamepad2.a){
                robot.hardware.getHoodservo().setPosition(0.65);
            }else if (gamepad2.b){
                robot.hardware.getHoodservo().setPosition(.4);
            }


        }
    }
}
