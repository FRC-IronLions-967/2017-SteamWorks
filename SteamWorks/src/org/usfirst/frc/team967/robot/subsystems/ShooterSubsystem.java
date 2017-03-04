package org.usfirst.frc.team967.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.CANTalon;

import org.usfirst.frc.team967.robot.RobotConstraints;
import org.usfirst.frc.team967.robot.RobotMap;
import org.usfirst.frc.team967.robot.commands.TeleOp_Shoot;

/*
 *Tested on the practice robot ready to be tested on the real robot.
 */

public class ShooterSubsystem extends Subsystem {
    
    public CANTalon shooterLead;
    private CANTalon shooterFollow;
    private CANTalon shooterFeed;
    
    public int shooterRpm = RobotConstraints.ShooterSubsystem_ShooterSpeed;
	int incrementVal = 50;
    
	double pValue = RobotConstraints.ShooterSubsystem_Shooter_P;
	double iValue = RobotConstraints.ShooterSubsystem_Shooter_I;
	double dValue = RobotConstraints.ShooterSubsystem_Shooter_D;
	
	public ShooterSubsystem(){
		shooterLead = new CANTalon(RobotMap.shooterLead);
		shooterFollow = new CANTalon(RobotMap.shooterFollow);
		shooterFeed = new CANTalon(RobotMap.shooterFeed);
		
		shooterLead.changeControlMode(CANTalon.TalonControlMode.Speed);
		shooterLead.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		shooterLead.reverseSensor(false);
		shooterLead.reverseOutput(true);
    	shooterLead.configEncoderCodesPerRev(12); 
    	shooterLead.configNominalOutputVoltage(+0.0f, -0.0f);
    	shooterLead.configPeakOutputVoltage(+12.0f, 0.0f);//+12.0f, -12.0f
    	shooterLead.setPID(RobotConstraints.ShooterSubsystem_Shooter_P, 
    					   RobotConstraints.ShooterSubsystem_Shooter_I, 
    					   RobotConstraints.ShooterSubsystem_Shooter_D, 
    					   RobotConstraints.ShooterSubsystem_Shooter_F, 
    					   RobotConstraints.ShooterSubsystem_Shooter_Izone, 
    					   RobotConstraints.ShooterSubsystem_Shooter_CloseLoopRampRate, 
    					   RobotConstraints.ShooterSubsystem_Shooter_profile);
		shooterFollow.changeControlMode(CANTalon.TalonControlMode.Follower);
		shooterFollow.set(shooterLead.getDeviceID());
		shooterFollow.reverseOutput(true);
		shooterFeed.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    }
	
	public void Shoot(){
    //may need to uncomment???
		shooterLead.changeControlMode(CANTalon.TalonControlMode.Speed);
    	shooterFollow.changeControlMode(CANTalon.TalonControlMode.Follower);
    	shooterFollow.set(shooterLead.getDeviceID());
		shooterLead.set(-shooterRpm);
    }
	
	public void PUp(){
		pValue +=  .01;
		shooterLead.setP(pValue);
	}
	public void PDown(){
		pValue -= .01;
		shooterLead.setP(pValue);
	}
	public void IUp(){
		iValue +=  .01;
		shooterLead.setI(iValue);
	}
	public void IDown(){
		iValue -= .01;
		shooterLead.setI(iValue);
	}
	public void DUp(){
		dValue +=  .01;
		shooterLead.setD(dValue);
	}
	public void DDown(){
		dValue -= .01;
		shooterLead.setD(dValue);
	}
    
    public void StopShooter(){
    	shooterRpm = 0;
    }
    
    public void ShootSpeedUp(){
    	shooterRpm = shooterRpm + incrementVal;
    }
    
    public void ShootSpeedDown(){
    	shooterRpm = shooterRpm - incrementVal;
    }
    
    public void FeedShooter(double speed){
    	shooterFeed.set(speed);
    }
    
    public void initDefaultCommand() {
    //	setDefaultCommand(new TeleOp_Shoot());
    }
    
    public void log(){
    /*	SmartDashboard.putNumber("Bus Voltage", shooterLead.getBusVoltage());
    	SmartDashboard.putNumber("Output Voltage", shooterLead.getOutputVoltage());
    	SmartDashboard.putNumber("Output Lead Current", shooterLead.getOutputCurrent());
    	SmartDashboard.putNumber("Output Follow Current", shooterFollow.getOutputCurrent());
    	SmartDashboard.putNumber("Shooter.getSpeed", shooterLead.getSpeed());
    	SmartDashboard.putNumber("Shooter RPM", shooterRpm);
    	SmartDashboard.putNumber("Fly Wheel .get()", shooterLead.get());
    	SmartDashboard.putNumber("Fly Wheel P", shooterLead.getP());
    	SmartDashboard.putNumber("Fly Wheel I", shooterLead.getI());
    	SmartDashboard.putNumber("Fly Wheel D", shooterLead.getD());
    	SmartDashboard.putNumber("Fly Wheel Setpoint", shooterLead.getSetpoint());
    	SmartDashboard.putNumber("Encoder Position", shooterLead.getEncPosition());
    	SmartDashboard.putNumber("Fly Wheel Velocity", shooterLead.getEncVelocity());
    	SmartDashboard.putNumber("Talon Closed Loop Error", shooterLead.getClosedLoopError());
    */
    }
}