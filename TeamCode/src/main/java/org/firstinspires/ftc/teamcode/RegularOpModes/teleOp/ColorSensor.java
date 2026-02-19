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
        intakeTimer = new Timer();
        robot.initRobot(hardwareMap);
        intakeTimer.resetTimer();
        robot.hardware.getPinwheelServo().setPosition(0.01 + .225/3 * 6);
        waitForStart();

        while (opModeIsActive()) {

           /* for(double i = 0.01; i<=1; i += (0.225/3) ) {
                robot.hardware.getPinwheelServo().setPosition(i);
                telemetry.addData("Position", i);
                telemetry.update();
                intakeTimer.resetTimer();
                while(intakeTimer.getElapsedTimeSeconds() < 2){

                }
            }
*/
            telemetry.addData("Value", robot.hardware.getColorSensor().green());
            telemetry.update();
            if(intakeTimer.getElapsedTimeSeconds() > 1.5 && robot.pinwheel.checkForBall(telemetry)){
                intakeTimer.resetTimer();
            }
            robot.pinwheel.updatePinwheelPosition();

        }
    }
}
