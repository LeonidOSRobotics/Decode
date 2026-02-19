package org.firstinspires.ftc.teamcode.robotSystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PinwheelSlot;
import org.firstinspires.ftc.teamcode.RobotHardware;

public class PinwheelSubsystem {
    RobotHardware hardware;
    PinwheelSlot[] heldArtifacts = {new PinwheelSlot(0),
                                    new PinwheelSlot(1),
                                    new PinwheelSlot(2)};

    private boolean isFull = false;

    private final double loweredArm = 0;
    private final double raisedArm = 0;

    private final int NoBall = 100;

    int currentIntakePos = 0;       //Options are 1, 2, and 3

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

    public boolean checkForBall(Telemetry telemetry){

        int colorvalue = getGreen();
        boolean hasBall = colorvalue < NoBall;
        telemetry.addData("Value", colorvalue);
        telemetry.addData("Current Location based on Position", heldArtifacts[currentIntakePos].getPosition());
        telemetry.addData("Current Intake Pos Number", currentIntakePos);
        telemetry.addData("Has Ball? ", hasBall );

        if(hasBall){
            heldArtifacts[currentIntakePos].setHasBall(true);
            currentIntakePos++; //findClosest("No Ball");

            if(currentIntakePos == 3){
                currentIntakePos = 0;//TODO Fix for angle wrapping
            }

        }

        telemetry.addData("New Location", heldArtifacts[currentIntakePos].getPosition() );
        isFull = allSlotsFull();
        for(int i = 0; i< heldArtifacts.length; i++){
            telemetry.addData("Slot" + i + " Has a Ball:", heldArtifacts[currentIntakePos].hasBall());
        }

        telemetry.update();
        return hasBall;

    }

    public int getGreen(){
        return hardware.getColorSensor().green();
    }

    public void MoveToCenter(){
        currentIntakePos = 0;
        updatePinwheelPosition();
    }

    public void updatePinwheelPosition(){
        hardware.getPinwheelServo().setPosition(heldArtifacts[currentIntakePos].getPosition());
    }

    private int findClosest(String ballType){
        if( currentIntakePos-1 != -1 && heldArtifacts[currentIntakePos-1].equals(ballType)){
            return currentIntakePos-1;
        }else if(currentIntakePos+1 != 3 && heldArtifacts[currentIntakePos+1].equals(ballType)){
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