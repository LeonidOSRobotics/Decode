package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.robotSystems.*;

/*
 * This class is the “hub” that wires everything together for an OpMode.
 * It holds one instance of RobotHardware and creates all subsystems using it.
 */


public class Robot {
    RobotHardware hardware;
    VisionSubsystem vision;
    DriveSubsystem driveTrain;
    LEDSubsystem LED;
    ImuSubsystem imu;



    public void initRobot(HardwareMap hwMap){
        //Creates and initializes the shared hardware for the robot
        hardware = new RobotHardware();
        hardware.init(hwMap);

        //Initializes the individual subsystems for the robot
        vision = new VisionSubsystem(hardware);
        imu = new ImuSubsystem(hardware);
        driveTrain = new DriveSubsystem(hardware, vision, imu);
        LED = new LEDSubsystem(hardware);
    }
}
