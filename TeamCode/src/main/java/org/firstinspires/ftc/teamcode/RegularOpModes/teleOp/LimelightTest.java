package org.firstinspires.ftc.teamcode.RegularOpModes.teleOp;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.Comparator;
import java.util.List;

@TeleOp(name="Omni Drive To AprilTag (LL3A)", group="Concept")
@SuppressWarnings("unused")
public class LimelightTest extends LinearOpMode {

    private static final double DESIRED_DISTANCE = 50.0;
    private static final double SPEED_GAIN  = 0.02;
    private static final double STRAFE_GAIN = 0.1;
    private static final double TURN_GAIN   = 0.05;

    private static final double MAX_AUTO_SPEED  = 0.5;
    private static final double MAX_AUTO_STRAFE = 0.5;
    private static final double MAX_AUTO_TURN   = 0.3;

    private static final int DESIRED_TAG_ID = 20;
    private static final double M_TO_IN = 39.3701;

    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor backLeftDrive;
    private DcMotor backRightDrive;

    private Robot robot;

    private AprilTagProcessor aprilTag;



    @Override
    public void runOpMode() {
        double drive;
        double strafe;
        double turn;

        frontLeftDrive  = hardwareMap.get(DcMotor.class, "leftFront");
        frontRightDrive = hardwareMap.get(DcMotor.class, "rightFront");
        backLeftDrive   = hardwareMap.get(DcMotor.class, "leftBack");
        backRightDrive  = hardwareMap.get(DcMotor.class, "rightBack");


        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        Limelight3A limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);
        limelight.start();

        telemetry.setMsTransmissionInterval(11);
        telemetry.addData("Camera preview on/off", "3 dots, Camera Stream");
        telemetry.addData(">", "Touch START to start OpMode");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            LLStatus status = limelight.getStatus();
            telemetry.addData("LL", "Temp %.1fC CPU %.1f%% FPS %d Pipe %d/%s",
                    status.getTemp(), status.getCpu(), (int) status.getFps(),
                    status.getPipelineIndex(), status.getPipelineType());

            LLResult result = limelight.getLatestResult();
            if (result != null && result.isValid()) {
                double totalLatency = result.getCaptureLatency() + result.getTargetingLatency();
                telemetry.addData("Latency(ms)", "cap+tar=%.1f parse=%.1f", totalLatency, result.getParseLatency());

                LLResultTypes.FiducialResult desired = selectDesiredTag(result.getFiducialResults());

                if (desired != null) {
                    double zMeters   = desired.getCameraPoseTargetSpace().getPosition().z;
                    double xMeters   = desired.getCameraPoseTargetSpace().getPosition().x;
                    double rangeIn   = zMeters * M_TO_IN;
                    double lateralIn = xMeters * M_TO_IN;
                    double rangeError   = rangeIn - DESIRED_DISTANCE;
                    double headingError = desired.getTargetXDegrees();

                    telemetry.addData("Found", "ID %d Range %.1f in Bearing %.0f deg Lat %.1f in",
                            desired.getFiducialId(), rangeIn, headingError, lateralIn);

                    if (gamepad1.left_bumper) {

                        //drive  = robot.calculateAutoDrive(rangeError);
                        //turn   = robot.calculateAutoTurn(headingError);
                        //strafe = robot.calculateAutoStrafe(lateralIn);

                        drive  = Range.clip(rangeError   * SPEED_GAIN,  -MAX_AUTO_SPEED,  MAX_AUTO_SPEED);
                        turn   = Range.clip(headingError * -TURN_GAIN,   -MAX_AUTO_TURN,   MAX_AUTO_TURN);
                        strafe = Range.clip(lateralIn    * STRAFE_GAIN, -MAX_AUTO_STRAFE, MAX_AUTO_STRAFE);
                        telemetry.addData("Auto", "Drive %5.2f, Strafe %5.2f, Turn %5.2f ", drive, strafe, turn);
                    } else {
                        drive  = -gamepad1.left_stick_y  / 2.0;
                        strafe = -gamepad1.left_stick_x  / 2.0;
                        turn   = -gamepad1.right_stick_x / 3.0;
                        telemetry.addData("Manual", "Drive %5.2f, Strafe %5.2f, Turn %5.2f ", drive, strafe, turn);
                    }
                } else {
                    telemetry.addData(">", "Drive using joysticks to find valid target");
                    drive  = -gamepad1.left_stick_y  / 2.0;
                    strafe = -gamepad1.left_stick_x  / 2.0;
                    turn   = -gamepad1.right_stick_x / 3.0;
                }
            } else {
                telemetry.addData("Limelight", "No data available");
                drive  = -gamepad1.left_stick_y  / 2.0;
                strafe = -gamepad1.left_stick_x  / 2.0;
                turn   = -gamepad1.right_stick_x / 3.0;
            }

            telemetry.update();
            moveRobot(drive, strafe, turn);
            sleep(10);
        }

        limelight.stop();
    }

    private LLResultTypes.FiducialResult selectDesiredTag(List<LLResultTypes.FiducialResult> list) {
        if (list == null || list.isEmpty()) return null;
        if (DESIRED_TAG_ID >= 0) {
            return list.stream()
                    .filter(fr -> fr.getFiducialId() == DESIRED_TAG_ID)
                    .min(Comparator.comparingDouble(fr -> Math.abs(fr.getCameraPoseTargetSpace().getPosition().z)))
                    .orElse(null);
        }
        return list.stream()
                .min(Comparator.comparingDouble(fr -> Math.abs(fr.getCameraPoseTargetSpace().getPosition().z)))
                .orElse(null);
    }

    public void moveRobot(double x, double y, double yaw) {
        double frontLeftPower  =  x - y - yaw;
        double frontRightPower =  x + y + yaw;
        double backLeftPower   =  x + y - yaw;
        double backRightPower  =  x - y + yaw;

        double max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
        max = Math.max(max, Math.abs(backLeftPower));
        max = Math.max(max, Math.abs(backRightPower));

        if (max > 1.0) {
            frontLeftPower  /= max;
            frontRightPower /= max;
            backLeftPower   /= max;
            backRightPower  /= max;
        }

        frontLeftDrive.setPower(frontLeftPower);
        frontRightDrive.setPower(frontRightPower);
        backLeftDrive.setPower(backLeftPower);
        backRightDrive.setPower(backRightPower);
    }
}
