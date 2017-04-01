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
    	Robot.driveSubsystem.zeroEncoders();
    	if(counts > 0){
    		power = -power;
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.pidSetPoint(Robot.driveSubsystem.getYaw());
    	Robot.driveSubsystem.zeroEncoders();
    	Robot.driveSubsystem.pidEnable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.move(power + 2*Robot.driveSubsystem.PIDOutput, power + -2*Robot.driveSubsystem.PIDOutput);
    //	Robot.driveSubsystem.driveDistance(counts);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveSubsystem.driveDistance(counts);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.zeroEncoders();
    	Robot.driveSubsystem.pidStop();
    	Robot.driveSubsystem.move(0, 0);
    	Robot.driveSubsystem.countsmeet = true;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {

    }
}
