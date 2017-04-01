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

import java.text.DecimalFormat;

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
	
	static final double kP = RobotConstraints.DriveSubsystem_P; // Setting the p for the PID loop to use.
	static final double kI = RobotConstraints.DriveSubsystem_I;     // Setting the I for the PID loop to use.
	static final double kD = RobotConstraints.DriveSubsystem_D;     // Setting the D for the PID loop to use.
	
	static final double kAbsoluteToleranceDegrees = RobotConstraints.DriveSubsystem_AbsoluteTolerance;
	static final double kToleranceDegrees = RobotConstraints.DriveSubsystem_Tolerance;
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
	
	private DecimalFormat df = new DecimalFormat("#.##");
	private int done;
	//follows (x*.9)^2
	private double[] turnLookUp = new double[]{	0
												,0.000081
												,0.000324
												,0.000729
												,0.001296
												,0.002025
												,0.002916
												,0.003969
												,0.005184
												,0.006561
												,0.0081
												,0.009801
												,0.011664
												,0.013689
												,0.015876
												,0.018225
												,0.020736
												,0.023409
												,0.026244
												,0.029241
												,0.0324
												,0.035721
												,0.039204
												,0.042849
												,0.046656
												,0.050625
												,0.054756
												,0.059049
												,0.063504
												,0.068121
												,0.0729
												,0.077841
												,0.082944
												,0.088209
												,0.093636
												,0.099225
												,0.104976
												,0.110889
												,0.116964
												,0.123201
												,0.1296
												,0.136161
												,0.142884
												,0.149769
												,0.156816
												,0.164025
												,0.171396
												,0.178929
												,0.186624
												,0.194481
												,0.2025
												,0.210681
												,0.219024
												,0.227529
												,0.236196
												,0.245025
												,0.254016
												,0.263169
												,0.272484
												,0.281961
												,0.2916
												,0.301401
												,0.311364
												,0.321489
												,0.331776
												,0.342225
												,0.352836
												,0.363609
												,0.374544
												,0.385641
												,0.3969
												,0.408321
												,0.419904
												,0.431649
												,0.443556
												,0.455625
												,0.467856
												,0.480249
												,0.492804
												,0.505521
												,0.5184
												,0.531441
												,0.544644
												,0.558009
												,0.571536
												,0.585225
												,0.599076
												,0.613089
												,0.627264
												,0.641601
												,0.6561
												,0.670761
												,0.685584
												,0.700569
												,0.715716
												,0.731025
												,0.746496
												,0.762129
												,0.777924
												,0.793881
												,0.81};

	
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
		 turnController.disable(); // turning off the pid loop
	     turnController.setInputRange(-180.0f,  180.0f); // setting the input range of the pid loop
	     turnController.setOutputRange(-1.0, 1.0); // setting the output range of the pid loop
	     turnController.setAbsoluteTolerance(kAbsoluteToleranceDegrees); // setting the tolerance of the pd loop
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
//    	SmartDashboard.putNumber("X axis", xAxis);
//    	SmartDashboard.putNumber("Y axis", yAxis);
//    	SmartDashboard.putNumber("R", R);
//    	SmartDashboard.putNumber("L", L);
//    	SmartDashboard.putNumber("R/max", R/max);
//    	SmartDashboard.putNumber("L/max", L/max);
    }
	// 0.010815180689100581 - 0.7353174468956218x + 8.812554716989258x2 - 11.619421463995128x3 + 4.534123196152433x4
	public void arcadeDriveCurved(double yAxis, double OGxAxis) {	 
		double x = Math.abs(OGxAxis);
		yAxis = yAxis*Math.abs(yAxis);
		double xAxis = (double)(0.010815180689100581 - 0.7353174468956218*x + 8.812554716989258*Math.pow(x,  2) - 11.619421463995128*Math.pow(x, 3) + 4.534123196152433*Math.pow(x, 4));
		if(OGxAxis < 0){
			xAxis = -xAxis;
		}
		
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
//    	SmartDashboard.putNumber("X axis", xAxis);
//    	SmartDashboard.putNumber("Y axis", yAxis);
//    	SmartDashboard.putNumber("R", R);
//    	SmartDashboard.putNumber("L", L);
//    	SmartDashboard.putNumber("R/max", R/max);
//    	SmartDashboard.putNumber("L/max", L/max);
    }
	public void arcadeDriveLookUp(double yAxis, double xAxisCurve) {	 
		double x = Math.abs(xAxisCurve);
		//square the values for better control at low speeds
		yAxis = yAxis*Math.abs(yAxis);
		double xAxis = turnLookUp[(int)(Double.valueOf(df.format(x))*100)];
		if(xAxisCurve > 0){
			xAxis = -xAxis;
		}
		if((yAxis < deadBand) && (yAxis > -deadBand)){ yAxis=0;}
    	if((xAxis < deadBand) && (xAxis > -deadBand)){ xAxis=0;}
    	double L = yAxis + xAxis;
    	double R = yAxis - xAxis;
    	double max = Math.abs(L);
    	if(Math.abs(R) > max) max = Math.abs(R);
    	if((Math.abs(yAxis) <= 1) && (Math.abs(xAxis) <= 1) && (max < 1)){
    		move(L,R);
    	}else
    		move(L/max, R/max);
//    	SmartDashboard.putNumber("X axis", xAxis);
//    	SmartDashboard.putNumber("Y axis", yAxis);
//    	SmartDashboard.putNumber("R", R);
//    	SmartDashboard.putNumber("L", L);
//    	SmartDashboard.putNumber("R/max", R/max);
//    	SmartDashboard.putNumber("L/max", L/max);
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
			done ++;
			if(done >=5){
				turnController.disable();
				return true;
			}
			return false;
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
			if((getREncoder()) > count){
//			if((-getLEncoder() + getREncoder())/2 > count){
				countsmeet = true;
				return true;
	    	}
			else{
				return false;
			}
		}
		else{
			if((getREncoder()) < count){
//			if((-getLEncoder() + getREncoder())/2 > count){
				countsmeet = true;
				return true;
	    	}
			else{
				return false;
			}
		}
	}
	
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
    	setDefaultCommand(new TeleOp_ArcadeDrive());
    }
        
    public void log(){
    	SmartDashboard.putNumber("Left Encoder Position", driveLeftLead.getEncPosition());
    	SmartDashboard.putNumber("Right Encoder Position", driveRightLead.getEncPosition());
    	SmartDashboard.putNumber("Gyro Yaw", gyro.getYaw());
    	SmartDashboard.putNumber("right lead amps", driveRightLead.getOutputCurrent());
    	SmartDashboard.putNumber("left lead amps", driveLeftLead.getOutputCurrent());
    	SmartDashboard.putNumber("right follow amps", driveRightFollow.getOutputCurrent());
    	SmartDashboard.putNumber("left follow amps", driveLeftFollow.getOutputCurrent());
    	SmartDashboard.putNumber("right lead volt", driveRightLead.getOutputVoltage());
    	SmartDashboard.putNumber("left lead volt", driveLeftLead.getOutputVoltage());
    	
    	SmartDashboard.putNumber("Drive PID Error", turnController.getError());
    	
    	
    	SmartDashboard.putBoolean("DriveGearHigh", InHighGear);
//    	SmartDashboard.putBoolean("counts meet", countsmeet);
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
        