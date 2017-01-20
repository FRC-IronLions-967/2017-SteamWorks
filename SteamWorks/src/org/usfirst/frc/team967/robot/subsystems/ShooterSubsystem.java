package org.usfirst.frc.team967.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import org.usfirst.frc.team967.robot.RobotConstraints;
import org.usfirst.frc.team967.robot.RobotMap;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {
    
    private CANTalon shooterLead;
    private CANTalon shooterFollow;
    
//    private final int shooterLead_Profile = RobotConstraints.ShooterSubsystem_Shooter_Profile;
//    private final double shooterLead_P = RobotConstraints.ShooterSubsystem_Shooter_P;
//    private final double shooterLead_I = RobotConstraints.ShooterSubsystem_Shooter_I;
//    private final double shooterLead_D = RobotConstraints.ShooterSubsystem_Shooter_D;
//    private final double shooterLead_F = RobotConstraints.ShooterSubsystem_Shooter_F;
//    private final double shooterSpeed = RobotConstraints.ShooterSubsystem_ShooterSpeed;
    
    public void Shoot(){
    	shooterLead = new CANTalon(45);
		shooterLead = new CANTalon(46);
    	shooterFollow.changeControlMode(TalonControlMode.Follower);
		shooterFollow.set(shooterLead.getDeviceID());
    	
    	shooterLead.changeControlMode(TalonControlMode.Speed);
    	shooterLead.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	shooterLead.reverseSensor(false);
    	shooterLead.configEncoderCodesPerRev(12);//Needs to be checked with sensors. 
    	shooterLead.configNominalOutputVoltage(+0.0f, -0.0f);
    	shooterLead.configPeakOutputVoltage(+12.0f, -12.0f);
    	shooterLead.setProfile(1);//1
    	shooterLead.setP(1);//1
    	shooterLead.setI(0);//1
    	shooterLead.setD(0);
    	shooterLead.setF(0);// www.chiefdelphi.com/forums/showthread.php?t=142381
    	
    	shooterLead.setSetpoint(0);
    }
    
    public void StopShooter(){
    	shooterLead.setSetpoint(0);
    }
     
    public void initDefaultCommand() {
//    	setDefaultCommand(new ());
    }
    
    public void log(){
    	SmartDashboard.getNumber("Fly Wheel Speed", shooterLead.get());
    	SmartDashboard.getNumber("Fly Wheel P", shooterLead.getP());
    	SmartDashboard.getNumber("Fly Wheel I", shooterLead.getI());
    	SmartDashboard.getNumber("Fly Wheel D", shooterLead.getD());
    	SmartDashboard.getNumber("Fly Wheel Setpoint", shooterLead.getSetpoint());
    	SmartDashboard.getNumber("position", shooterLead.getEncPosition());
    	SmartDashboard.getNumber("Fly Wheel Velocity", shooterLead.getEncVelocity());
    }
}

