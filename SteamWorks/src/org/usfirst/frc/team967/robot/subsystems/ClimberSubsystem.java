package org.usfirst.frc.team967.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team967.robot.RobotConstraints;
import org.usfirst.frc.team967.robot.RobotMap;

import com.ctre.CANTalon;

/**
 *
 */
public class ClimberSubsystem extends Subsystem {
	
	private final CANTalon climber1 = RobotMap.climber1;
	private final CANTalon climber2 = RobotMap.climber2;
    
	public void climb(){
		
	}
 
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void log(){
        
    }
}

