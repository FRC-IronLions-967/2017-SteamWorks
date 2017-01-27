package org.usfirst.frc.team967.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team967.robot.commands.TeleOp_ArcadeDrive;

public class DriveSubsystem extends Subsystem implements PIDOutput{
	
	AHRS ahrs;
	PIDController turnController;
	double rotateToAngleRate;
	
	static final double kP = 0.01;
	static final double kI = 1.00;
	static final double kD = 0.00;
	
	static final double kToleranceDegrees = 2.0f;
	  
	public CANTalon driveLeftLead;
	public CANTalon driveLeftFollow;
	public CANTalon driveLeftFollow1;
	public CANTalon driveRightLead;
	public CANTalon driveRightFollow;
	public CANTalon driveRightFollow1;
	
	public DoubleSolenoid shifter;
	
	private final double deadBand = .2;
	public boolean InHighGear;	
	
	public DriveSubsystem(){
		
		driveLeftLead = new CANTalon(30);    // The left drive lead motor
		driveLeftFollow = new CANTalon(31);  // The left drive follow motor
		driveLeftFollow1 = new CANTalon(33);
		driveRightLead = new CANTalon(34);   // The right drive lead motor
		driveRightFollow = new CANTalon(35);
		driveRightFollow1 = new CANTalon(36);// The right drive follow motor
		
		shifter = new DoubleSolenoid(0, 0, 1); // The shifter for high-low gear. (CAN bus ID, On port, Off port)
		driveLeftLead.changeControlMode(TalonControlMode.PercentVbus);
		driveLeftFollow.changeControlMode(TalonControlMode.PercentVbus);
		driveRightLead.changeControlMode(TalonControlMode.PercentVbus);
		driveRightFollow.changeControlMode(TalonControlMode.PercentVbus);
		driveRightFollow1.changeControlMode(TalonControlMode.PercentVbus);
		driveLeftFollow1.changeControlMode(TalonControlMode.PercentVbus);
	
		 try {
			 ahrs = new AHRS(SPI.Port.kMXP); 
	     } 
		 catch (RuntimeException ex ) 
		 {
			 DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
	     }
		 
		 ahrs.zeroYaw();
		 
		 turnController = new PIDController(kP, kI, kD, ahrs,this);
//		 driveRightLead.changeControlMode(TalonControlMode.Follower);
//		 driveRightLead.set(driveLeftLead.getDeviceID());
//		 driveRightLead.reverseOutput(true);
		 turnController.disable();
	     turnController.setInputRange(-180.0f,  180.0f);
	     turnController.setOutputRange(-1.0, 1.0);
	     turnController.setAbsoluteTolerance(kToleranceDegrees);
	     turnController.setContinuous(true);
	}
	
	public void arcadeDrive(double yAxis, double xAxis) {	
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
		driveLeftFollow.set(left);
		driveLeftFollow1.set(left);
    	driveRightLead.set(-right);
    	driveRightFollow.set(-right);
    	driveRightFollow1.set(-right);
    }
	
	public void moveTime(double left,double right, double time){
		move(left,right);
		Timer.delay(time);
		move(0,0);
	}
	
	public void turn (double amount){
		turnController.enable();
		turnController.setSetpoint(amount);		
	}
	public void pidStop(){
		turnController.disable();
	}
	
	public void shiftLow() {
	    InHighGear = false;
	    shifter.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void shiftHigh() {
	    InHighGear = true;
	    shifter.set(DoubleSolenoid.Value.kForward);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new TeleOp_ArcadeDrive());
    }
    
