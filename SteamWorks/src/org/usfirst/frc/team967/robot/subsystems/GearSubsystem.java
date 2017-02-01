package org.usfirst.frc.team967.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team967.robot.RobotConstraints;
import org.usfirst.frc.team967.robot.RobotMap;
import org.usfirst.frc.team967.robot.commands.TeleOp_GearBoxClosed;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
/**
 *
 */
public class GearSubsystem extends Subsystem {
	
	public Boolean isOpen;
	private DoubleSolenoid gearShifter;
    
    public GearSubsystem(){
    	isOpen = false;
    	gearShifter = new DoubleSolenoid(RobotMap.gearClosed, RobotMap.gearOpen);
    }
	
    public void gearBoxOpen(){
    	isOpen = true;
    	gearShifter.set(DoubleSolenoid.Value.kForward);
    }
    public void gearBoxClosed(){
    	isOpen = false;
    	gearShifter.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void initDefaultCommand() {
    	//setDefaultCommand(new TeleOp_GearBoxClosed());
    }
    public void log(){
        SmartDashboard.putBoolean("Open", isOpen);
    }
}