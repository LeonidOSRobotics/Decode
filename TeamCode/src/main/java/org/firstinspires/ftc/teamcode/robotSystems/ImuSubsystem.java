package org.firstinspires.ftc.teamcode.robotSystems;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.RobotHardware;

public class ImuSubsystem {
    RobotHardware hardware;

    public ImuSubsystem(RobotHardware hardware) {
        this.hardware = hardware;
    }

    public double getBotHeading(){
        return hardware.getImu().getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS) - Math.PI;
    }

    public void resetYaw() {
        hardware.getImu().resetYaw();
    }
}
