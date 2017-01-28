package org.usfirst.frc.team967.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team967.robot.RobotConstraints;
import org.usfirst.frc.team967.robot.RobotMap;
import org.usfirst.frc.team967.robot.commands.TeleOp_GearBoxClosed;
import edu.wpi.first.wpilibj.Servo;
/**
 *
 */
public class GearSubsystem extends Subsystem {
	
	private Servo gearServo;
    
    public GearSubsystem(){
    	gearServo = new Servo(RobotMap.gearServo);
    }
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void gearBoxMove(double angle){
    	gearServo.setAngle(angle);    	
    }
    public void gearBoxOpen(){
    	gearBoxMove(RobotConstraints.GearSubsystem_Servo_Open);
    }
    public void gearBoxClosed(){
    	gearBoxMove(RobotConstraints.GearSubsystem_Servo_Closed);
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new TeleOp_GearBoxClosed());
    }
    public void log(){
        SmartDashboard.putNumber("Servo Angle", gearServo.getAngle());
    }
}