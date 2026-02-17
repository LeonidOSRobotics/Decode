package org.firstinspires.ftc.teamcode.pedroPathingSetUp.PedroAutos;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathingSetUp.Constants;

@Autonomous(name="Big Triangle Blue", group="Robot")
public class BigTriangleBlue extends OpMode {
    private Follower follower;
    private Timer pathTimer, opModetimer;


    public enum PathState {
        DRIVE_STARTPOS_SHOOT_POS,
        SHOOT_PRELOAD,
        DRIVE_SHOOTPOS_ENDPOS
    }

    PathState pathState;

    private final Pose startPose = new Pose(24.700, 130.054, Math.toRadians(140));//DONT CHANGE
    private final Pose shootPose = new Pose(51.585, 92.247, Math.toRadians(140));
    private final Pose endPose = new Pose(29.909, 72.084, Math.toRadians(150));

    private PathChain driveStartPosShootPos, driveShootPosEndPos;

    public void buildPaths() {
        driveStartPosShootPos = follower.pathBuilder()
                .addPath(new BezierLine(startPose, shootPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), shootPose.getHeading())
                .build();
        driveShootPosEndPos = follower.pathBuilder()
                .addPath(new BezierLine(shootPose, endPose))
                .setLinearHeadingInterpolation(shootPose.getHeading(), endPose.getHeading())
                .build();

    }

    public void statePathUpdate() {
        switch (pathState) {
            case DRIVE_STARTPOS_SHOOT_POS:
                follower.followPath(driveStartPosShootPos, true);
                setPathState(PathState.SHOOT_PRELOAD);
                pathTimer.resetTimer();//reset timer and make new state
                break;
            case SHOOT_PRELOAD:
                //is follower done its path
                // and check that 5 seconds has elapsed
                if (!follower.isBusy() && pathTimer.getElapsedTimeSeconds() > 3) {
                    telemetry.addLine("Done Path 1");
                    follower.followPath(driveShootPosEndPos, true);
                    setPathState(PathState.DRIVE_SHOOTPOS_ENDPOS);
                    //transition to next state
                }
                break;
            case DRIVE_SHOOTPOS_ENDPOS:
                if (!follower.isBusy()) {
                    telemetry.addLine("Done all Paths");
                }
            default:
                telemetry.addLine("No state command");
                break;
        }
    }

    public void setPathState(PathState newState) {
        pathState = newState;
        pathTimer.resetTimer();


    }

    @Override
    public void init() {
        pathState = PathState.DRIVE_STARTPOS_SHOOT_POS;
        pathTimer = new Timer();
        opModetimer = new Timer();
        follower = Constants.createFollower(hardwareMap);
        // TODO add in any other init mechanisms
        buildPaths();
        follower.setPose(startPose);

    }

    public void start() {
        opModetimer.resetTimer();
        setPathState(pathState);

    }

    @Override
    public void loop() {
        follower.update();
        statePathUpdate();
        telemetry.addData("path state", pathState.toString());
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.addData("Path Time", pathTimer.getElapsedTimeSeconds());
    }
}

