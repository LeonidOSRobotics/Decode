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
        robot.driveTrain.drive(.7,0,0);
        sleep(200);
        robot.driveTrain.stopDriveTrain();
        robot.autoManager.driveCm(.65, 50, 50, true, 10);// ***BIG TRIANGLE***
      //robot.autoManager.turnDegrees(180);


    }


    
}
