package org.firstinspires.ftc.teamcode.pedroPathingSetUp.PedroAutos;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


import org.firstinspires.ftc.teamcode.pedroPathingSetUp.AutoPositions;
import org.firstinspires.ftc.teamcode.pedroPathingSetUp.PathState;
import org.firstinspires.ftc.teamcode.pedroPathingSetUp.PedroBase;

@Autonomous(name="Test Intergrations", group="Robot")
public class TestPedroIntergration extends PedroBase {
    private PathChain driveStartPosShootPos, driveShootPosEndPos;


    public void buildPaths() {
        driveStartPosShootPos = follower.pathBuilder()
                .addPath(new BezierLine(AutoPositions.STARTPOSEBIGTRIANGLEBLUE.getPos(), AutoPositions.SHOOTPOSEBLUETEAMBIGTRIANGLE.getPos()))
                .setLinearHeadingInterpolation(AutoPositions.STARTPOSEBIGTRIANGLEBLUE.getHeading(), AutoPositions.SHOOTPOSEBLUETEAMBIGTRIANGLE.getHeading())
                .build();
        driveShootPosEndPos = follower.pathBuilder()
                .addPath(new BezierLine(AutoPositions.SHOOTPOSEBLUETEAMBIGTRIANGLE.getPos(), AutoPositions.ENDPOSEBLUE.getPos()))
                .setLinearHeadingInterpolation(AutoPositions.SHOOTPOSEBLUETEAMBIGTRIANGLE.getHeading(),AutoPositions.ENDPOSEBLUE.getHeading())
                .build();


    }


    public void statePathUpdate() {
       follower.followPath(driveStartPosShootPos);
        follower.followPath(driveShootPosEndPos);
    }
}
