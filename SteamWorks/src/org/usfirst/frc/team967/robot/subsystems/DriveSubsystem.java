package org.usfirst.frc.team967.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team967.robot.Robot;
import org.usfirst.frc.team967.robot.RobotConstraints;
import org.usfirst.frc.team967.robot.RobotMap;
import org.usfirst.frc.team967.robot.commands.TeleOp_ArcadeDrive;
import org.usfirst.frc.team967.robot.commands.testing.ArcadeDrive3_4Turn;

/*
 * Works on the practice robot needs to be tested on the real robot.
 */

public class DriveSubsystem extends Subsystem implements PIDOutput {
	
	private AHRS gyro;  					   // Creating the variable gyro as a AHRS variable.
	private PIDController turnController;      // Creating the variable turnController as a PidLoop.  
	
	public double PIDOutput;           // Creating a public double as PIDOutput. 
	
	static final double kP = 0.018; // Setting the p for the PID loop to use.
	static final double kI = 0.00;     // Setting the I for the PID loop to use.
	static final double kD = 0.02;     // Setting the D for the PID loop to use.
	
	static final double kToleranceDegrees = 5.0f; // Setting the tolerance for the pid loop.
	//1.0f
	private CANTalon driveLeftLead;		// Creating driveLeftLead as a motor controller.
	private CANTalon driveLeftFollow;   // Creating driveLeftFollow as a motor controller.
	//private CANTalon driveLeftFollow1;  // Creating driveLeftFollow1 as a motor controller.
	private CANTalon driveRightLead;	// Creating driveRightLead as a motor controller.		
	private CANTalon driveRightFollow;	// Creating driveRightFollow as a motor controller.
//	private CANTalon driveRightFollow1;	// Creating driveRightFollow1 as a motor controller.
	
	private DoubleSolenoid shifter;     // Creating the solenoid to use for shifting.
	
	private final double deadBand = RobotConstraints.DriveSubsystem_deadBand; // Setting the deadband to what's in the RobotConstraints file.
	public boolean InHighGear;	// Creating the variable InHighGear to tell if in high gear.
	public boolean countsmeet;
	/*
	 * This gets called from the main file Robot.java 
	 * It is used to setup all of the different functions that the subsystem needs to do.
	 */
	public DriveSubsystem(){
		
		driveLeftLead = new CANTalon(RobotMap.driveLeftLead);    	  // The left drive lead motor
		driveLeftFollow = new CANTalon(RobotMap.driveLeftFollow);  	  // The left drive follow motor
		driveRightLead = new CANTalon(RobotMap.driveRightLead);   	  // The right drive lead motor
		driveRightFollow = new CANTalon(RobotMap.driveRightFollow);	  // The right drive follow motor
		shifter = new DoubleSolenoid(RobotMap.PCM, RobotMap.driveShifterLow, RobotMap.driveShifterHigh);

		
		driveLeftLead.changeControlMode(TalonControlMode.PercentVbus);			// changing the Talon mode to PrecentVbus.
		driveLeftLead.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);   // setting the feedbackDevice to a quad encoder.
		driveLeftLead.configEncoderCodesPerRev(12); // Setting the counts on the encoder to 12.
    	
		// Setting the motor driveLeftFollow to driveLeaftLead.
		driveLeftFollow.changeControlMode(TalonControlMode.Follower);
		driveLeftFollow.set(driveLeftLead.getDeviceID());
		
