package org.usfirst.frc.team967.robot.commands;

import org.usfirst.frc.team967.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Auto_Delay extends Command {
	private double time;
	
    public Auto_Delay(double Time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		time = Time;
    	requires(Robot.cameraSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.cameraSubsystem.delayTime(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.cameraSubsystem.getTimeDone();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
