package org.usfirst.frc.team967.robot.commands;

import org.usfirst.frc.team967.robot.Robot;
import org.usfirst.frc.team967.robot.RobotConstraints;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleOp_ClimbToggle extends Command {

	private String Toggle;
	
    public TeleOp_ClimbToggle(String toggle) {
        requires(Robot.climberSubsystem);
        Toggle = toggle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Toggle == "Start") Robot.climberSubsystem.climb(RobotConstraints.ClimberSubsystem_ClimberSpeed);
    	else if (Toggle == "Stop") Robot.climberSubsystem.climb(0);
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
