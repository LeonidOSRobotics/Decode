package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Big Triangle", group="Robot")
public class BigTriangle extends LinearOpMode {

    AutoManager robot = new AutoManager();



    @Override
    public void runOpMode() throws InterruptedException {
        robot.driveCm(.5, 10, 10, 3.0);


    }
}
