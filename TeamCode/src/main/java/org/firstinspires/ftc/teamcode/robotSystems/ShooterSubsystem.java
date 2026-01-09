package org.firstinspires.ftc.teamcode.robotSystems;

public class ShooterSubsystem {
    RobotHardware hardware;

    public ShooterSubsystem(RobotHardware hardware) {
        this.hardware = hardware;
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


