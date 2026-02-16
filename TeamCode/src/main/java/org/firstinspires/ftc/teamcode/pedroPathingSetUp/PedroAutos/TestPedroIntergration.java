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

    private final Pose startPose = new Pose(24.700, 130.054, Math.toRadians(140));//DONT CHANGE
    private final Pose shootPose = new Pose(51.585, 92.247, Math.toRadians(140));
    private final Pose endPose = new Pose(29.909, 72.084, Math.toRadians(150));

    public void buildPaths() {
        driveStartPosShootPos = follower.pathBuilder()
                .addPath(new BezierLine(startPose, shootPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), shootPose.getHeading())
                .build();
        driveShootPosEndPos = follower.pathBuilder()
                .addPath(new BezierLine(shootPose, endPose))
                .setLinearHeadingInterpolation(shootPose.getHeading(),endPose.getHeading())
                .build();


    }


    public void statePathUpdate() {
       follower.followPath(driveStartPosShootPos);
        follower.followPath(driveShootPosEndPos);
    }
}
