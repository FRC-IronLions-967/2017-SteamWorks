package org.usfirst.frc.team967.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;
import com.ctre.CANTalon;

import org.usfirst.frc.team967.robot.RobotConstraints;
import org.usfirst.frc.team967.robot.RobotMap;
import org.usfirst.frc.team967.robot.commands.TeleOp_ArcadeDrive;

public class DriveSubsystem extends Subsystem implements PIDOutput {
	  
	AHRS gyro;
	PIDController turnController;
	double rotateToAngleRate;
	
	static final double kP = 0.005;
	static final double kI = 0.00;
	static final double kD = 0.00;
	
	static final double kToleranceDegrees = 2.0f;
	
	public boolean isSet = false;
	
	public CANTalon driveLeftLead;
	public CANTalon driveLeftFollow;
	public CANTalon driveLeftFollow1;
	public CANTalon driveRightLead;
	public CANTalon driveRightFollow;
	public CANTalon driveRightFollow1;
	
	public DoubleSolenoid shifter;
	
	private final double deadBand = RobotConstraints.DriveSubsystem_deadBand;
	public boolean InHighGear;	
	
	public DriveSubsystem(){
		driveLeftLead = new CANTalon(RobotMap.driveLeftLead);    // The left drive lead motor
		driveLeftFollow = new CANTalon(RobotMap.driveLeftFollow);  // The left drive follow motor
		driveLeftFollow1 = new CANTalon(RobotMap.driveLeftFollow1);
		driveRightLead = new CANTalon(RobotMap.driveRightLead);   // The right drive lead motor
		driveRightFollow = new CANTalon(RobotMap.driveRightFollow);
		driveRightFollow1 = new CANTalon(RobotMap.driveRightFollow1);// The right drive follow motor
		
	//	shifter = new DoubleSolenoid(0, 0, 7); // The shifter for high-low gear. (CAN bus ID, On port, Off port)
		shifter = new DoubleSolenoid(RobotMap.driveShifterLow, RobotMap.driveShifterHigh); //defaults to module 0
		
		driveLeftLead.changeControlMode(TalonControlMode.PercentVbus);
		driveLeftFollow.changeControlMode(TalonControlMode.PercentVbus);
		driveRightLead.changeControlMode(TalonControlMode.PercentVbus);
		driveRightFollow.changeControlMode(TalonControlMode.PercentVbus);
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
	public void gyroNoPid(double set){
			SmartDashboard.putNumber("set",set );
			if (gyro.getYaw() > set){
				move(.3,-.3);
				isSet = false;
			}
			else if (gyro.getYaw() < set){
				move(.3,-.3);
				isSet = false;
			}
			else if (gyro.getYaw() == set){
				move(0,0);
				isSet = true;
		}
	}
	
	public void turn (double amount){
		turnController.enable();
		double yawVal = gyro.getYaw() + amount;
		turnController.setSetpoint(yawVal);
		double val = turnController.get();
		move(val,-val);
//		turnController.disable();
	}
	public void pidStop(){
		turnController.disable();
	}
	
	public void shiftLow() {
	    InHighGear = false;
	    shifter.set(DoubleSolenoid.Value.kReverse);
	}
	/*
	 * 	exampleDouble.set(DoubleSolenoid.Value.kOff);
	 *	exampleDouble.set(DoubleSolenoid.Value.kForward);
	 *	exampleDouble.set(DoubleSolenoid.Value.kReverse);
	 */
	public void shiftHigh() {
	    InHighGear = true;
	    shifter.set(DoubleSolenoid.Value.kForward);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new TeleOp_ArcadeDrive());
    }
    
