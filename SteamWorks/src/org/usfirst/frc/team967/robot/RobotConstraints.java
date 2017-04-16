package org.usfirst.frc.team967.robot;

public class RobotConstraints {
	/*** Auto Constraints ***/
//	public static final double Auto_Speed = .5;
	public static final double Auto_Speed_Half = .5;
	public static final double Auto_Speed_Fast = .75;
	public static final double Auto_Speed_Full = 1;
	public static final double Auto_Angle_AirShip = 60;
	
	/*** DRIVETRAIN Constraints ***/
	public static final double DriveSubsystem_deadBand = .15;
	public static final double DriveSubsystem_P = .018;
	public static final double DriveSubsystem_I = .00;
	public static final double DriveSubsystem_D = .02;
	public static final double DriveSubsystem_Tolerance = 1.0f;
	
	/*** Shooter Constraints ***/
	public static int ShooterSubsystem_Shooter_Profile;
	public static final double ShooterSubsystem_Shooter_P = .2;//.15
	public static final double ShooterSubsystem_Shooter_I = 0;
	public static final double ShooterSubsystem_Shooter_D = .3;
	public static final double ShooterSubsystem_Shooter_F = .014;
	public static final int ShooterSubsystem_Shooter_Izone = 8525;
	public static final double ShooterSubsystem_Shooter_CloseLoopRampRate = 0;
	public static final int ShooterSubsystem_Shooter_profile = 0;
	
	public static final double ShooterSubsystem_Feeder_P = .2;//.15
	public static final double ShooterSubsystem_Feeder_I = 0;
	public static final double ShooterSubsystem_Feeder_D = .3;
	public static final double ShooterSubsystem_Feeder_F = .014;
	public static final int ShooterSubsystem_Feeder_Izone = 8525;
	public static final double ShooterSubsystem_Feeder_CloseLoopRampRate = 0;
	public static final int ShooterSubsystem_Feeder_profile = 0;
	
	public static final int ShooterSubsystem_FeederSpeed = 600;//245
	
	public static final int ShooterSubsystem_ShooterSpeed = 800;//770 good speed//800
	//790 fly wheel with .6 feed speed
	/*** Climber Constraints ***/
	public static double ClimberSubsystem_ClimberSpeed = 1;
	
	/*** Intake Constraints ***/
	public static double IntakeSubsystem_IntakeSpeed = 1;
	public static double IntakeSubsystem_LowArmPeg = 90;
	
	
	/*** Gear Constraints ***/
	
	public RobotConstraints() {
	
	}
}