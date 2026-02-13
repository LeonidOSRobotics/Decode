package org.firstinspires.ftc.teamcode.pedroPathingSetUp.PedroAutos;

import org.firstinspires.ftc.teamcode.AutoPathing;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.pedroPathingSetUp.AutoPositions;
import org.firstinspires.ftc.teamcode.pedroPathingSetUp.PedroBase;

public class SmallTriangle extends PedroBase {
    Robot robot = new Robot();

    private double TARGET_FLYWHEEL_RPM = 4200;

    Pose currentPos =


    @Override
    public void buildPaths() {

    }

    @Override
    public void statePathUpdate() {
        switch(pathState){
            case SPIN_UP:
                if(robot.shooter.isUpToSpeed(TARGET_FLYWHEEL_RPM)){
                    if(currentPos.equals(AutoPositions.SHOOTPOSE.getPos())){
                        setPathState(pathState.LAUNCH);
                    }
                }
                break;
            case LAUNCH:
                //lifts Arm and ball shoots
                setPathState(pathState.IDLE);
                break;
            case IDLE:
                //Turn off intake
                if(robot.);
        }


    }


}
