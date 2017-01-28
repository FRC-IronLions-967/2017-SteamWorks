
package org.usfirst.frc.team967.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team967.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team967.robot.commands.Auto_1;
import org.usfirst.frc.team967.robot.commands.Auto_2;
import org.usfirst.frc.team967.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team967.robot.subsystems.GearSubsystem;
import org.usfirst.frc.team967.robot.subsystems.NavxSubsystem;
import org.usfirst.frc.team967.robot.subsystems.ShooterSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	

//	public static RobotMap robotMap;
//	public static RobotConstraints robotConstraints;
	public static DriveSubsystem  driveSubsystem;
	public static ShooterSubsystem  shooterSubsystem;
	public static GearSubsystem  gearSubsystem;
	public static ClimberSubsystem  climberSubsystem;
	public static NavxSubsystem navxSubsystem;
	public static OI oi;
	
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		driveSubsystem = new DriveSubsystem();
		gearSubsystem = new GearSubsystem();
		climberSubsystem = new ClimberSubsystem();		
		shooterSubsystem = new ShooterSubsystem();
		navxSubsystem = new NavxSubsystem();
		oi = new OI();
		
		chooser.addDefault("Auto1", new Auto_1());
		chooser.addObject("Auto2", new Auto_2());
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
		  autonomousCommand = chooser.getSelected();
		  String autoSelected = SmartDashboard.getString("Auto Selector","Auto_1");
		  switch(autoSelected) {
		  case "Auto_2": autonomousCommand = new Auto_2();
		  break;
		  case "Auto_1":
		  default:
		  autonomousCommand = new Auto_1(); 
		  break; 
		  }
		 

		// schedule the autonomous command (example)
		if (autonomousCommand != null)autonomousCommand.start();
//		autonomousCommand = (Command) chooser.getSelected();
//        
//		String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
//		switch(autoSelected) {
//		case "Auto_2":
//			autonomousCommand = new Auto_2();
//			break;
//		case "Auto_1":
//		default:
//			autonomousCommand = new Auto_1();
//			break;
//		}
//    	
//    	// schedule the autonomous command (example)
//        if (autonomousCommand != null) autonomousCommand.start();
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
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
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
    	SmartDashboard.putData(gearSubsystem);
    	SmartDashboard.putData(driveSubsystem);
    	SmartDashboard.putData(Scheduler.getInstance());
    
//    	oi.log();
    	driveSubsystem.log();
//    	shooterSubsystem.log();
//    	gearSubsystem.log();
//    	climberSubsystem.log();
	}
}