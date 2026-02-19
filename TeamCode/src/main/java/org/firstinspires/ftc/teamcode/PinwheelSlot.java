package org.firstinspires.ftc.teamcode;

public class PinwheelSlot {
    private final double position;
    private boolean hasBall;

    private static final double pinwheelIncrement= .2/3;

    public PinwheelSlot(int position) {
        if (position== 0){
            this.position=0;
        } else if (position==1) {
            this.position = pinwheelIncrement;
        } else {
            this.position=pinwheelIncrement*2;
        }
        this.hasBall = false;
    }


    public void setHasBall(boolean hasBall) {
        this.hasBall = hasBall;
    }

    public boolean hasBall() {
        return hasBall;
    }

    public double getPosition() {
        return position;
    }
}
