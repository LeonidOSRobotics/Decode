package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.Range;

public class VisionSubsystem {
    private static final double MAX_AUTO_SPEED = 0.5;
    private static final double MAX_AUTO_STRAFE = 0.5;
    private static final double MAX_AUTO_TURN = 0.3;
    private static final double SPEED_GAIN = 0.02;
    private static final double STRAFE_GAIN = 0.1;
    private static final double TURN_GAIN = 0.05;
    public double calculateAutoDrive(double rangeError) {
        return Range.clip(rangeError * SPEED_GAIN, -MAX_AUTO_SPEED, MAX_AUTO_SPEED);
    }

    public double calculateAutoTurn(double headingError) {
        return Range.clip(headingError * -TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN);
    }

    public double calculateAutoStrafe(double lateralIn) {
        return Range.clip(lateralIn * STRAFE_GAIN, -MAX_AUTO_STRAFE, MAX_AUTO_STRAFE);
    }
}
