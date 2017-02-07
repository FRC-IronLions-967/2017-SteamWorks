package org.usfirst.frc.team967.robot.commands;

import org.usfirst.frc.team967.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleOp_DriveShiftHigh extends Command {
	private boolean high;
	
    public TeleOp_DriveShiftHigh(boolean High) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveSubsystem);
        high = High;
    	// eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(high){
    		Robot.driveSubsystem.shiftLow();
    	}
    	else{
    		Robot.driveSubsystem.shiftHigh();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
