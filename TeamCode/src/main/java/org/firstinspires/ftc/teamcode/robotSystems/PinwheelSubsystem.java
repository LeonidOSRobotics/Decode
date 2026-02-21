package org.firstinspires.ftc.teamcode.robotSystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PinwheelSlot;
import org.firstinspires.ftc.teamcode.RobotHardware;

import com.pedropathing.util.Timer;

public class PinwheelSubsystem {
    RobotHardware hardware;
    PinwheelSlot[] heldArtifacts = {new PinwheelSlot(6),
                                    new PinwheelSlot(7),
                                    new PinwheelSlot(8)};

    private boolean isFull = false;

    private final double loweredArm = .55;
    private final double raisedArm = 0.1;

    private final int NoBall = 110;

    int currentIntakePos = 6;       //Starts at 6 with is in the middle of the wheel

    public PinwheelSubsystem(RobotHardware hardware) {
        this.hardware = hardware;
    }

    public void shootBall(){
        Timer leverArmTimer = new Timer();
        hardware.getLeverArm().setPosition(raisedArm);
        leverArmTimer.resetTimer();
        while(leverArmTimer.getElapsedTimeSeconds() < 1.2){
        }
        hardware.getLeverArm().setPosition(loweredArm);
        heldArtifacts[(currentIntakePos%3)].setHasBall(false);
        currentIntakePos++;
        pinwheelWrap();
        while(leverArmTimer.getElapsedTimeSeconds() < 1.2){
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
            telemetry.addData("Current Location based on Position", currentIntakePos * PinwheelSlot.pinwheelIncrement);
            telemetry.addData("Current Intake Pos Number", currentIntakePos * PinwheelSlot.pinwheelIncrement);
            telemetry.addData("Has Ball? ", hasBall);
        if(!allSlotsFull()) {
            if (hasBall) {
                heldArtifacts[currentIntakePos % 3].setHasBall(true);
                currentIntakePos++; //findClosest("No Ball");
                pinwheelWrap();

            }

            telemetry.addData("New Location", currentIntakePos * PinwheelSlot.pinwheelIncrement);
            isFull = allSlotsFull();
            for (int i = 0; i < heldArtifacts.length; i++) {
                telemetry.addData("Slot" + i + " Has a Ball:", currentIntakePos * PinwheelSlot.pinwheelIncrement);
            }

            telemetry.update();
        }
        return hasBall;

    }

    public int getGreen(){
        return hardware.getColorSensor().green();
    }

    public void MoveToCenter(){
        currentIntakePos = 6;
    }

    public void pinwheelWrap(){
        if((currentIntakePos > 13 || currentIntakePos < 1) && allSlotsOpen()){
            MoveToCenter();
        }else if(currentIntakePos > 13){
            currentIntakePos = 11;
        }else if( currentIntakePos < 1){
            currentIntakePos = 3;
        }
    }

    public void updatePinwheelPosition(){
        hardware.getPinwheelServo().setPosition(0.01 + currentIntakePos * PinwheelSlot.pinwheelIncrement);
    }

    private int findClosest(String ballType){
        if( currentIntakePos-1 != -1 && heldArtifacts[(currentIntakePos%3)-1].equals(ballType)){
            return currentIntakePos-1;
        }else if(currentIntakePos+1 != 3 && heldArtifacts[(currentIntakePos%3)+1].equals(ballType)){
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