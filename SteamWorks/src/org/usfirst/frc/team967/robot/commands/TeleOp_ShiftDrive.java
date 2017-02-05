package org.usfirst.frc.team967.robot.commands;

import org.usfirst.frc.team967.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleOp_ShiftDrive extends Command {

	private String Position;
	
    public TeleOp_ShiftDrive(String position) {
        requires(Robot.driveSubsystem);
        Position = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Position == "Low") Robot.driveSubsystem.shiftLow();
    	else if (Position == "High") Robot.driveSubsystem.shiftHigh();
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
    	Position = null;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
