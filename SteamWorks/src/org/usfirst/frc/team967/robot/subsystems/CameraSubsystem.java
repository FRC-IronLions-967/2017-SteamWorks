package org.usfirst.frc.team967.robot.subsystems;


import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.Timer;

/**
 *work in progress 
 */
public class CameraSubsystem extends Subsystem {
	private boolean rearCamera;
	private boolean timeDone;
	Timer timer = new Timer();
	
	
	public CameraSubsystem(){
		rearCamera = true;
	}
	
	public void toggleCamera(){
		rearCamera = !rearCamera;
	}
	public boolean getCamera(){
		return rearCamera;
	}
	public boolean delayTime(double time){
		timeDone = false;
		double loops = 1000*time;
		double i = 0;
		while( i <(loops)){
			i++;
		}
		timeDone = true;
		return true;
	}
	public boolean getTimeDone(){
		return timeDone;
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }

	public void log(){
		SmartDashboard.putBoolean("Time Done", timeDone);
		SmartDashboard.putBoolean("Rear Camera", rearCamera);
	}
}

