package org.usfirst.frc.team967.robot.commands;

import org.usfirst.frc.team967.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
	
public class Auto_Drive_Distance extends Command {
	private double counts;
	
    public Auto_Drive_Distance(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	counts = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.zeroEncoders();
    	Robot.driveSubsystem.arcadeDrive(-.75, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.driveDistance(counts);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveSubsystem.driveDistance(counts);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.zeroEncoders();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
