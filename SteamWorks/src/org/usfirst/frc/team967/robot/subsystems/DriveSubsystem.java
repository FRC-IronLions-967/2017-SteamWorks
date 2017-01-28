package org.usfirst.frc.team967.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon;

import org.usfirst.frc.team967.robot.RobotConstraints;
import org.usfirst.frc.team967.robot.RobotMap;
import org.usfirst.frc.team967.robot.commands.TeleOp_ArcadeDrive;

public class DriveSubsystem extends Subsystem {
	  
	public CANTalon driveLeftLead;
	public CANTalon driveLeftFollow;
	public CANTalon driveLeftFollow1;
	public CANTalon driveRightLead;
	public CANTalon driveRightFollow;
	public CANTalon driveRightFollow1;
	
	public DoubleSolenoid shifter;
	
	private final double deadBand = RobotConstraints.DriveSubsystem_deadBand;
	public boolean InHighGear;	
	
	public DriveSubsystem(){
		driveLeftLead = new CANTalon(RobotMap.driveLeftLead);    // The left drive lead motor
		driveLeftFollow = new CANTalon(RobotMap.driveLeftFollow);  // The left drive follow motor
		driveLeftFollow1 = new CANTalon(RobotMap.driveLeftFollow1);
		driveRightLead = new CANTalon(RobotMap.driveRightLead);   // The right drive lead motor
		driveRightFollow = new CANTalon(RobotMap.driveRightFollow);
		driveRightFollow1 = new CANTalon(RobotMap.driveRightFollow1);// The right drive follow motor
		
	//	shifter = new DoubleSolenoid(0, 0, 7); // The shifter for high-low gear. (CAN bus ID, On port, Off port)
		shifter = new DoubleSolenoid(RobotMap.driveShifterLow, RobotMap.driveShifterHigh); //defaults to module 0
		
		driveLeftLead.changeControlMode(TalonControlMode.PercentVbus);
		driveLeftFollow.changeControlMode(TalonControlMode.PercentVbus);
		driveRightLead.changeControlMode(TalonControlMode.PercentVbus);
		driveRightFollow.changeControlMode(TalonControlMode.PercentVbus);
		driveRightFollow1.changeControlMode(TalonControlMode.PercentVbus);
		driveLeftFollow1.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	public void arcadeDrive(double yAxis, double xAxis) {	
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
		driveLeftFollow.set(left);
		driveLeftFollow1.set(left);
    	driveRightLead.set(-right);
    	driveRightFollow.set(-right);
    	driveRightFollow1.set(-right);
    }
	
	public void shiftLow() {
	    InHighGear = false;
	    shifter.set(DoubleSolenoid.Value.kReverse);
	}
	/*
	 * 	exampleDouble.set(DoubleSolenoid.Value.kOff);
	 *	exampleDouble.set(DoubleSolenoid.Value.kForward);
	 *	exampleDouble.set(DoubleSolenoid.Value.kReverse);
	 */
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