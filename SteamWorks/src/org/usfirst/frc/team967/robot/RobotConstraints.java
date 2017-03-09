package org.usfirst.frc.team967.robot;

public class RobotConstraints {
	
	/*** DRIVETRAIN Constraints ***/
	public static final double DriveSubsystem_deadBand = .15;
	//now that input is squared, this could probably come down
	
	/*** Shooter Constraints ***/
	public static int ShooterSubsystem_Shooter_Profile;
	public static final double ShooterSubsystem_Shooter_P = .4;
	public static final double ShooterSubsystem_Shooter_I = 0;
	public static final double ShooterSubsystem_Shooter_D = .3;
	public static final double ShooterSubsystem_Shooter_F = .014;
	public static final int ShooterSubsystem_Shooter_Izone = 8525;
	public static final double ShooterSubsystem_Shooter_CloseLoopRampRate = 0;
	public static final int ShooterSubsystem_Shooter_profile = 0;
	
	public static final int ShooterSubsystem_ShooterSpeed = 435;//1300
	
	/*** Climber Constraints ***/
	public static double ClimberSubsystem_ClimberSpeed = 1;
	
	/*** Intake Constraints ***/
	public static double IntakeSubsystem_IntakeSpeed = 1;
	
	/*** Gear Constraints ***/
	
	public RobotConstraints() {
	
	}
}