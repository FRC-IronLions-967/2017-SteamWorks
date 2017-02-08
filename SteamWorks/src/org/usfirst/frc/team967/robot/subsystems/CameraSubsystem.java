package org.usfirst.frc.team967.robot.subsystems;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 */
public class CameraSubsystem extends Subsystem {
	private DigitalInput button;
	public boolean autoButton;
	
	public CameraSubsystem(){
		button = new DigitalInput(0);
	}
	public boolean AutoButtonValue(){
		autoButton = button.get();
		return autoButton;
	}
	public void log(){
		SmartDashboard.putBoolean("Preset Button", autoButton);
		autoButton = button.get();
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

