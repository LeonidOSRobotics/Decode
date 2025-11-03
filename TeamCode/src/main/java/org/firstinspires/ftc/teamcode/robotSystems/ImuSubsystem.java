package org.firstinspires.ftc.teamcode.robotSystems;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class ImuSubsystem {
    RobotHardware hardware;

    public ImuSubsystem(RobotHardware hardware) {
        this.hardware = hardware;
    }

    public double getBotHeading(){
        return hardware.getImu().getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS) - Math.PI;
    }

    private void resetYaw() {
        hardware.getImu().resetYaw();
    }
}
