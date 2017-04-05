package org.usfirst.frc.team967.robot.commands;

import org.usfirst.frc.team967.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
	
public class Auto_Drive_Distance extends Command {
	private double counts;
	private double power;
	
    public Auto_Drive_Distance(double distance, double Power) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	counts = distance;
    	power = Power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.driveSubsystem.zeroEncoders();
    	if(counts > 0){
    		Robot.driveSubsystem.arcadeDrive(-power, 0);
    	}
    	else{
    		Robot.driveSubsystem.arcadeDrive(power, 0);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.driveDistance(counts, power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveSubsystem.driveDistance(counts, power);
    }

    // Called once after isFinished returns true
    protected void end() {
//    	Robot.driveSubsystem.zeroEncoders();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
