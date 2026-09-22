package org.firstinspires.ftc.teamcode.pedroPathing.setUp;

import com.pedropathing.geometry.Pose;

public enum AutoPositions {
    STARTPOSEBIGTRIANGLEBLUE(new Pose(21.003500583430572, 124.67677946324388, Math.toRadians(144))),
    ENDPOSESMALLTRIANGLERED(new Pose(0.00, 0.00, Math.toRadians(144)));

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