		driveRightLead.changeControlMode(TalonControlMode.PercentVbus);			// changing the Talon mode to PrecentVbus.
		driveRightLead.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);  // setting the feedbackDevice to a quad encoder.
		driveRightLead.configEncoderCodesPerRev(12);  // Setting the counts on the encoder to 12.
    	
		// setting the motor driveRightFollow to driveRightLead
		driveRightFollow.changeControlMode(TalonControlMode.Follower);
		driveRightFollow.set(driveRightLead.getDeviceID());
			
		/*
		 * trying to set the navx to the mxp port on the robot but if that does not work it catches the error 
		 * and the rest of the program goes on without it.
		 * Very important so if the navx does not work the rest of the code goes on
		 */
		try { 
			 gyro = new AHRS(SPI.Port.kMXP); // setting the navx to the mxp port 
	     } 
		 catch (RuntimeException ex )  // catching if an error was called.
		 {
			 DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true); // sending a message to the driver station telling that the navx is not working.
	     }
		 
		 gyro.zeroYaw(); // zeroing the Yaw of the gyro.
		 
		 turnController = new PIDController(kP, kI, kD, gyro,this);// setting the P,I,D and the input source. 
		 turnController.disable(); // turning of the pid loop
	     turnController.setInputRange(-180.0f,  180.0f); // setting the input range of the pid loop
	     turnController.setOutputRange(-1.0, 1.0); // setting the output range of the pid loop
	     turnController.setAbsoluteTolerance(kToleranceDegrees); // setting the tolerance of the pd loop
	     turnController.setContinuous(true); // setting if the pid code can go over the -180 to 180 line.
	}
	
	/*
	 * used to change the joystick's into arcade format but with two different joysticks.
	 */
	public void arcadeDrive(double yAxis, double xAxis) {	 
		//square the values for better control at low speeds
		yAxis = yAxis*Math.abs(yAxis);
		xAxis = xAxis*Math.abs(xAxis);
		
		if((yAxis< deadBand) && (yAxis > -deadBand)){ yAxis=0;}
    	if((xAxis< deadBand) && (xAxis > -deadBand)){ xAxis=0;}
    	double L = yAxis + xAxis;
    	double R = yAxis - xAxis;
    	double max = Math.abs(L);
    	if(Math.abs(R) > max) max = Math.abs(R);
    	if((Math.abs(yAxis) <= 1) && (Math.abs(xAxis) <= 1) && (max < 1)){
    		move(L,R);
    	}else
    		move(L/max, R/max);
    	SmartDashboard.putNumber("R", R);
    	SmartDashboard.putNumber("L", L);
    	SmartDashboard.putNumber("R/max", R/max);
    	SmartDashboard.putNumber("L/max", L/max);
    }
	
	/*
	 * The only place that the code sets the power to the drive motors 
	 *  
	 */
	public void move(double left, double right){
		if(left > 1){
			left = 1;
		}
		if(left < -1){
			left = -1;
		}
		if(right > 1){
			right = 1;
		}
		if(right < -1){
			right = -1;
		}
		if(left < 0 && left > -0.1){
			left = -.1;
		}
		if(right < 0 && right > -0.1){
			right = -.1;
		}
		if(right > 0 && right < 0.1){
			right = .1;
		}
		if(left > 0 && left < 0.1){
			left = .1;
		}
		driveLeftLead.set(left);
    	driveRightLead.set(-right);
    }
	
	public void pidEnable(){
		turnController.enable();	
	}
	public void pidSetPoint(double input){
		turnController.setSetpoint(input);
	}
	public void pidWrite(double output) {		
		if(turnController.getDeltaSetpoint() < 0){
			PIDOutput = output;	
		}
		else {
			PIDOutput = -output;
		}
	}
	public boolean pidDone(){
		if(turnController.onTarget()){
			turnController.disable();
			return true;
		}
		else{
			return false;
		}
	}
	
	public void resetYaw(){
		gyro.zeroYaw();
	}
	
	public double getYaw(){
		return gyro.getYaw();
	}
	
	public void pidStop(){
		turnController.disable();
	}

	public double getLEncoder(){
		return driveLeftLead.getEncPosition();
	}
	public double getREncoder(){
		return driveRightLead.getEncPosition();
	}
	public void zeroEncoders(){
		driveLeftLead.setEncPosition(0);
		driveRightLead.setEncPosition(0);
	}
	public boolean driveDistance(double count){
		countsmeet = false;
		if(count > 0){
			if((-getLEncoder() + getREncoder())/2 > count){
				countsmeet = true;
				return true;
	    	}
			else{
				return false;
			}
		}
		else{
			if((-getLEncoder() + getREncoder())/2 < count){
				countsmeet = true;
				return true;
	    	}
			else{
				return false;
			}
		}
	}
