package org.firstinspires.ftc.teamcode.robotSystems;

public class ShooterSubsystem {
    RobotHardware hardware;

    public ShooterSubsystem(RobotHardware hardware) {
        this.hardware = hardware;
    }
    public void powerOn(){
        hardware.getShooterLeft().setPower(1);
        hardware.getShooterRight().setPower(1);
    }

    public void powerOff(){
        hardware.getShooterLeft().setPower(0);
        hardware.getShooterRight().setPower(0);

    }



}


