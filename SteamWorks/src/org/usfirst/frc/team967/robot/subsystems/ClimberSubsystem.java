package org.usfirst.frc.team967.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.*;

import org.usfirst.frc.team967.robot.RobotConstraints;
import org.usfirst.frc.team967.robot.RobotMap;
import org.usfirst.frc.team967.robot.commands.TeleOp_Climb;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;


/**
 *
 */
public class ClimberSubsystem extends Subsystem {
	
	public CANTalon climber1;
	public CANTalon climber2;
	
	private double ClimberSpeed = .5;
    
	public ClimberSubsystem(){
		climber2 = new CANTalon(40);
		climber1 = new CANTalon(41);
		climber2.changeControlMode(TalonControlMode.Follower);
		climber2.set(climber1.getDeviceID());
	
	}
	
	public void climb(){
		climber1.set(ClimberSpeed); 
	}
 
    public void initDefaultCommand() {
        //setDefaultCommand(new TeleOp_Climb());
    }
    public void log(){
    //	SmartDashboard.putNumber("ClimberSpeed", ClimberSpeed);
    }
}

