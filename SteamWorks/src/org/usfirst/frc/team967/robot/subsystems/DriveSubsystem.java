package org.usfirst.frc.team967.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team967.robot.Robot;
import org.usfirst.frc.team967.robot.RobotConstraints;
import org.usfirst.frc.team967.robot.RobotMap;
import org.usfirst.frc.team967.robot.commands.TeleOp_ArcadeDrive;

public class DriveSubsystem extends Subsystem implements PIDOutput {
	
	AHRS gyro;
	PIDController turnController;
	double rotateToAngleRate;
	
	public double PIDOutput;
	
	static final double kP = 0.012;
	static final double kI = 0.00;
	static final double kD = 0.00;
	
	public boolean Finished;
	
	static final double kToleranceDegrees = 1.0f;
	
	private CANTalon driveLeftLead;
	private CANTalon driveLeftFollow;
	private CANTalon driveLeftFollow1;
	private CANTalon driveRightLead;
	private CANTalon driveRightFollow;
	private CANTalon driveRightFollow1;
	
	private DoubleSolenoid shifter;
	
	private final double deadBand = RobotConstraints.DriveSubsystem_deadBand;
	public boolean InHighGear;	
	
	public DriveSubsystem(){
		driveLeftLead = new CANTalon(RobotMap.driveLeftLead);    // The left drive lead motor
		driveLeftFollow = new CANTalon(RobotMap.driveLeftFollow);  // The left drive follow motor
		driveLeftFollow1 = new CANTalon(RobotMap.driveLeftFollow1);
		driveRightLead = new CANTalon(RobotMap.driveRightLead);   // The right drive lead motor
		driveRightFollow = new CANTalon(RobotMap.driveRightFollow);
		driveRightFollow1 = new CANTalon(RobotMap.driveRightFollow1);// The right drive follow motor
		
		shifter = new DoubleSolenoid(RobotMap.PCM, RobotMap.driveShifterLow, RobotMap.driveShifterHigh); //defaults to module 0
		
		driveLeftLead.changeControlMode(TalonControlMode.PercentVbus);
		driveLeftLead.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		driveLeftLead.reverseSensor(false);
		driveLeftLead.reverseOutput(false);
		driveLeftLead.configEncoderCodesPerRev(12); 
    	
		driveLeftFollow.changeControlMode(TalonControlMode.Follower);
		driveLeftFollow.set(driveLeftLead.getDeviceID());
		
		driveRightLead.changeControlMode(TalonControlMode.PercentVbus);
		driveRightLead.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		driveRightFollow.changeControlMode(TalonControlMode.Follower);
		driveRightFollow.set(driveRightLead.getDeviceID());
		//these will go away
		driveRightFollow1.changeControlMode(TalonControlMode.PercentVbus);
		driveLeftFollow1.changeControlMode(TalonControlMode.PercentVbus);
		
		try {
			 gyro = new AHRS(SPI.Port.kMXP); 
	     } 
		 catch (RuntimeException ex ) 
		 {
			 DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
	     }
		 
		 gyro.zeroYaw();
		 
		 turnController = new PIDController(kP, kI, kD, gyro,this);
		 turnController.disable();
	     turnController.setInputRange(-180.0f,  180.0f);
	     turnController.setOutputRange(-1.0, 1.0);
	     turnController.setAbsoluteTolerance(kToleranceDegrees);
	     turnController.setContinuous(true);
	}
	
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
	
	public void move(double left, double right){
		driveLeftLead.set(left);
		driveLeftFollow1.set(left);
    	driveRightLead.set(-right);
    	driveRightFollow1.set(-right);
    }
	/*
	 *  public void moveTime(double left,double right, double time){
		move(left,right);
		Timer.delay(time);
		move(0,0);
	}
	*/
	public void pidEnable(){
		turnController.enable();	
	}
	public void pidSetPoint(double input){
		turnController.setSetpoint(input);
	}
	public void pidWrite(double output) {		
		if(turnController.getDeltaSetpoint() < 0){
			//move(output,-output);
			PIDOutput = output;	
		}
		else {
			PIDOutput = -output;
			//move(-output,output);
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
		if((getLEncoder() + getREncoder())/2 > count){
    		return true;
    	}
		else{
			return false;
		}
	}
	public void shiftLow() {
	    InHighGear = false;
	    shifter.set(DoubleSolenoid.Value.kReverse);
	}
	public void shiftHigh() {
	    InHighGear = true;
	    shifter.set(DoubleSolenoid.Value.kForward);
	}
	public void toggleShift(){
		if(InHighGear){
			shiftLow();
		}
		else{
			shiftHigh();
		}
	}
	
    
	public void initDefaultCommand() {
    	setDefaultCommand(new TeleOp_ArcadeDrive());
    }
    
    public void outputOn(){
    	Robot.oi.getBox().setOutput(2, true);
    }
    public void outputOff(){
    	Robot.oi.getBox().setOutput(2, false);
    }
    
    public void log(){
    	SmartDashboard.putNumber("Left Encoder Position", driveLeftLead.getEncPosition());
    	SmartDashboard.putNumber("Right Encoder Position", driveRightLead.getEncPosition());
    	
//    	SmartDashboard.putBoolean("High Gear", InHighGear);
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