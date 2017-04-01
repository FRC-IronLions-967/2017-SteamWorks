package org.usfirst.frc.team967.robot.subsystems;

import org.usfirst.frc.team967.robot.RobotMap;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 *Ready to be tested on the real robot.
 */

public class IntakeSubsystem extends Subsystem {

	public CANTalon intakeLead;
	//public CANTalon IntakeFollow;
	public DoubleSolenoid upperArm;
	public DoubleSolenoid lowerArm;
	
	private boolean UpperExtended;
	private boolean LowerExtended;
	
	public IntakeSubsystem(){
		intakeLead = new CANTalon(RobotMap.intake);
		//IntakeFollow = new CANTalon(RobotMap.driveLeftFollow);
		upperArm = new DoubleSolenoid(RobotMap.PCM, RobotMap.intakeUpperIn, RobotMap.intakeUpperOut);
		lowerArm = new DoubleSolenoid(RobotMap.PCM2, RobotMap.intakeLowerIn, RobotMap.intakeLowerOut);//RobotMap.PCM
		
		UpperExtended = false;
		LowerExtended = false;
		
		intakeLead.changeControlMode(TalonControlMode.PercentVbus);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void shiftUpperIn() {
	    UpperExtended = false;
	    upperArm.set(DoubleSolenoid.Value.kForward);
	}
	public void shiftUpperOut() {
	    UpperExtended = true;
	    upperArm.set(DoubleSolenoid.Value.kReverse);
	}
	public void upperToggle(){
		if(UpperExtended){
			shiftUpperIn();
		}
		else{
			shiftUpperOut();
		}
	}
	public void shiftLowerIn() {
	    LowerExtended = false;
	    lowerArm.set(DoubleSolenoid.Value.kForward);
	}
	public void shiftLowerOut() {
	    LowerExtended = true;
	    lowerArm.set(DoubleSolenoid.Value.kReverse);
	}
	public void lowerToggle(){
		if(LowerExtended){
			shiftLowerIn();
		}
		else{
			shiftLowerOut();
		}
	}
	
	public void intakeMove(double power){
		intakeLead.set(-power);
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
    	SmartDashboard.putBoolean("UpperExtended", UpperExtended);
    	SmartDashboard.putBoolean("LowerExtended", LowerExtended);
    }
}