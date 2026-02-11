package org.firstinspires.ftc.teamcode.robotSystems;

import org.firstinspires.ftc.teamcode.PinwheelSlot;

public class PinwheelSubsystem {
    RobotHardware hardware;
    PinwheelSlot[] heldArtifacts = {new PinwheelSlot(0),
                                    new PinwheelSlot(1),
                                    new PinwheelSlot(2)};
    
    private final int noBall = 0;
    private final int green = 2;
    private final int purple = 1;

    int currentIntakePos = 0;

    public PinwheelSubsystem(RobotHardware hardware) {
        this.hardware = hardware;

    }

    public void shootBall(){

    }

    public void shootBall(String color){

    }

    public void checkForBall(){
        if(getHue() !=  noBall){
            heldArtifacts[currentIntakePos].setColor();
            currentIntakePos++;
            if(currentIntakePos == 4){
                currentIntakePos = 0;
            }
        }
    }

    public int getHue(){
        return hardware.getPinwheelSensor().argb();
    }


    }