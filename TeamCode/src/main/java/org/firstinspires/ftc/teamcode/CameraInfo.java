package org.firstinspires.ftc.teamcode;


public class CameraInfo {
    private static final double A = 2.06;
    private static final double B = -147.06;

    /**
     * This method take in
     * @param cameraDistance Limelight's "distance" value (experimentally measured).
     * @return Predicted horizontal distance in centimeters.
     */
    public static double horizontalDistanceCm(double cameraDistance) {
        return A + B * cameraDistance;

    }
}



