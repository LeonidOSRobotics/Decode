package org.firstinspires.ftc.teamcode.pedroPathingSetUp.PedroAutos;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathingSetUp.Constants;

@Autonomous(name="Big Triangle Red", group="Robot")
public class BigTriangleRed extends OpMode {
    private Follower follower;
    private Timer pathTimer, opModetimer;


    public enum PathState {
        DRIVE_STARTPOS_SHOOT_POS,
        SHOOT_PRELOAD,
        DRIVE_SHOOTPOS_ENDPOS
    }

    PathState pathState;

    private final Pose startPose = new Pose(31.226, 8.476, Math.toRadians(90));//DONT CHANGE
    private final Pose endPose = new Pose(31.226, 36.953, Math.toRadians(90));


    private PathChain driveStartPosShootPos, driveShootPosEndPos;

    public void buildPaths() {
        driveStartPosShootPos = follower.pathBuilder()
                .addPath(new BezierLine(startPose, endPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), endPose.getHeading())
                .build();

    }

    /*    public void statePathUpdate() {
         follower.followPath(driveStartPosShootPos);
         }
     }

  public void setPathState(PathState newState) {
         pathState = newState;
         pathTimer.resetTimer();


     }
 */
    @Override
    public void init() {
       // pathState = PathState.DRIVE_STARTPOS_SHOOT_POS;
        pathTimer = new Timer();
        opModetimer = new Timer();
        follower = Constants.createFollower(hardwareMap);
        // TODO add in any other init mechanisms
        buildPaths();
        follower.setPose(startPose);

    }

    public void start() {
        opModetimer.resetTimer();
       // setPathState(pathState);

    }

    @Override
    public void loop() {
        follower.update();
        follower.followPath(driveStartPosShootPos);
      //  telemetry.addData("path state", pathState.toString());
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
       // telemetry.addData("Path Time", pathTimer.getElapsedTimeSeconds());
    }
}