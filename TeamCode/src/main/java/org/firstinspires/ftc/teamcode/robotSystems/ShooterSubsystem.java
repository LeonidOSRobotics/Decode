package org.firstinspires.ftc.teamcode.robotSystems;

public class ShooterSubsystem {
    RobotHardware hardware;

    public ShooterSubsystem(RobotHardware hardware) {
        this.hardware = hardware;
    }

    public void setVelocity(double velocity){
      // Converting the velocity to RPM
        velocity = (velocity / 60) * .28;
        hardware.getShooterLeft().setVelocity(velocity);
        hardware.getShooterRight().setVelocity(velocity);

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





