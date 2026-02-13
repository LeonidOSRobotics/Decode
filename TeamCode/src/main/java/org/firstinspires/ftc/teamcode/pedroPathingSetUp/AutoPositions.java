package org.firstinspires.ftc.teamcode.pedroPathingSetUp;

import com.pedropathing.geometry.Pose;

public enum AutoPositions {
    STARTPOSEBIGTRIANGLEBLUE(new Pose(21.003500583430572, 124.67677946324388, Math.toRadians(144))),
    SHOOTPOSEBLUETEAMBIGTRIANGLE(new Pose(71.91598599766631, 80.075845974326, Math.toRadians(144))),
    ENDPOSEBLUE(new Pose(71.91598599766625, 58.13768961493584, Math.toRadians(144)));



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
