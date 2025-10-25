package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;



public void drive(double forward, double strafe, double rotateLeft, double rotateRight, boolean stickPressed) {
    double y = forward;
    double x = strafe;
    double rotate = rotateRight - rotateLeft;
    //Slows speed of wheels
    double dampening;
    if(stickPressed) {
        dampening = .8;
    }else{
        dampening = .6;
    }

    //Calculating the power for the wheels
    double frontLeftPower = (y + x + rotate) * dampening;
    double backLeftPower = (y - x + rotate) * dampening;
    double frontRightPower = (y - x - rotate) * dampening;
    double backRightPower = (y + x - rotate) * dampening;

    //Set Power
    leftFront.setPower(frontLeftPower);
    rightFront.setPower(frontRightPower);
    leftBack.setPower(backLeftPower);
    rightBack.setPower(backRightPower);
}

