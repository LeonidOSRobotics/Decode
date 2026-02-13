package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robotSystems.*;

/*
 * This class is the “hub” that wires everything together for an OpMode.
 * It holds one instance of RobotHardware and creates all subsystems using it.
 */


public class Robot {
    public RobotHardware hardware;
    public VisionSubsystem vision;
    public DriveSubsystem driveTrain;
    public LEDSubsystem LED;
    public ImuSubsystem imu;
    public ShooterSubsystem shooter;
    public AutoManager autoManager;
    public IntakeSubsystem intake;
    public PinwheelSubsystem pinwheel;



    public void initRobot(HardwareMap hwMap){
        //Creates and initializes the shared hardware for the robot
        hardware = new RobotHardware();
        hardware.init(hwMap);

        //Initializes the individual subsystems for the robot
        vision = new VisionSubsystem(hardware);
        imu = new ImuSubsystem(hardware);
        driveTrain = new DriveSubsystem(hardware, vision, imu);
        LED = new LEDSubsystem(hardware);
        shooter = new ShooterSubsystem(hardware);
        autoManager = new AutoManager(driveTrain, hardware, vision, imu);
        intake = new IntakeSubsystem(hardware);
        pinwheel = new PinwheelSubsystem(hardware);

    }
}
