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
	
	public static final int driveShifterHigh = 6;//1
	public static final int driveShifterLow = 1;//6
	//----------------------------------------
	
	/**-----------Shooter----------------------**/
	public static final int shooterFeed = 6;
	public static final int shooterLead = 7;
	public static final int shooterFollow = 8;
	//----------------------------------------
	
	/**----------Intake-----------------------**/
	public static final int intakeWheelLead = 5;
	public static final int intakeLowerArmLead = 11;
	
	public static final int intakeShifterUpperOut = 2;
	public static final int intakeShifterUpperIn = 5;
	public static final int intakeLimitSwitch = 0;
	public static final int intakeLowerOut = 3;
	public static final int intakeLowerIn = 4;
	//----------------------------------------
	
	/**----------Climber----------------------**/
	public static final int climberLead = 9;
	public static final int climberFollow = 10;
	//----------------------------------------
	
	/**----------Gears------------------------**/
	public static final int gearShifterClosed = 0;
	public static final int gearShifterOpen = 7;
	public static final int gearShifterTopClosed = 3;
	public static final int gearShifterTopOpen = 4;
	//----------------------------------------
	
	/**--------Camera---------------------**/
	public static final int cameraRelay = 0;
	public static final int cameraLeftInput = 3;
	public static final int cameraRightInput = 1;
	
	//---------------------------------------
	
	public RobotMap(){
		
	}
}
