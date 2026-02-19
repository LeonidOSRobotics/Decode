package org.firstinspires.ftc.teamcode;

public class PinwheelSlot {
    private final double position;
    private boolean hasBall;

    public static final double pinwheelIncrement= .225/3;

    public PinwheelSlot(int position) {
        this.position= 0.01 + pinwheelIncrement * position;

        this.hasBall = false;
    }


    public void setHasBall(boolean hasBall) {
        this.hasBall = hasBall;
    }

    public boolean hasBall() {
        return hasBall;
    }

    //public double getPosition() {
     //   return position;
   // }
}
