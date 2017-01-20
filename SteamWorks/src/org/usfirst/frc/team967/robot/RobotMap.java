package org.usfirst.frc.team967.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.CANTalon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//------------Drive----------------------
	public static CANTalon driveLeftLead;
	public static CANTalon driveLeftFollow;
	public static CANTalon driveLeftFollow1;
	public static CANTalon driveRightLead;
	public static CANTalon driveRightFollow;
	public static CANTalon driveRightFollow1;
	
	public static DoubleSolenoid shifter;
	//----------------------------------------
	
	
	//-----------Shooter----------------------
	public static CANTalon shooterLead;
	public static CANTalon shooterFollow;
	//----------------------------------------
	
	
	
	//-----------Climber---------------------
	public static CANTalon climber1;
	public static CANTalon climber2;
	//----------------------------------------
	
	
	//-----------Gears------------------------
	
	//----------------------------------------
	
	
	//---------Navigation--------------------
	
	//---------------------------------------
	
	
	public RobotMap(){
		SmartDashboard.putNumber("test", 5);
		
		//-------------Drive-------------
//		driveLeftLead = new CANTalon(30);    // The left drive lead motor
//		driveLeftFollow = new CANTalon(31);  // The left drive follow motor
//		driveLeftFollow = new CANTalon(33);
//		driveRightLead = new CANTalon(34);   // The right drive lead motor
//		driveRightFollow = new CANTalon(35);
//		driveRightFollow = new CANTalon(36);// The right drive follow motor
//		
//		shifter = new DoubleSolenoid(0, 0, 1); // The shifter for high-low gear. (CAN bus ID, On port, Off port)
		//------------------------------
		
		//-------------Shooter----------
//		shooterLead = new CANTalon(45);
//		shooterFollow = new CANTalon(46);
		//------------------------------
		
		//-----------Climber---------------------
//		climber1 = new CANTalon(40);		// The First climber motor 
//		climber2 = new CANTalon(41);		// The Second Climber Motor
		//----------------------------------------
		
	}
}