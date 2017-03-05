package org.usfirst.frc.team967.robot.commands;

import org.usfirst.frc.team967.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Auto_Straight_Drive extends Command {
	
	private double counts;
	private double power;
	
    public Auto_Straight_Drive(double Distance, double Power) {
    	requires(Robot.driveSubsystem);
    	counts = Distance;
    	power = Power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.pidSetPoint(Robot.driveSubsystem.getYaw());
    	Robot.driveSubsystem.pidEnable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.move(power + Robot.driveSubsystem.PIDOutput, power + -Robot.driveSubsystem.PIDOutput);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveSubsystem.driveDistance(counts);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.zeroEncoders();
    	Robot.driveSubsystem.pidStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {

    }
}