    public void log(){
    
		turnController.getError();
    	
		SmartDashboard.putNumber("error" , turnController.getError());
		
    	 SmartDashboard.putBoolean(  "IMU_Connected",        ahrs.isConnected());
         SmartDashboard.putBoolean(  "IMU_IsCalibrating",    ahrs.isCalibrating());
         SmartDashboard.putNumber(   "IMU_Yaw",              ahrs.getYaw());
         SmartDashboard.putNumber(   "IMU_Pitch",            ahrs.getPitch());
         SmartDashboard.putNumber(   "IMU_Roll",             ahrs.getRoll());
         
         /* Display tilt-corrected, Magnetometer-based heading (requires             */
         /* magnetometer calibration to be useful)                                   */
         
         SmartDashboard.putNumber(   "IMU_CompassHeading",   ahrs.getCompassHeading());
         
         /* Display 9-axis Heading (requires magnetometer calibration to be useful)  */
         SmartDashboard.putNumber(   "IMU_FusedHeading",     ahrs.getFusedHeading());

         /* These functions are compatible w/the WPI Gyro Class, providing a simple  */
         /* path for upgrading from the Kit-of-Parts gyro to the navx-MXP            */
         
         SmartDashboard.putNumber(   "IMU_TotalYaw",         ahrs.getAngle());
         SmartDashboard.putNumber(   "IMU_YawRateDPS",       ahrs.getRate());

         /* Display Processed Acceleration Data (Linear Acceleration, Motion Detect) */
         
         SmartDashboard.putNumber(   "IMU_Accel_X",          ahrs.getWorldLinearAccelX());
         SmartDashboard.putNumber(   "IMU_Accel_Y",          ahrs.getWorldLinearAccelY());
         SmartDashboard.putBoolean(  "IMU_IsMoving",         ahrs.isMoving());
         SmartDashboard.putBoolean(  "IMU_IsRotating",       ahrs.isRotating());

         /* Display estimates of velocity/displacement.  Note that these values are  */
         /* not expected to be accurate enough for estimating robot position on a    */
         /* FIRST FRC Robotics Field, due to accelerometer noise and the compounding */
         /* of these errors due to single (velocity) integration and especially      */
         /* double (displacement) integration.                                       */
         
         SmartDashboard.putNumber(   "Velocity_X",           ahrs.getVelocityX());
         SmartDashboard.putNumber(   "Velocity_Y",           ahrs.getVelocityY());
         SmartDashboard.putNumber(   "Displacement_X",       ahrs.getDisplacementX());
         SmartDashboard.putNumber(   "Displacement_Y",       ahrs.getDisplacementY());
         
         /* Display Raw Gyro/Accelerometer/Magnetometer Values                       */
         /* NOTE:  These values are not normally necessary, but are made available   */
         /* for advanced users.  Before using this data, please consider whether     */
         /* the processed data (see above) will suit your needs.                     */
         
         SmartDashboard.putNumber(   "RawGyro_X",            ahrs.getRawGyroX());
         SmartDashboard.putNumber(   "RawGyro_Y",            ahrs.getRawGyroY());
         SmartDashboard.putNumber(   "RawGyro_Z",            ahrs.getRawGyroZ());
         SmartDashboard.putNumber(   "RawAccel_X",           ahrs.getRawAccelX());
         SmartDashboard.putNumber(   "RawAccel_Y",           ahrs.getRawAccelY());
         SmartDashboard.putNumber(   "RawAccel_Z",           ahrs.getRawAccelZ());
         SmartDashboard.putNumber(   "RawMag_X",             ahrs.getRawMagX());
         SmartDashboard.putNumber(   "RawMag_Y",             ahrs.getRawMagY());
         SmartDashboard.putNumber(   "RawMag_Z",             ahrs.getRawMagZ());
         SmartDashboard.putNumber(   "IMU_Temp_C",           ahrs.getTempC());
         
         /* Omnimount Yaw Axis Information                                           */
         /* For more info, see http://navx-mxp.kauailabs.com/installation/omnimount  */
         AHRS.BoardYawAxis yaw_axis = ahrs.getBoardYawAxis();
         SmartDashboard.putString(   "YawAxisDirection",     yaw_axis.up ? "Up" : "Down" );
         SmartDashboard.putNumber(   "YawAxis",              yaw_axis.board_axis.getValue() );
         
         /* Sensor Board Information                                                 */
         SmartDashboard.putString(   "FirmwareVersion",      ahrs.getFirmwareVersion());
         
         /* Quaternion Data                                                          */
         /* Quaternions are fascinating, and are the most compact representation of  */
         /* orientation data.  All of the Yaw, Pitch and Roll Values can be derived  */
         /* from the Quaternions.  If interested in motion processing, knowledge of  */
         /* Quaternions is highly recommended.                                       */
         SmartDashboard.putNumber(   "QuaternionW",          ahrs.getQuaternionW());
         SmartDashboard.putNumber(   "QuaternionX",          ahrs.getQuaternionX());
         SmartDashboard.putNumber(   "QuaternionY",          ahrs.getQuaternionY());
         SmartDashboard.putNumber(   "QuaternionZ",          ahrs.getQuaternionZ());
         
         /* Connectivity Debugging Support                                           */
         SmartDashboard.putNumber(   "IMU_Byte_Count",       ahrs.getByteCount());
         SmartDashboard.putNumber(   "IMU_Update_Count",     ahrs.getUpdateCount());
    }

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		
	}
}