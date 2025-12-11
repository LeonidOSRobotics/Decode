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
        robot.autoManager.driveCm(.25,150,150, true,20);//Forward 150cm
        robot.autoManager.strafeToPosition(150, .25, 15, telemetry);//right 150cm
        robot.autoManager.strafeToPosition(-300, .25, 15, telemetry);// left 300cm
        robot.autoManager.strafeToPosition(150, .25, 15, telemetry);//Back to center (150cm)
        robot.autoManager.driveCm(.25,-125 ,-125, false,20);//Back to Return position

    }


    
}
