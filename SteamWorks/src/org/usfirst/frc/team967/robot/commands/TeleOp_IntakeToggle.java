package org.usfirst.frc.team967.robot.commands;

import org.usfirst.frc.team967.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleOp_IntakeToggle extends Command {
	
	private String Toggle;

    public TeleOp_IntakeToggle(String toggle) {
        requires(Robot.intakeSubsystem);
        Toggle = toggle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Toggle == "In")Robot.intakeSubsystem.intakeIn();
    	else if (Toggle == "Out")Robot.intakeSubsystem.intakeOut();
    	else if (Toggle == "stop")Robot.intakeSubsystem.intakeMove(0);;
    	
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
    	Toggle = null;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
