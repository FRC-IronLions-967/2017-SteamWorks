package org.usfirst.frc.team967.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team967.robot.RobotMap;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;


/**
 * Ready to test on the real robot
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
		climberFollow.reverseOutput(true);
	}
	
	public void climb(double speed){
		climberLead.set(-speed); 
	}
 
    public void initDefaultCommand() {
    //    setDefaultCommand(new command);
    }
    public void log(){
    /*	SmartDashboard.putNumber("Climber Current lead", climberLead.getOutputCurrent());
    	SmartDashboard.putNumber("Climber Current follow", climberFollow.getOutputCurrent());
    */
    }
}

