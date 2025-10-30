package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


/*
 * This class serves as a sample teleOp the both test the ability of the
 * organization system to build and also provides a template for future op modes
 */

public class SampleTeleOp extends LinearOpMode {
    private Robot robot = new Robot();


    @Override
    public void runOpMode() throws InterruptedException {
        robot.initRobot(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.x) {
                robot.LED.setBlue();
            }
            else if (gamepad1.b) {
                robot.LED.setRed();
            }
            else if (gamepad1.y) {
                robot.LED.setOff();

            }
        }

    }
}