    public void log(){
  
    	SmartDashboard.putNumber("error" , gyro.getYaw());
		
   	 	SmartDashboard.putBoolean(  "IMU_Connected",        gyro.isConnected());
        SmartDashboard.putBoolean(  "IMU_IsCalibrating",    gyro.isCalibrating());
        SmartDashboard.putNumber(   "IMU_Yaw",              gyro.getYaw());
        SmartDashboard.putNumber(   "IMU_Pitch",            gyro.getPitch());
        SmartDashboard.putNumber(   "IMU_Roll",             gyro.getRoll());
        
        /* Display tilt-corrected, Magnetometer-based heading (requires             */
        /* magnetometer calibration to be useful)                                   */
        
        SmartDashboard.putNumber(   "IMU_CompassHeading",   gyro.getCompassHeading());
        
        /* Display 9-axis Heading (requires magnetometer calibration to be useful)  */
        SmartDashboard.putNumber(   "IMU_FusedHeading",     gyro.getFusedHeading());

        /* These functions are compatible w/the WPI Gyro Class, providing a simple  */
        /* path for upgrading from the Kit-of-Parts gyro to the navx-MXP            */
        
        SmartDashboard.putNumber(   "IMU_TotalYaw",         gyro.getAngle());
        SmartDashboard.putNumber(   "IMU_YawRateDPS",       gyro.getRate());

        /* Display Processed Acceleration Data (Linear Acceleration, Motion Detect) */
        
        SmartDashboard.putNumber(   "IMU_Accel_X",          gyro.getWorldLinearAccelX());
        SmartDashboard.putNumber(   "IMU_Accel_Y",          gyro.getWorldLinearAccelY());
        SmartDashboard.putBoolean(  "IMU_IsMoving",         gyro.isMoving());
        SmartDashboard.putBoolean(  "IMU_IsRotating",       gyro.isRotating());

        /* Display estimates of velocity/displacement.  Note that these values are  */
        /* not expected to be accurate enough for estimating robot position on a    */
        /* FIRST FRC Robotics Field, due to accelerometer noise and the compounding */
        /* of these errors due to single (velocity) integration and especially      */
        /* double (displacement) integration.                                       */
        
        SmartDashboard.putNumber(   "Velocity_X",           gyro.getVelocityX());
        SmartDashboard.putNumber(   "Velocity_Y",           gyro.getVelocityY());
        SmartDashboard.putNumber(   "Displacement_X",       gyro.getDisplacementX());
        SmartDashboard.putNumber(   "Displacement_Y",       gyro.getDisplacementY());
        
        /* Display Raw Gyro/Accelerometer/Magnetometer Values                       */
        /* NOTE:  These values are not normally necessary, but are made available   */
        /* for advanced users.  Before using this data, please consider whether     */
        /* the processed data (see above) will suit your needs.                     */
        
        SmartDashboard.putNumber(   "RawGyro_X",            gyro.getRawGyroX());
        SmartDashboard.putNumber(   "RawGyro_Y",            gyro.getRawGyroY());
        SmartDashboard.putNumber(   "RawGyro_Z",            gyro.getRawGyroZ());
        SmartDashboard.putNumber(   "RawAccel_X",           gyro.getRawAccelX());
        SmartDashboard.putNumber(   "RawAccel_Y",           gyro.getRawAccelY());
        SmartDashboard.putNumber(   "RawAccel_Z",           gyro.getRawAccelZ());
        SmartDashboard.putNumber(   "RawMag_X",             gyro.getRawMagX());
        SmartDashboard.putNumber(   "RawMag_Y",             gyro.getRawMagY());
        SmartDashboard.putNumber(   "RawMag_Z",             gyro.getRawMagZ());
        SmartDashboard.putNumber(   "IMU_Temp_C",           gyro.getTempC());
        
        /* Omnimount Yaw Axis Information                                           */
        /* For more info, see http://navx-mxp.kauailabs.com/installation/omnimount  */
        AHRS.BoardYawAxis yaw_axis = gyro.getBoardYawAxis();
        SmartDashboard.putString(   "YawAxisDirection",     yaw_axis.up ? "Up" : "Down" );
        SmartDashboard.putNumber(   "YawAxis",              yaw_axis.board_axis.getValue() );
        
        /* Sensor Board Information                                                 */
        SmartDashboard.putString(   "FirmwareVersion",      gyro.getFirmwareVersion());
        
        /* Quaternion Data                                                          */
        /* Quaternions are fascinating, and are the most compact representation of  */
        /* orientation data.  All of the Yaw, Pitch and Roll Values can be derived  */
        /* from the Quaternions.  If interested in motion processing, knowledge of  */
        /* Quaternions is highly recommended.                                       */
        SmartDashboard.putNumber(   "QuaternionW",          gyro.getQuaternionW());
        SmartDashboard.putNumber(   "QuaternionX",          gyro.getQuaternionX());
        SmartDashboard.putNumber(   "QuaternionY",          gyro.getQuaternionY());
        SmartDashboard.putNumber(   "QuaternionZ",          gyro.getQuaternionZ());
        
        /* Connectivity Debugging Support                                           */
        SmartDashboard.putNumber(   "IMU_Byte_Count",       gyro.getByteCount());
        SmartDashboard.putNumber(   "IMU_Update_Count",     gyro.getUpdateCount());
        
    }

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		
	}
}