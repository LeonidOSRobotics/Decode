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
    private PathChain path1;

    private final Pose startPose = new Pose(31.226, 8.476, Math.toRadians(90));

    private final Pose endPose = new Pose(31.226, 36.953, Math.toRadians(90));

    public void buildPaths() {
        path1 = follower.pathBuilder()
                .addPath(new BezierLine(startPose, endPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), endPose.getHeading())
                .build();

    }


    public void statePathUpdate() {
        follower.followPath(path1);
    }
}
