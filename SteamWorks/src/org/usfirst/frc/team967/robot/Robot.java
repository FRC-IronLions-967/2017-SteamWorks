package org.usfirst.frc.team967.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team967.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team967.robot.commands.AutoDriveGyro;
import org.usfirst.frc.team967.robot.commands.PIDTurnToAngle;
import org.usfirst.frc.team967.robot.commands.auto.*;
import org.usfirst.frc.team967.robot.subsystems.CameraSubsystem;
import org.usfirst.frc.team967.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team967.robot.subsystems.GearSubsystem;
import org.usfirst.frc.team967.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team967.robot.subsystems.ShooterSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {	
	public static final CameraSubsystem cameraSubsystem = new CameraSubsystem();	
	public static RobotMap robotMap;
	public static RobotConstraints robotConstraints;
	public static final DriveSubsystem driveSubsystem = new DriveSubsystem();
	public static final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	public static final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	public static final GearSubsystem gearSubsystem = new GearSubsystem();
	public static final ClimberSubsystem climberSubsystem = new ClimberSubsystem();
	public static OI oi;
	
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		robotMap = new RobotMap();
    	robotConstraints = new RobotConstraints();
		oi = new OI();
		CameraServer.getInstance().startAutomaticCapture();
		chooser.addObject("ShooterBlueLeft", new blueLeftShoot());
		
		chooser.addDefault("Drive Forward", new driveBaseline());
		chooser.addObject("LeftBlue", new blueLeftGear());
		chooser.addObject("RightBlue", new blueRightGear());
		chooser.addObject("CenterBlue", new blueCenterGear());
		chooser.addObject("LeftRed", new redLeftGear());
		chooser.addObject("RightRed", new redRightGear());
		chooser.addObject("CenterRed", new redCenterGear());
		SmartDashboard.putData("Auto mode", chooser);
	}
	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Robot.driveSubsystem.pidStop();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		driveSubsystem.zeroEncoders();
		driveSubsystem.shiftLow();
		gearSubsystem.gearBoxClosed();
		shooterSubsystem.StopShooter();
		//		intakeSubsystem.shiftUpperOut();
		autonomousCommand = chooser.getSelected();

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		driveSubsystem.shiftLow();
		driveSubsystem.zeroEncoders();
		shooterSubsystem.StopShooter();
		//intakeSubsystem.shiftLowerOut();
		//intakeSubsystem.shiftUpperOut();
		//gearSubsystem.gearBoxClosed();
		log();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	public void log(){
    	SmartDashboard.putData(shooterSubsystem);
    	SmartDashboard.putData(climberSubsystem);
    	SmartDashboard.putData(intakeSubsystem);
    	SmartDashboard.putData(gearSubsystem);
    	SmartDashboard.putData(driveSubsystem);
    	SmartDashboard.putNumber("Shooter Speed", Robot.shooterSubsystem.shooterLead.getSpeed());
    	SmartDashboard.putData(Scheduler.getInstance());
    	oi.log();
    	driveSubsystem.log();
    	intakeSubsystem.log();
    	shooterSubsystem.log();
    	gearSubsystem.log();
    	climberSubsystem.log();
    	cameraSubsystem.log();
	}
}