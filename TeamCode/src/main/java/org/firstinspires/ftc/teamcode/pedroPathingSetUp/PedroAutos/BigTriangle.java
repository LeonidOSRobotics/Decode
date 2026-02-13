package org.firstinspires.ftc.teamcode.pedroPathingSetUp.PedroAutos;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.pedroPathingSetUp.PedroBase;

public class BigTriangle extends PedroBase {
    Robot robot = new Robot();
    @Override
    public void buildPaths() {

    }

    @Override
    public void statePathUpdate() {
        robot.shooter.setVelocity(4200);
        //Drive Away from goal to shooting position
        while(!robot.shooter.isUpToSpeed(4200)){
            robot.shooter.setVelocity(4200);
        }
        //Launch 3 balls (for loop, lift arm, lower arm, spin pinwheel and repeat)
        //Park
    }
}
