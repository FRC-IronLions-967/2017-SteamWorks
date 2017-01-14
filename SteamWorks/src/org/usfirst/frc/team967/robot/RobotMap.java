package org.usfirst.frc.team967.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

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
	
	//----------------------------------------
	
	
	
	//-----------Climber---------------------
	
	//----------------------------------------
	
	
	//-----------Gears------------------------
	
	//----------------------------------------
	
	
	//---------Navigation--------------------
	
	//---------------------------------------
	
	
	
	public static void init(){
		
		//-------------Drive-------------
		driveLeftLead = new CANTalon(30);    // The left drive lead motor
		driveLeftFollow = new com.ctre.CANTalon(31);  // The left drive follow motor
		driveLeftFollow = new com.ctre.CANTalon(33);
		
		driveRightLead = new com.ctre.CANTalon(34);   // The right drive lead motor
		driveRightFollow = new com.ctre.CANTalon(35);
		driveRightFollow = new com.ctre.CANTalon(36);// The right drive follow motor
		
		shifter = new DoubleSolenoid(0, 0, 8); // The shifter for high-low gear. (CAN bus ID, On port, Off port)
		//------------------------------
		
		//-------------Shooter----------
	}
}