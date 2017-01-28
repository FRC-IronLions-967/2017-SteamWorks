package org.usfirst.frc.team967.robot.subsystems;

import org.usfirst.frc.team967.robot.RobotMap;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

	public CANTalon intakeLead;
	//public CANTalon IntakeFollow;
	public DoubleSolenoid upperArm;
	public DoubleSolenoid lowerArm;
	
	private boolean UpperExtended;
	private boolean LowerExtended;
	
	public IntakeSubsystem(){
		intakeLead = new CANTalon(RobotMap.intakeLead);
		//IntakeFollow = new CANTalon(RobotMap.driveLeftFollow);
		upperArm = new DoubleSolenoid(RobotMap.intakeUpperIn, RobotMap.intakeUpperOut);
		lowerArm = new DoubleSolenoid(RobotMap.intakeLowerIn, RobotMap.intakeLowerOut);
		
		UpperExtended = false;
		LowerExtended = false;
		
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void shiftUpperIn() {
	    UpperExtended = false;
	    upperArm.set(DoubleSolenoid.Value.kReverse);
	}
	public void shiftUpperOut() {
	    UpperExtended = true;
	    upperArm.set(DoubleSolenoid.Value.kForward);
	}
	
	public void shiftLowerIn() {
	    LowerExtended = false;
	    lowerArm.set(DoubleSolenoid.Value.kReverse);
	}
	public void shiftLowerOut() {
	    LowerExtended = true;
	    lowerArm.set(DoubleSolenoid.Value.kForward);
	}
	
	public void intakeMove(double power){
		intakeLead.set(power);
	}
	public void intakeIn(){
		intakeMove(1);
	}
	public void intakeOut(){
		intakeMove(-1);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void log(){
    	
    }
}

