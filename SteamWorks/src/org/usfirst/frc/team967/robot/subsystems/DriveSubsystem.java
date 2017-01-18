package org.usfirst.frc.team967.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team967.robot.RobotConstraints;
import org.usfirst.frc.team967.robot.RobotMap;
import org.usfirst.frc.team967.robot.commands.TeleOp_ArcadeDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class DriveSubsystem extends Subsystem {
	 
	private final DoubleSolenoid shifter = RobotMap.shifter; 
	private final CANTalon driveLeftLead = RobotMap.driveLeftLead;
	private final CANTalon driveLeftFollow = RobotMap.driveLeftFollow;
	private final CANTalon driveRightLead = RobotMap.driveRightLead;
	private final CANTalon driveRightFollow = RobotMap.driveRightFollow;
	
	private final double deadBand = RobotConstraints.DriveSubsystem_deadBand;
	
	public boolean InHighGear;
	
	public void arcadeDrive(double yAxis, double xAxis) {
		driveLeftLead.changeControlMode(TalonControlMode.PercentVbus);
		driveLeftFollow.changeControlMode(TalonControlMode.PercentVbus);
		driveRightLead.changeControlMode(TalonControlMode.PercentVbus);
		driveRightFollow.changeControlMode(TalonControlMode.PercentVbus);
		
		if((yAxis< deadBand) && (yAxis > -deadBand)){ yAxis=0;}
    	if((xAxis< deadBand) && (xAxis > -deadBand)){ xAxis=0;}
    	
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
		driveLeftFollow.setSetpoint(left);
    	driveRightLead.setSetpoint(-right);
    	driveRightFollow.setSetpoint(-right);
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
    	setDefaultCommand(new TeleOp_ArcadeDrive());
    }
    public void log(){
        
    }
    
}

