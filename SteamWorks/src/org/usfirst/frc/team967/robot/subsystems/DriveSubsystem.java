package org.usfirst.frc.team967.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon;

import org.usfirst.frc.team967.robot.commands.TeleOp_ArcadeDrive;

public class DriveSubsystem extends Subsystem {
	  
	public CANTalon driveLeftLead;
	public CANTalon driveLeftFollow;
	public CANTalon driveLeftFollow1;
	public CANTalon driveRightLead;
	public CANTalon driveRightFollow;
	public CANTalon driveRightFollow1;
	
	public DoubleSolenoid shifter;
	
	private final double deadBand = .2;
	public boolean InHighGear;	
	
	public void driveSubsystem(){
		driveLeftLead = new CANTalon(30);    // The left drive lead motor
		driveLeftFollow = new CANTalon(31);  // The left drive follow motor
		driveLeftFollow1 = new CANTalon(33);
		driveRightLead = new CANTalon(34);   // The right drive lead motor
		driveRightFollow = new CANTalon(35);
		driveRightFollow1 = new CANTalon(36);// The right drive follow motor
		
		shifter = new DoubleSolenoid(0, 2, 1); // The shifter for high-low gear. (CAN bus ID, On port, Off port)
	}
	
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

