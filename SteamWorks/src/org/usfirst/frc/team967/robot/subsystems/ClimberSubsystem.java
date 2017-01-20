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
	
	private CANTalon climber1 = RobotMap.climber1;
	private CANTalon climber2 = RobotMap.climber2;
	
	private final double ClimberSpeed = RobotConstraints.ClimberSubsystem_ClimberSpeed;
    
	public void climb(){
		climber2 = new CANTalon(40);
		climber1 = new CANTalon(41);
		climber2.changeControlMode(TalonControlMode.Follower);
		climber2.setSetpoint(climber1.getDeviceID());
		climber1.setSetpoint(ClimberSpeed); 
	}
 
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
//        setDefaultCommand(new TeleOp_Climb());
    }
    public void log(){
    	SmartDashboard.putNumber("ClimberSpeed", ClimberSpeed);
    }
}

