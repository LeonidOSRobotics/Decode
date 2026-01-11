package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous(name="Small Triangle Blue", group="Robot")
public class SmallTriangleBlue extends LinearOpMode{
    Robot robot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        robot.initRobot(hardwareMap);
        waitForStart();
        //robot.autoManager.strafeToPosition(-25, .5, 15,telemetry);
        robot.driveTrain.drive(0,-.5,0);
        sleep(200);
        robot.driveTrain.stopDriveTrain();
    }}
