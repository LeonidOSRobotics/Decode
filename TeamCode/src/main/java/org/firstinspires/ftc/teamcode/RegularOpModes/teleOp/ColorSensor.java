package org.firstinspires.ftc.teamcode.RegularOpModes.teleOp;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;



@TeleOp
public class ColorSensor extends LinearOpMode {
    Robot robot = new Robot();
    Timer intakeTimer;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.initRobot(hardwareMap);
        intakeTimer.resetTimer();
        robot.hardware.getPinwheelServo().setPosition(0);
        waitForStart();

        while (opModeIsActive()) {



            if(intakeTimer.getElapsedTimeSeconds() > 1 && robot.pinwheel.checkForBall(telemetry)){
                intakeTimer.resetTimer();
            }

        }
    }
}
