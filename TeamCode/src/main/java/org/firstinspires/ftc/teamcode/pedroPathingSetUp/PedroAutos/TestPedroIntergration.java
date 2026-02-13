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
        switch(pathState){
            case DRIVE_STARTPOS_SHOOT_POS:
                follower.followPath(driveStartPosShootPos, true);
                setPathState(PathState.SHOOT_PRELOAD); //reset timer and make new state
                break;
            case SHOOT_PRELOAD:
                //is follower done its path
                // and check that .5 seconds has elapsed
                if(!follower.isBusy() && pathTimer.getElapsedTimeSeconds() > 0.5){
                    telemetry.addLine("Done Path 1");
                    follower.followPath(driveShootPosEndPos, true);
                    setPathState(PathState.DRIVE_SHOOTPOS_ENDPOS);
                    //transition to next state
                }
                break;
            case DRIVE_SHOOTPOS_ENDPOS:
                if(!follower.isBusy()){
                    telemetry.addLine("Done all Paths");
                }
            default:
                telemetry.addLine("No state command");
                break;
        }
    }
}
