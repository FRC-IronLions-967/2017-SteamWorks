package org.usfirst.frc.team967.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//------------Drive----------------------
	public static final int driveLeftLead = 30;
	public static final int driveLeftFollow = 31;
	public static final int driveLeftFollow1 = 33;
	public static final int driveRightLead = 34;
	public static final int driveRightFollow = 35;
	public static final int driveRightFollow1 = 36;
	
	public static final int driveShifterHigh = 0;
	public static final int driveShifterLow = 7;
	//----------------------------------------
	
	
	//-----------Shooter----------------------
	public static final int shooterLead = 6;
	public static final int shooterFollow = 5;
	//----------------------------------------
	
	
	
	//-----------Climber---------------------
	public static final int climberLead = 41;
	public static final int climberFollow = 40;
	//----------------------------------------
	
	
	//-----------Gears------------------------
	public static final int gearShifterHigh = 1;
	public static final int gearShifterLow = 6;
	//----------------------------------------
	
	
	//---------Navigation--------------------
	
	//---------------------------------------
	
	
	public RobotMap(){
		
	}
}