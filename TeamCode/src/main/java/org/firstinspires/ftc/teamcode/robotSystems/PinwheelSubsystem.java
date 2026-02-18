package org.firstinspires.ftc.teamcode.robotSystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PinwheelSlot;
import org.firstinspires.ftc.teamcode.RobotHardware;

public class PinwheelSubsystem {
    RobotHardware hardware;
    PinwheelSlot[] heldArtifacts = {new PinwheelSlot(0),
                                    new PinwheelSlot(0),
                                    new PinwheelSlot(0)};

    private boolean isFull = false;

    private final double loweredArm = 0;
    private final double raisedArm = 0;

    private final int NoBall = 100;

    int currentIntakePos = 2;       //Options are 1, 2, and 3

    public PinwheelSubsystem(RobotHardware hardware) {
        this.hardware = hardware;

    }

    public void shootBall(){
        hardware.getLeverArm().setPosition(raisedArm);
        while (hardware.getLeverArm().getPosition() != raisedArm){
        }
        hardware.getLeverArm().setPosition(loweredArm);
        currentIntakePos++;
        if(currentIntakePos == 3){
            currentIntakePos = 0;//TODO Fix for angle wrapping
        }
    }

    public void shootBall(String color){
        currentIntakePos = findClosest(color);
        updatePinwheelPosition();
        shootBall();

    }

    public void checkForBall(Telemetry telemetry){
        int colorvalue = getGreen();
        telemetry.addData("Value", colorvalue);
        telemetry.addData("Current Location", heldArtifacts[currentIntakePos].getPosition() );
        if(colorvalue < NoBall){
            //heldArtifacts[currentIntakePos].setHasBall(true);

            telemetry.addData("Has Ball", true );
        }
        if(heldArtifacts[currentIntakePos].hasBall()){
            currentIntakePos++; //findClosest("No Ball");

            if(currentIntakePos == 3){
                currentIntakePos = 0;//TODO Fix for angle wrapping
            }
            telemetry.addData("New Location", heldArtifacts[currentIntakePos].getPosition() );

            isFull = allSlotsFull();
        }
        telemetry.update();

    }

    public int getGreen(){
        return hardware.getColorSensor().green();
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
            if (!slot.hasBall()){
                return false;
            }
        }

        return true;
    }

    public boolean allSlotsFull(){
        for (PinwheelSlot heldArtifact : heldArtifacts) {
            if (!heldArtifact.hasBall()) {
                return false;
            }
        }
        return true;
    }




    }