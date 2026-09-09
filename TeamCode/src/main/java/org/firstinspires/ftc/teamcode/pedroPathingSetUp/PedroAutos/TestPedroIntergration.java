package org.firstinspires.ftc.teamcode.pedroPathingSetUp.PedroAutos;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


import org.firstinspires.ftc.teamcode.pedroPathingSetUp.AutoPositions;
import org.firstinspires.ftc.teamcode.pedroPathingSetUp.PathState;
import org.firstinspires.ftc.teamcode.pedroPathingSetUp.PedroBase;

@Autonomous(name="Test Intergrations", group="Robot")
public class TestPedroIntergration extends PedroBase {
    private PathChain driveStartPosShootPos, driveShootPosEndPos;

    private final Pose startPose = new Pose(32.215, 56.446, Math.toRadians(140));//DONT CHANGE
    //private final Pose shootPose = new Pose(51.585, 92.247, Math.toRadians(140));
    private final Pose endPose = new Pose(111.58265186915888, 56.18691588785045084, Math.toRadians(150));

    public void buildPaths() {
        driveStartPosShootPos = follower.pathBuilder()
                .addPath(new BezierLine(startPose, endPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), endPose.getHeading())
                .build();
        driveShootPosEndPos = follower.pathBuilder()
                .addPath(new BezierLine(startPose, endPose))
                .setLinearHeadingInterpolation(endPose.getHeading(),endPose.getHeading())
                .build();


    }


    public void statePathUpdate() {
       follower.followPath(driveStartPosShootPos);
        follower.followPath(driveShootPosEndPos);
    }
}
