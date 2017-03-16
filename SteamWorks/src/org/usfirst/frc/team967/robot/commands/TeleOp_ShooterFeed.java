package org.usfirst.frc.team967.robot.commands;

import org.usfirst.frc.team967.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleOp_ShooterFeed extends Command {
	
	private double Speed;
	
    public TeleOp_ShooterFeed(double speed) {
    	requires(Robot.shooterSubsystem);
    	Speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.shooterSubsystem.FeedShooter(Speed);
    	Robot.shooterSubsystem.FeedPIDShooter();
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
