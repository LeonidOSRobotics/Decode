package org.firstinspires.ftc.teamcode.robotSystems;

public class IntakeSubsystem {
    RobotHardware hardware;

    public IntakeSubsystem(RobotHardware hardware) { this.hardware = hardware;}
    public void powerOn(){
        hardware.getIntake().setPower(.5);
    }
    public void powerOff(){
        hardware.getIntake().setPower(0);
    }
    public void reverse(){
        hardware.getIntake().setPower(-1);
    }
    //public void servoIntake(){hardware.getServo().setPower(-.13);}

    //public void servoLaunch(){hardware.getServo().setPower(.18);}

    //public void stopServo() {
        //hardware.getServo().setPower(0);
    //}
}

