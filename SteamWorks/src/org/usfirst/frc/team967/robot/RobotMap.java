package org.usfirst.frc.team967.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	/**-----------General---------------------**/
	public static final int PCM = 1;
	public static final int PCM2 = 2;
	
	/**------------Drive----------------------**/
	public static final int driveLeftLead = 1;
	public static final int driveLeftFollow = 2;
//	public static final int driveLeftFollow1 = 33;
	public static final int driveRightLead = 3;
	public static final int driveRightFollow = 4;
//	public static final int driveRightFollow1 = 36;
	
	public static final int driveShifterHigh = 6;
	public static final int driveShifterLow = 1;//1 &6
	//----------------------------------------
	
	/**-----------Shooter----------------------**/
	public static final int shooterFeed = 6;
	public static final int shooterLead = 7;
	public static final int shooterFollow = 8;
	//----------------------------------------
	
	/**----------Intake-----------------------**/
	public static final int intake = 5;
	public static final int lowerArmLead = 11;
	
	
	public static final int intakeUpperOut = 2;
	public static final int intakeUpperIn = 5;
	public static final int intakeLowerOut = 3;
	public static final int intakeLowerIn = 4;
	//----------------------------------------
	
	/**----------Climber----------------------**/
	public static final int climberLead = 9;
	public static final int climberFollow = 10;
	//----------------------------------------
	
	/**----------Gears------------------------**/
	public static final int gearClosed = 0;
	public static final int gearOpen = 7;
	public static final int topClosed = 3;
	public static final int topOpen = 4;
	
	//----------------------------------------
	
	/**--------Camera---------------------**/
	
	//---------------------------------------
	
	public RobotMap(){
		
	}
}
