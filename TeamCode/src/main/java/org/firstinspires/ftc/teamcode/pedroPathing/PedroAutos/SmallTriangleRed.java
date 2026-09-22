package org.firstinspires.ftc.teamcode.pedroPathing.PedroAutos;
import com.pedropathing.follower.Follower;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.setUp.Constants;

@Autonomous(name="Small Triangle Red", group="Robot")
public class SmallTriangleRed extends OpMode {
    private Follower follower;
    private Timer pathTimer, opModetimer;


    private final Pose startPose = new Pose(100.885, 8.569, Math.toRadians(90));//DONT CHANGE
    private final Pose endPose = new Pose(87.039, 43.519, Math.toRadians(90));


    private PathChain path1;

    public void buildPaths() {
        path1 = follower.pathBuilder()
                .addPath(new BezierLine(startPose, endPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), endPose.getHeading())
                .build();
    }

    public void statePathUpdate() {
        follower.followPath(path1, true);
    }



    @Override
    public void init() {

        pathTimer = new Timer();
        opModetimer = new Timer();
        follower = Constants.createFollower(hardwareMap);
        buildPaths();
        follower.setPose(startPose);

    }

    public void start() {
        opModetimer.resetTimer();

    }

    @Override
    public void loop() {
        follower.update();
        statePathUpdate();
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.addData("Path Time", pathTimer.getElapsedTimeSeconds());
    }
}