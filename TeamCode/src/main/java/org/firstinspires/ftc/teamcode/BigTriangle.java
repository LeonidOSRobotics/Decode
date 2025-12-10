package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Big Triangle", group="Robot")
public class BigTriangle extends LinearOpMode {
    Robot robot = new Robot();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.initRobot(hardwareMap);
        waitForStart();
        robot.autoManager.driveCm(.25,250,250, true,20);
        robot.autoManager.strafeToPosition(125, .25, 15, telemetry);
        robot.autoManager.strafeToPosition(-125, .25, 15, telemetry);
        robot.autoManager.driveCm(.25,-225,-225, false,20);

    }


    
}
