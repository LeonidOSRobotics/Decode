//Importing team specific pedropathing files
package org.firstinspires.ftc.teamcode.pedroPathing.setUp;

//Importing general pedropathing files
import com.pedropathing.follower.Follower;
import com.pedropathing.util.Timer;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robot;

public abstract class PedroBase extends OpMode {

    public Follower follower;
    public Timer pathTimer, opModeTimer; //Make sure you don't go over time

    public PathState pathState; //Keeps track of robot's current state


    // Force child classes to define these
    public abstract void buildPaths();
    public abstract void statePathUpdate(); //Updates the state of the robot,
                                            // allows to switch between drive, shooting,
                                            // intaking, ect.
    public abstract Pose getStartPose();
    public abstract PathState getInitialState();

    public void setPathState(PathState newState){
        pathState = newState;
        pathTimer.resetTimer();


    }

    //Initalizes the hardware of the robot, timers, and bul
    @Override
    public void init() {
        Robot robot = new Robot();
        pathTimer = new Timer();
        opModeTimer = new Timer();

        follower = Constants.createFollower(hardwareMap); //Makes a driver to follow the route
        follower.setPose(getStartPose());
        buildPaths(); //Builds the routes the robot will take.
    }

    //Starts timers and sets up states
    @Override
    public void start(){
        opModeTimer.resetTimer();
        pathState = getInitialState();
        setPathState(pathState);
    }

    // Main code that runs on repeat.
    @Override
    public void loop() {
        follower.update();
        statePathUpdate();

        telemetry.addData("Path State", pathState);
        telemetry.addData("X", follower.getPose().getX());
        telemetry.addData("Y", follower.getPose().getY());
        telemetry.addData("Heading", headingDeg(follower.getPose()));
        telemetry.addData("Path Time", pathTimer.getElapsedTimeSeconds());
        telemetry.update();
    }


    //Following methods allow us to think in degrees instead of radians
    public static Pose poseDeg(double x, double y, double headingDeg) {
        return new Pose(x, y, Math.toRadians(headingDeg));
    }

    public static double headingDeg(Pose pose) {
        return Math.toDegrees(pose.getHeading());
    }
}
