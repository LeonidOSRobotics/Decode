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

    //todo Add in a range so it doesn't have to be exact value
    public boolean isUpToSpeed(double target){
        target = (target/60) * 28;
        return hardware.getShooterLeft().getVelocity() == target && hardware.getShooterRight().getVelocity() == target;

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





