package org.usfirst.frc.team967.robot.commands;

import org.usfirst.frc.team967.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDTurnToAngle extends Command {

	private double Angle;
	
    public PIDTurnToAngle(double angle) {
    	requires(Robot.driveSubsystem);
    	Angle = angle;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.pidSetPoint(Angle);
    	Robot.driveSubsystem.pidEnable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.move(Robot.driveSubsystem.PIDOutput, -Robot.driveSubsystem.PIDOutput);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveSubsystem.pidDone();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.pidStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
