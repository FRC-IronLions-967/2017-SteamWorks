package org.usfirst.frc.team967.robot.subsystems;

import org.usfirst.frc.team967.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *work in progress 
 */
public class CameraSubsystem extends Subsystem {
	private boolean rearCamera;
	public boolean TurnLeft;
	public boolean TurnRight;
	
	private DigitalInput left, right;
	private Relay light;
	
	public CameraSubsystem(){
		light = new Relay(RobotMap.cameraRelay);
		rearCamera = true;
		left = new DigitalInput(RobotMap.cameraLeftInput);
		right = new DigitalInput(RobotMap.cameraRightInput);		
	}
	
	public void lightOn(){
		light.set(Relay.Value.kOn);
	}
	public void lightOff(){
		light.set(Relay.Value.kOff);
	}
	public void toggleCamera(){
		rearCamera = !rearCamera;
	}
	public boolean getCamera(){
		return rearCamera;
	}
	public boolean checkCamera(){
		if(!TurnLeft && !TurnRight){
			return true;
		}
		else{
			return false;
		}
	}
	public void updatePi(){
		TurnLeft = left.get();
		TurnRight = right.get();
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }
	public void log(){
		updatePi();
		SmartDashboard.putBoolean("Camera Left 1", TurnLeft);
		SmartDashboard.putBoolean("Camera Right 3", TurnRight);
//		SmartDashboard.putBoolean("Rear Camera", rearCamera);
	}

}

