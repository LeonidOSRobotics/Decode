package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Big Triangle", group="Robot")
public class BigTriangle extends LinearOpMode {

    AutoManager robot = new AutoManager();



    @Override
    public void runOpMode() throws InterruptedException {
        robot.driveinches(.5, 10, 10, 3.0);


    }
}
