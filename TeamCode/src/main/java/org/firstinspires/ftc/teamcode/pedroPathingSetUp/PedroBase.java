package org.firstinspires.ftc.teamcode.pedroPathingSetUp;

import static org.firstinspires.ftc.teamcode.pedroPathingSetUp.PathState.*;

import com.pedropathing.follower.Follower;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot;

public abstract class PedroBase extends OpMode {

    public Follower follower;
    public Timer pathTimer, opModeTimer;

    public PathState pathState = DRIVE_STARTPOS_SHOOT_POS;

    // Force child classes to define these
    public abstract void buildPaths();
    public abstract void statePathUpdate();

    public void setPathState(PathState newState){
        pathState = newState;
        pathTimer.resetTimer();


    }

    @Override
    public void init() {
        Robot robot = new Robot();
        pathTimer = new Timer();
        opModeTimer = new Timer();

        follower = Constants.createFollower(hardwareMap);

        buildPaths();
    }

    @Override
    public void start(){
        opModeTimer.resetTimer();
        setPathState(pathState);
    }

    @Override
    public void loop() {
        follower.update();
        statePathUpdate();

        telemetry.addData("Path State", pathState);
        telemetry.addData("X", follower.getPose().getX());
        telemetry.addData("Y", follower.getPose().getY());
        telemetry.addData("Heading", follower.getPose().getHeading());
        telemetry.addData("Path Time", pathTimer.getElapsedTimeSeconds());
    }
}
