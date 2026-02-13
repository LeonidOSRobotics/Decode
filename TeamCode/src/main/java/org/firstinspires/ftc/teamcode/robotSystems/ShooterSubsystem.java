package org.firstinspires.ftc.teamcode.robotSystems;

import org.firstinspires.ftc.teamcode.RobotHardware;

public class ShooterSubsystem {
    RobotHardware hardware;

    public ShooterSubsystem(RobotHardware hardware) {
        this.hardware = hardware;
    }

    public void setVelocity(double velocity){
      // Converting the velocity to RPM
        velocity = (velocity / 60) * 28;
        hardware.getShooterLeft().setVelocity(velocity);
        hardware.getShooterRight().setVelocity(velocity);
    }

    public boolean isUpToSpeed(double target){
        double threshold = 50; //Allows for the motor to be up to 50 rpm lower than needed
        target = ((target-threshold)/60) * 28;
        boolean leftMotor = hardware.getShooterLeft().getVelocity() > target;
        boolean rightMotor = hardware.getShooterRight().getVelocity() > target;
        return leftMotor && rightMotor;
    }




    public void powerShooter(double val){
        hardware.getShooterLeft().setPower(val);
        hardware.getShooterRight().setPower(val);
    }

    public void powerOff(){
        hardware.getShooterLeft().setPower(0);
        hardware.getShooterRight().setPower(0);

    }

    }





