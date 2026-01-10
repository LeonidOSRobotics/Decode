package org.firstinspires.ftc.teamcode.robotSystems;

public class IntakeSubsystem {
    RobotHardware hardware;

    public IntakeSubsystem(RobotHardware hardware) { this.hardware = hardware;}
    public void powerOn(){
        hardware.getIntake().setPower(1);
    }
    public void powerOff(){
        hardware.getIntake().setPower(0);
    }

    public void servoIntake(){hardware.getServo().setPosition(.8);}

    public void servoLaunch(){hardware.getServo().setPosition(-.8);}
}

