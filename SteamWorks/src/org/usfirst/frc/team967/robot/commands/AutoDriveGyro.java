package org.usfirst.frc.team967.robot.commands;

import org.usfirst.frc.team967.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveGyro extends Command {
	double distance;
	double power;
    public AutoDriveGyro(double Distance, double Power) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	power = Power;
    	distance = Distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.pidSetPoint(Robot.driveSubsystem.getYaw());
    	if(distance > 0){
    		Robot.driveSubsystem.arcadeDrive(-power, 0);
    	}
    	else{
    		Robot.driveSubsystem.arcadeDrive(power, 0);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.move(power + Robot.driveSubsystem.PIDOutput, power-Robot.driveSubsystem.PIDOutput+.02);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveSubsystem.driveDistance(distance);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
