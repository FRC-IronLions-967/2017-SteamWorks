package org.usfirst.frc.team967.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team967.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/*
 *ready to be tested on the real robot 
 */

public class GearSubsystem extends Subsystem {
	
	public Boolean isOpen;
	private DoubleSolenoid gearShifter;
    
    public GearSubsystem(){
    	isOpen = false;
    	gearShifter = new DoubleSolenoid(RobotMap.PCM, RobotMap.gearOpen, RobotMap.gearClosed);
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
    
    public void initDefaultCommand() {
    	//setDefaultCommand(new TeleOp_GearBoxClosed());
    }
    public void log(){
        SmartDashboard.putBoolean("Open", isOpen);
    }
}