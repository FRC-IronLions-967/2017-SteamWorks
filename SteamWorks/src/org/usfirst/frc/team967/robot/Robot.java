package org.usfirst.frc.team967.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team967.robot.subsystems.DriveSubsystem;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team967.robot.commands.Auto_Drive_Distance;
import org.usfirst.frc.team967.robot.commands.Auto_Straight_Drive;
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
	Thread visionThread;
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
		chooser.addDefault("Drive Forward", new driveBaseline());
		chooser.addObject("Test", new Auto_Straight_Drive(1000, .5));
//		chooser.addObject("Test", new Auto_Drive_Distance(1000, .5));
		
		chooser.addObject("ShooterBlueLeft", new blueLeftShoot());
		chooser.addObject("LeftBlue", new blueLeftGear());
		chooser.addObject("CenterBlue", new blueCenterGear());
		chooser.addObject("RightBlue", new blueRightGear());
		chooser.addObject("LeftRed", new redLeftGear());
		chooser.addObject("CenterRed", new redCenterGear());
		chooser.addObject("RightRed", new redRightGear());
		chooser.addObject("ShootRedRight", new redRightShoot());
		SmartDashboard.putData("Auto mode", chooser);
	/*	visionThread = new Thread(() -> {
			// Get the UsbCamera from CameraServer		
			UsbCamera RearCamera = CameraServer.getInstance().startAutomaticCapture(0);
			UsbCamera FrontCamera = CameraServer.getInstance().startAutomaticCapture(1);
			
			// Set the resolution
			RearCamera.setResolution(640, 480);//half to 320, 240 ????
			FrontCamera.setResolution(640, 480);
			
			// Get a CvSink. This will capture Mats from the camera
			CvSink rearSink = CameraServer.getInstance().getVideo(RearCamera);
			CvSink frontSink = CameraServer.getInstance().getVideo(FrontCamera);
			// Setup a CvSource. This will send images back to the Dashboard
			CvSource dashOutput = CameraServer.getInstance().putVideo("Rectangle", 640, 480);

			// Mats are very memory expensive. Lets reuse this Mat.
			Mat mat = new Mat();
			// This cannot be 'true'. The program will never exit if it is. This
			// lets the robot stop this thread when restarting robot code or
			// deploying.
			while (!Thread.interrupted()) {
				if(cameraSubsystem.rearCamera){
					// Tell the CvSink to grab a frame from the camera and put it
					// in the source mat.  If there is an error notify the output.
					if (rearSink.grabFrame(mat) == 0) {
						// Send the output the error.
						dashOutput.notifyError(rearSink.getError());
						// skip the rest of the current iteration
						continue;
					}
					// Put a rectangle on the image
					Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400),
							new Scalar(255, 255, 255), 5);
				}
				else{
					if (frontSink.grabFrame(mat) == 0) {
						// Send the output the error.
						dashOutput.notifyError(frontSink.getError());
						// skip the rest of the current iteration
						continue;
					}
				}
				// Give the output stream a new image to display
				dashOutput.putFrame(mat);
			}
//			if(cameraSubsystem.rearCamera){
//				dashOutput.putFrame(mat);
//			}
//			else{
//				dashOutput.putFrame(null);
//			}
			/*
//***********************************************************************
			// Mats are very memory expensive. Lets reuse this Mat.
			Mat mat = new Mat();

			// This cannot be 'true'. The program will never exit if it is. This
			// lets the robot stop this thread when restarting robot code or
			// deploying.
			while (!Thread.interrupted()) {
				// Tell the CvSink to grab a frame from the camera and put it
				// in the source mat.  If there is an error notify the output.
				if (rearSink.grabFrame(mat) == 0) {
					// Send the output the error.
					dashOutput.notifyError(rearSink.getError());
					// skip the rest of the current iteration
					continue;
				}
				// Put a rectangle on the image
				Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400),
						new Scalar(255, 255, 255), 5);
				// Give the output stream a new image to display
				dashOutput.putFrame(mat);
			}
		});*/
		/* this works*/
		visionThread = new Thread(() -> {
			// Get the UsbCamera from CameraServer
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			// Set the resolution
			camera.setResolution(320/2, 240/2);
/*
			// Get a CvSink. This will capture Mats from the camera
			CvSink cvSink = CameraServer.getInstance().getVideo();
			// Setup a CvSource. This will send images back to the Dashboard
			CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 320, 240);

			// Mats are very memory expensive. Lets reuse this Mat.
			Mat mat = new Mat();

			// This cannot be 'true'. The program will never exit if it is. This
			// lets the robot stop this thread when restarting robot code or
			// deploying.
			while (!Thread.interrupted()) {
				// Tell the CvSink to grab a frame from the camera and put it
				// in the source mat.  If there is an error notify the output.
				if (cvSink.grabFrame(mat) == 0) {
					// Send the output the error.
					outputStream.notifyError(cvSink.getError());
					// skip the rest of the current iteration
					continue;
				}
				// Put a rectangle on the image
				Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400),
						new Scalar(0, 0, 255), 5);
				// Give the output stream a new image to display
				outputStream.putFrame(mat);
				
			}*/
		});	
		visionThread.setDaemon(true);
		visionThread.start();
	}
	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Robot.driveSubsystem.pidStop();
		Robot.driveSubsystem.zeroEncoders();
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
		driveSubsystem.resetYaw();
		//driveSubsystem.shiftLow();
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
		shooterSubsystem.FeedPIDShooterStop();
		intakeSubsystem.shiftLowerIn();
		//intakeSubsystem.shiftUpperOut();
		gearSubsystem.gearTopClosed();
		gearSubsystem.gearBoxClosed();
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