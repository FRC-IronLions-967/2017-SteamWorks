package org.usfirst.frc.team967.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team967.robot.RobotConstraints;
import org.usfirst.frc.team967.robot.RobotMap;
import org.usfirst.frc.team967.robot.commands.ArcadeDrive;

public class DriveSubsystem extends Subsystem {
	 
	private final DoubleSolenoid shifter = RobotMap.shifter; 
	private final CANTalon driveLeftLead = RobotMap.driveLeftLead;
	private final CANTalon driveLeftFollow = RobotMap.driveLeftFollow;
	private final CANTalon driveRightLead = RobotMap.driveRightLead;
	private final CANTalon driveRightFollow = RobotMap.driveRightFollow;
	
	public boolean InHighGear;
	
	public void arcadeDrive(double yAxis, double xAxis) {
    	double deadband = .2;
    	if((yAxis< deadband) && (yAxis > -deadband)){ yAxis=0;}
    	if((xAxis< deadband) && (xAxis > -deadband)){ xAxis=0;}
    	
    	double L = yAxis + xAxis;
    	double R = yAxis - xAxis;
    	double max = Math.abs(L);
    	if(Math.abs(R) > max) max = Math.abs(R);
    	if((Math.abs(yAxis) <= 1) && (Math.abs(xAxis) <= 1) && (max < 1)){
    		move(L,R);
    	}else
    		move(L/max, R/max);
    	SmartDashboard.putNumber("R", R);
    	SmartDashboard.putNumber("L", L);
    	SmartDashboard.putNumber("R/max", R/max);
    	SmartDashboard.putNumber("L/max", L/max);
    }
	
	public void move(double left, double right){
		driveLeftLead.set(left);
    	driveLeftFollow.set(left);
    	driveRightLead.set(-right);
    	driveRightFollow.set(-right);
    }
	
	 public void shiftLow() {
	    	InHighGear = false;
	        shifter.set(DoubleSolenoid.Value.kReverse);
	    }
	    public void shiftHigh() {
	    	InHighGear = true;
	    	shifter.set(DoubleSolenoid.Value.kForward);
	    }

    public void initDefaultCommand() {
    	setDefaultCommand(new ArcadeDrive());
    }
    
}

