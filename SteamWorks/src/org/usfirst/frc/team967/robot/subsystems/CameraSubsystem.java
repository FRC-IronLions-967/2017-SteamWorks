package org.usfirst.frc.team967.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraSubsystem extends Subsystem {
	
	public CameraSubsystem(){
		CameraServer.getInstance().startAutomaticCapture();
	//	serverFront = CameraServer.getInstance().startAutomaticCapture();
    	//serverFront.setQuality(7);
    	//serverFront.startAutomaticCapture("cam0");
    	
		//CameraServer serverFront = CameraServer.getInstance();
		//CameraServer.getInstance().startAutomaticCapture();
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

