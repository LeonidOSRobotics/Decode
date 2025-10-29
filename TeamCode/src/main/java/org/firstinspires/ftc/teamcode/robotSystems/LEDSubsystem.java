package org.firstinspires.ftc.teamcode.robotSystems;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

public class LEDSubsystem {

    private final RevBlinkinLedDriver blinkinLedDriver;

    public LEDSubsystem(RobotHardware hardware) {
        blinkinLedDriver = hardware.getBlinkin();
        if (blinkinLedDriver != null) {
            blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_RAINBOW_PALETTE);
        }
    }

    public void setRed() {
        if (blinkinLedDriver != null) {
            blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.SHOT_RED);
        }
    }

    public void setBlue() {
        if (blinkinLedDriver != null) {
            blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.SHOT_BLUE);
        }
    }

    public void setOff() {
        if (blinkinLedDriver != null) {
            blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);
        }
    }

    public void setLaunch() {
        if (blinkinLedDriver != null) {
            blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_OCEAN_PALETTE);
        }
    }
}