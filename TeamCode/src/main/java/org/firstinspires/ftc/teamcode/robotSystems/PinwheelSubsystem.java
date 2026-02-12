package org.firstinspires.ftc.teamcode.robotSystems;

import org.firstinspires.ftc.teamcode.PinwheelSlot;

public class PinwheelSubsystem {
    RobotHardware hardware;
    PinwheelSlot[] heldArtifacts = {new PinwheelSlot(0),
                                    new PinwheelSlot(1),
                                    new PinwheelSlot(2)};

    private boolean isFull = false;

    private final int minNoBall = 0;
    private final int maxNoBall = 0;
    private final int minGreen = 3;
    private final int maxGreen = 4;
    private final int minPurple = 1;
    private final int maxPurple = 2;

    int currentIntakePos = 2;       //Options are 1, 2, and 3

    public PinwheelSubsystem(RobotHardware hardware) {
        this.hardware = hardware;

    }

    public void shootBall(){

    }

    public void shootBall(String color){

    }

    public void checkForBall(){
        int hue = getHue();
        if(hue >= minGreen && hue <= maxGreen){
            heldArtifacts[currentIntakePos].setColor("Green");
        }else if(hue >= minPurple && hue <= maxPurple){
            heldArtifacts[currentIntakePos].setColor("Purple");
        }
        if(!heldArtifacts[currentIntakePos].getColor().equals("No Ball")){
            currentIntakePos = findClosest("No Ball");
                isFull = allSlotsFull();
        }

    }



    public int getHue(){
        return hardware.getPinwheelSensor().argb();
    }

    public void returnToCenter(){
        currentIntakePos = 2;
        updatePinwheelPosition();
    }

    public void updatePinwheelPosition(){
        hardware.getPinwheelServo().setPosition(heldArtifacts[currentIntakePos].getPosition());
    }

    private int findClosest(String ballType){
        if( currentIntakePos-1 != 0 && heldArtifacts[currentIntakePos-1].equals(ballType)){
            return currentIntakePos-1;
        }else if(currentIntakePos+1 != 4 && heldArtifacts[currentIntakePos+1].equals(ballType)){
            return currentIntakePos+1;
        }else{
            return currentIntakePos;
        }
    }
    public boolean allSlotsOpen(){
        for(PinwheelSlot slot: heldArtifacts){
            if (!slot.getColor().equals("No Ball")){
                return false;
            }
        }

        return true;
    }

    public boolean allSlotsFull(){
        for(int i = 0; i < heldArtifacts.length; i++){
            if (heldArtifacts[i].getColor().equals("No Ball")){
                return false;
            }
        }
        return true;
    }




    }