//	public boolean
	public void shiftLow() {
	    InHighGear = false;
	    shifter.set(DoubleSolenoid.Value.kForward);
	}
	public void shiftHigh() {
	    InHighGear = true;
	    shifter.set(DoubleSolenoid.Value.kReverse);
	}
	public void toggleShift(){
		if(InHighGear){	shiftLow();}
		else{			shiftHigh();}
	}
    public void initDefaultCommand() {
    	setDefaultCommand(new ArcadeDrive3_4Turn());
    	//	setDefaultCommand(new TeleOp_ArcadeDrive());
    }
        
    public void log(){
    	SmartDashboard.putNumber("Left Encoder Position", driveLeftLead.getEncPosition());
    	SmartDashboard.putNumber("Right Encoder Position", driveRightLead.getEncPosition());
    	SmartDashboard.putNumber("Gyro Yaw", gyro.getYaw());
    	SmartDashboard.putNumber("right lead amps", driveRightLead.getOutputCurrent());
    	SmartDashboard.putNumber("left lead amps", driveLeftLead.getOutputCurrent());
    	SmartDashboard.putNumber("right follow amps", driveRightFollow.getOutputCurrent());
    	SmartDashboard.putNumber("left follow amps", driveLeftFollow.getOutputCurrent());
    	SmartDashboard.putBoolean("DriveGearHigh", InHighGear);
    	SmartDashboard.putBoolean("counts meet", countsmeet);
    /*
   	 	SmartDashboard.putBoolean(  "IMU_Connected",        gyro.isConnected());
        SmartDashboard.putBoolean(  "IMU_IsCalibrating",    gyro.isCalibrating());
        SmartDashboard.putNumber(   "IMU_Yaw",              gyro.getYaw());
        SmartDashboard.putNumber(   "IMU_Pitch",            gyro.getPitch());
        SmartDashboard.putNumber(   "IMU_Roll",             gyro.getRoll());
        SmartDashboard.putNumber(   "Error", turnController.getError());
        SmartDashboard.putNumber(   "P", turnController.getP());
        SmartDashboard.putNumber(   "I", turnController.getI());
        SmartDashboard.putNumber(   "D", turnController.getD());
        SmartDashboard.putNumber(   "Setpoint", turnController.getSetpoint());
        
      */  

        //        
//        /* Display tilt-corrected, Magnetometer-based heading (requires             */
//        /* magnetometer calibration to be useful)                                   */
//        
//        SmartDashboard.putNumber(   "IMU_CompassHeading",   gyro.getCompassHeading());
//        
//        /* Display 9-axis Heading (requires magnetometer calibration to be useful)  */
//        SmartDashboard.putNumber(   "IMU_FusedHeading",     gyro.getFusedHeading());
//
//        /* These functions are compatible w/the WPI Gyro Class, providing a simple  */
//        /* path for upgrading from the Kit-of-Parts gyro to the navx-MXP            */
//        
//        SmartDashboard.putNumber(   "IMU_TotalYaw",         gyro.getAngle());
//        SmartDashboard.putNumber(   "IMU_YawRateDPS",       gyro.getRate());
//
//        /* Display Processed Acceleration Data (Linear Acceleration, Motion Detect) */
//		SmartDashboard.putNumber(   "IMU_Accel_X",          gyro.getWorldLinearAccelX());
//        SmartDashboard.putNumber(   "IMU_Accel_Y",          gyro.getWorldLinearAccelY());
//        SmartDashboard.putBoolean(  "IMU_IsMoving",         gyro.isMoving());
//        SmartDashboard.putNumber("error" , gyro.getYaw());
//		
//   	 	SmartDashboard.putBoolean(  "IMU_Connected",        gyro.isConnected());
//        SmartDashboard.putBoolean(  "IMU_IsCalibrating",    gyro.isCalibrating());
//        SmartDashboard.putNumber(   "IMU_Yaw",              gyro.getYaw());
//        SmartDashboard.putNumber(   "IMU_Pitch",            gyro.getPitch());
//        SmartDashboard.putNumber(   "IMU_Roll",             gyro.getRoll());
//        
//        /* Display tilt-corrected, Magnetometer-based heading (requires             */
//        /* magnetometer calibration to be useful)                                   */
//        
//        SmartDashboard.putNumber(   "IMU_CompassHeading",   gyro.getCompassHeading());
//        
//        /* Display 9-axis Heading (requires magnetometer calibration to be useful)  */
//        SmartDashboard.putNumber(   "IMU_FusedHeading",     gyro.getFusedHeading());
//
//        /* These functions are compatible w/the WPI Gyro Class, providing a simple  */
//        /* path for upgrading from the Kit-of-Parts gyro to the navx-MXP            */
//        
//        SmartDashboard.putNumber(   "IMU_TotalYaw",         gyro.getAngle());
//        SmartDashboard.putNumber(   "IMU_YawRateDPS",       gyro.getRate());
//
//        /* Display Processed Acceleration Data (Linear Acceleration, Motion Detect) */
//		SmartDashboard.putNumber(   "IMU_Accel_X",          gyro.getWorldLinearAccelX());
//        SmartDashboard.putNumber(   "IMU_Accel_Y",          gyro.getWorldLinearAccelY());
//        SmartDashboard.putBoolean(  "IMU_IsMoving",         gyro.isMoving());
//        
//        SmartDashboard.putNumber("gyro Yaw" , gyro.getYaw());
//		
//   	 	SmartDashboard.putBoolean(  "IMU_Connected",        gyro.isConnected());
//        SmartDashboard.putBoolean(  "IMU_IsCalibrating",    gyro.isCalibrating());
//        SmartDashboard.putNumber(   "IMU_Yaw",              gyro.getYaw());
//        SmartDashboard.putNumber(   "IMU_Pitch",            gyro.getPitch());
//        SmartDashboard.putNumber(   "IMU_Roll",             gyro.getRoll());
//        
//        /* Display tilt-corrected, Magnetometer-based heading (requires             */
//        /* magnetometer calibration to be useful)                                   */
//        
//        SmartDashboard.putNumber(   "IMU_CompassHeading",   gyro.getCompassHeading());
//        
//        /* Display 9-axis Heading (requires magnetometer calibration to be useful)  */
//        SmartDashboard.putNumber(   "IMU_FusedHeading",     gyro.getFusedHeading());
//
//        /* These functions are compatible w/the WPI Gyro Class, providing a simple  */
//        /* path for upgrading from the Kit-of-Parts gyro to the navx-MXP            */
//        
//        SmartDashboard.putNumber(   "IMU_TotalYaw",         gyro.getAngle());
//        SmartDashboard.putNumber(   "IMU_YawRateDPS",       gyro.getRate());
//
//        /* Display Processed Acceleration Data (Linear Acceleration, Motion Detect) */
//		SmartDashboard.putNumber(   "IMU_Accel_X",          gyro.getWorldLinearAccelX());
//        SmartDashboard.putNumber(   "IMU_Accel_Y",          gyro.getWorldLinearAccelY());
//        SmartDashboard.putBoolean(  "IMU_IsMoving",         gyro.isMoving());
    }
}
        