package org.usfirst.frc.team967.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team967.robot.RobotMap;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;


/**
 *
 */
public class ClimberSubsystem extends Subsystem {
	
	public CANTalon climberLead;
	public CANTalon climberFollow;
    
	public ClimberSubsystem(){
		climberFollow = new CANTalon(RobotMap.climberFollow);
		climberLead = new CANTalon(RobotMap.climberLead);
		climberLead.changeControlMode(TalonControlMode.PercentVbus);
		climberFollow.changeControlMode(TalonControlMode.Follower);
		climberFollow.set(climberLead.getDeviceID());
	}
	
	public void climb(double speed){
		climberLead.set(speed); 
	}
 
    public void initDefaultCommand() {
    //    setDefaultCommand(new command);
    }
    public void log(){
    //	SmartDashboard.putNumber("ClimberSpeed", ClimberSpeed);
    }
}

