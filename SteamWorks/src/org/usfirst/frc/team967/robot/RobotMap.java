package org.usfirst.frc.team967.robot;

import edu.wpi.first.wpilibj.CANTalon;

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
	public static CANTalon driveRightLead;
	public static CANTalon driveRightFollow;
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
		driveLeftLead = new CANTalon(20);    // The left drive lead motor
		driveLeftFollow = new CANTalon(21);  // The left drive follow motor
		
		driveRightLead = new CANTalon(22);   // The right drive lead motor
		driveRightFollow = new CANTalon(23); // The right drive follow motor
		
	}
}