package org.firstinspires.ftc.teamcode.pedroPathingSetUp;

import com.pedropathing.geometry.Pose;

public enum AutoPositions {
    STARTPOSE(new Pose(21.003500583430576, 124.58, Math.toRadians(180))),
    SHOOTPOSE(new Pose(40, 80.075845974326, Math.toRadians(180))),
    ENDPOSE(new Pose(84.17736289381563, 94.78296382730456, Math.toRadians(45)));



    private final Pose pos;

    AutoPositions(Pose pos) {
        this.pos = pos;
    }

    public Pose getPos() {
        return pos;
    }

    public double getHeading(){
        return pos.getHeading();
    }
}
