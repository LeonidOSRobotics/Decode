package org.firstinspires.ftc.teamcode.RegularOpModes.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;
@TeleOp
public class ColorSensor extends LinearOpMode {
    Robot robot = new Robot();

    @Override
    public void runOpMode() throws InterruptedException {

        robot.initRobot(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Hue", robot.hardware.getColorSensor().argb());
            telemetry.addData("Green", robot.hardware.getColorSensor().green());
            telemetry.addData("Red", robot.hardware.getColorSensor().red());
        telemetry.update();
        }
    }
}
