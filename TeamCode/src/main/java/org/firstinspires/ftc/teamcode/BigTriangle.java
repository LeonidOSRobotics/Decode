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
        //robot.autoManager.driveCm(.5, 10, 10, true, 10);

        //robot.autoManager.driveCm(.35,100,100, true,20);//Forward 150cm
        //robot.autoManager.driveCm(.35,100,100, true,20);//Forward 150cm

        //robot.autoManager.strafeToPosition(100, .35, 15, telemetry);//right 150cm
        //robot.autoManager.strafeToPosition(-200, .35, 15, telemetry);// left 300cm
        //robot.autoManager.strafeToPosition(100, .35, 15,  telemetry);//Back to center (150cm)
        //robot.autoManager.driveCm(.35,-100 ,-100, false,20);//Back to Return position

        robot.autoManager.turnDegrees(150, telemetry);


    }


    
}
