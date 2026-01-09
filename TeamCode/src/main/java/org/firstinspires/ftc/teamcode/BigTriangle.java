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
        robot.autoManager.driveCm(.5, 50, 50, true, 10);// ***BIG TRIANGLE***
        //robot.autoManager.driveCm(.5,50,50, true,20);//***BIG TRIANGLE***
        //robot.autoManager.strafeToPosition(25, .5, 15, telemetry);// ***USE IF LEFT WHEEL ON SMALL TRIANGLE***
       //robot.autoManager.strafeToPosition(-25, .5, 15,telemetry);// ***USE IF RIGHT WHEEL ON SMALL TRIANGLE***

      //robot.autoManager.turnDegrees(180);


    }


    
}
