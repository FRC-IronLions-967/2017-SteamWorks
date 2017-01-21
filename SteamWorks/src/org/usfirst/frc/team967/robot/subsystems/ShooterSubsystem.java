package org.usfirst.frc.team967.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import org.usfirst.frc.team967.robot.RobotConstraints;
import org.usfirst.frc.team967.robot.RobotMap;
import org.usfirst.frc.team967.robot.commands.TeleOp_Shoot;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {
    
    private CANTalon shooterLead;
    private CANTalon shooterFollow;
    
    int shooterRpm = 200;
	int incrementVal = 50;
    
	double pValue = 1.0;
	double iValue = 0.0;
	double dValue = 0.00;
	
//    private final int shooterLead_Profile = RobotConstraints.ShooterSubsystem_Shooter_Profile;
//    private final double shooterLead_P = RobotConstraints.ShooterSubsystem_Shooter_P;
//    private final double shooterLead_I = RobotConstraints.ShooterSubsystem_Shooter_I;
//    private final double shooterLead_D = RobotConstraints.ShooterSubsystem_Shooter_D;
//    private final double shooterLead_F = RobotConstraints.ShooterSubsystem_Shooter_F;
//    private final double shooterSpeed = RobotConstraints.ShooterSubsystem_ShooterSpeed;
    
	public ShooterSubsystem(){
		shooterLead = new CANTalon(6);
		shooterFollow = new CANTalon(5);

		shooterLead.changeControlMode(TalonControlMode.Speed);
    	shooterLead.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	shooterLead.reverseSensor(false);
    	shooterLead.configEncoderCodesPerRev(12);//Needs to be checked with sensors. 
    	shooterLead.configNominalOutputVoltage(+0.0f, -0.0f);
    	shooterLead.configPeakOutputVoltage(+12.0f, -12.0f);//+12.0f, -12.0f
    	shooterLead.setProfile(1);
    	shooterLead.setP(pValue);//
    	shooterLead.setI(iValue);//
    	shooterLead.setD(dValue);//
    	shooterLead.setF(.0);// www.chiefdelphi.com/forums/showthread.php?t=142381
		shooterFollow.changeControlMode(TalonControlMode.Follower);
		shooterFollow.set(shooterLead.getDeviceID());    	
    }
	
	public void Shoot(){
    	shooterLead.set(shooterRpm);//setSetpoint
    }
	
	public void PUp(){
		iValue +=  .01;
		shooterLead.setI(iValue);
//		Shoot();
	}
	public void PDown(){
		iValue -= .01;
		shooterLead.setI(iValue);
//		Shoot();
	}
    
    public void StopShooter(){
    	shooterLead.set(0);//setSetpoint
    }
    
    public void ShootSpeedUp(){
    //	shooterRpm = shooterRpm + incrementVal;
    }
    
    public void ShootSpeedDown(){
    //	shooterRpm = shooterRpm - incrementVal;
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new TeleOp_Shoot());
    }
    
    public void log(){
    	SmartDashboard.putNumber("Bus Voltage", shooterLead.getBusVoltage());
    	SmartDashboard.putNumber("Output Voltage", shooterLead.getOutputVoltage());
    	SmartDashboard.putNumber("Output Current", shooterLead.getOutputCurrent());

    	SmartDashboard.putNumber("Shooter RPM", shooterRpm);
    	SmartDashboard.putNumber("Fly Wheel Speed", shooterLead.get());
    	SmartDashboard.putNumber("Fly Wheel P", shooterLead.getP());
    	SmartDashboard.putNumber("Fly Wheel I", shooterLead.getI());
    	SmartDashboard.putNumber("Fly Wheel D", shooterLead.getD());
    	SmartDashboard.putNumber("Fly Wheel Setpoint", shooterLead.getSetpoint());
    	SmartDashboard.putNumber("Encoder Position", shooterLead.getEncPosition());
    	SmartDashboard.putNumber("Fly Wheel Velocity", shooterLead.getEncVelocity());
    	SmartDashboard.putNumber("Talon Closed Loop Error", shooterLead.getClosedLoopError());
    }
}