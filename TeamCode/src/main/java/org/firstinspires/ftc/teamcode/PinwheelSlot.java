package org.firstinspires.ftc.teamcode;

public class PinwheelSlot {
    private double position;
    private String color;

    private final double pinwheelIncrement=0.4/3;

    public PinwheelSlot(int position) {
        if (position== 0){
            this.position=0;
        } else if (position==1) {
            this.position = pinwheelIncrement;
        } else {
            this.position=pinwheelIncrement*2;
        }
        this.color = "No Ball";
    }


    public void setColor(String color) {
        this.color = color;
    }
}
