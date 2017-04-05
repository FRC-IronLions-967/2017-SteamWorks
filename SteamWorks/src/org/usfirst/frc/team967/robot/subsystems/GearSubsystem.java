package org.usfirst.frc.team967.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team967.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;

/*
 *ready to be tested on the real robot 
 */

public class GearSubsystem extends Subsystem {
	
	private Boolean isOpen, isTopOpen;
	private DoubleSolenoid gearShifter, topShifter;
//	private Relay boxTop;
    
    public GearSubsystem(){
    	isOpen = false;
    	isTopOpen = false;
    	gearShifter = new DoubleSolenoid(RobotMap.PCM, RobotMap.gearOpen, RobotMap.gearClosed);
    	topShifter = new DoubleSolenoid(RobotMap.PCM, RobotMap.topOpen, RobotMap.topClosed);//RobotMap.PCM2
//    	boxTop = new Relay(0);
    }
	
    public void gearBoxOpen(){
    	isOpen = true;
    	gearShifter.set(DoubleSolenoid.Value.kForward);
    }
    public void gearBoxClosed(){
    	isOpen = false;
    	gearShifter.set(DoubleSolenoid.Value.kReverse);
    }
    public void toggleBox(){
    	if(isOpen){
    		gearBoxClosed();
    	}
    	else{
    		gearBoxOpen();
    	}
    }
    public boolean isOpen(){
    	return isOpen;
    }
    public void gearTopOpen(){
    	isTopOpen = true;
    	topShifter.set(DoubleSolenoid.Value.kForward);
//    	boxTop.set(Relay.Value.kForward);
    }
    public void gearTopClosed(){
    	isTopOpen = false;
    	topShifter.set(DoubleSolenoid.Value.kReverse);
//    	boxTop.set(Relay.Value.kReverse);
    }
    public void toggleTop(){
    	if(isTopOpen){
    		gearTopClosed();
    	}
    	else{
    		gearTopOpen();
    	}
    }
    public boolean isTopOpen(){
    	return isTopOpen;
    }

    public void initDefaultCommand() {
    	//setDefaultCommand(new TeleOp_GearBoxClosed());
    }
    public void log(){
    /*
    	SmartDashboard.putBoolean("GearBox Open", isOpen);
        SmartDashboard.putBoolean("GearBoxTop Open", isTopOpen);
     */    
    }
}