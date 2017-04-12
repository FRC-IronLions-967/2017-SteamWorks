package org.usfirst.frc.team967.robot.commands;

import org.usfirst.frc.team967.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Auto_CheckCamera extends Command {

    public Auto_CheckCamera() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	requires(Robot.cameraSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.cameraSubsystem.updatePi();
    	if(Robot.cameraSubsystem.TurnRight){
    		Robot.driveSubsystem.move(-.2, .2);
    	}
    	else if(Robot.cameraSubsystem.TurnLeft){
    		Robot.driveSubsystem.move(.2, -.2);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.cameraSubsystem.checkCamera();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.move(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
