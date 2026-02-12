package org.firstinspires.ftc.teamcode.RegularOpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

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
