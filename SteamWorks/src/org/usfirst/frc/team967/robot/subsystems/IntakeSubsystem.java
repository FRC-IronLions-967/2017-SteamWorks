package org.usfirst.frc.team967.robot.subsystems;

import org.usfirst.frc.team967.robot.RobotMap;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 *Ready to be tested on the real robot.
 */

public class IntakeSubsystem extends Subsystem {

	private CANTalon intakeLead, lowerArm;
	//public CANTalon IntakeFollow;
	private DoubleSolenoid upperArm;
//	public DoubleSolenoid lowerArm;
	private DigitalInput lowerArmLimit;
	
	private boolean UpperExtended;
//	private boolean LowerExtended;
	private int encoderIntakeTimer = 0;
	
	public IntakeSubsystem(){
		intakeLead = new CANTalon(RobotMap.intakeWheelLead);
		lowerArm = new CANTalon(RobotMap.intakeLowerArmLead);
		//IntakeFollow = new CANTalon(RobotMap.driveLeftFollow);
		upperArm = new DoubleSolenoid(RobotMap.PCM, RobotMap.intakeShifterUpperIn, RobotMap.intakeShifterUpperOut);
//		lowerArm = new DoubleSolenoid(RobotMap.PCM, RobotMap.intakeLowerIn, RobotMap.intakeLowerOut);
		lowerArmLimit = new DigitalInput(RobotMap.intakeLimitSwitch);
		
		UpperExtended = false;
//		LowerExtended = false;
		lowerArm.changeControlMode(TalonControlMode.PercentVbus);
		lowerArm.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
//		shooterLead.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		
//		lowerArm.reverseSensor(false);
//		lowerArm.reverseOutput(true);
		lowerArm.configEncoderCodesPerRev(12); 
//		lowerArm.configNominalOutputVoltage(+0.0f, -0.0f);
//		lowerArm.configPeakOutputVoltage(+12.0f, 0.0f);//+12.0f, -12.0f

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
/*
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
	*/
	public void lowerArmsMove(double power){
		lowerArm.set(-power);
	}
	public void lowerArmsDown(){
		lowerArmsMove(1);
	}
	public void lowerArmsUp(){
		lowerArmsMove(-1);
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
	public void bringLowerUp(){
		if(!lowerArmLimit.get()){
			lowerArmsMove(-.5);
		}
		else{
			lowerArmsMove(-.5);
		}
	}
	public boolean zeroEncoder(){	
		if(encoderIntakeTimer > 10){
			encoderIntakeTimer = 0;
			return true;
		}
		else if(encoderIntakeTimer == 1){
			lowerArm.setEncPosition(0);
			encoderIntakeTimer ++;
			return false;
		}
		else{
			encoderIntakeTimer ++;
			return false;
		}
	}
	public boolean lowerArmToCount(int count){
		if(count - 50 > lowerArm.getEncPosition()){
			lowerArmsMove(-.5);
			return false;
		}
		else if(count + 50 < lowerArm.getEncPosition()){
			lowerArmsMove(.5);
			return false;
		}
		else{
			lowerArmsMove(0);
			return true;
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void log(){
    	SmartDashboard.putBoolean("UpperExtended", UpperExtended);
//    	SmartDashboard.putBoolean("LowerExtended", LowerExtended);
    }
}