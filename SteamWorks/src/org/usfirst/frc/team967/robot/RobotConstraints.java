package org.usfirst.frc.team967.robot;

public class RobotConstraints {
	
	/*** DRIVETRAIN Constraints ***/
	public static final double DriveSubsystem_deadBand = .2;
	
	/*** Shooter Constraints ***/
	public static int ShooterSubsystem_Shooter_Profile;
	public static final double ShooterSubsystem_Shooter_P = .12;
	public static final double ShooterSubsystem_Shooter_I = 0;
	public static final double ShooterSubsystem_Shooter_D = .5;
	public static final double ShooterSubsystem_Shooter_F = .014;
	public static final int ShooterSubsystem_Shooter_Izone = 8525;
	public static final double ShooterSubsystem_Shooter_CloseLoopRampRate = 0;
	public static final int ShooterSubsystem_Shooter_profile = 0;
	
	public static final int ShooterSubsystem_ShooterSpeed = 650;//will change (450 for 1 to 1)
	
	/*** Climber Constraints ***/
//	public static double ClimberSubsystem_ClimberSpeed = .5;
	
	
	/*** Gear Constraints ***/
	
	public RobotConstraints() {

		
	}
}