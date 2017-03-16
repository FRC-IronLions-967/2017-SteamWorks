package org.usfirst.frc.team967.robot.subsystems;


import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *work in progress 
 */
public class CameraSubsystem extends Subsystem {
	private boolean rearCamera;
	
	public CameraSubsystem(){
		rearCamera = true;
	}
	
	public void log(){
		SmartDashboard.putBoolean("Rear Camera", rearCamera);
	}
	public void toggleCamera(){
		rearCamera = !rearCamera;
	}
	public boolean getCamera(){
		return rearCamera;
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
}

