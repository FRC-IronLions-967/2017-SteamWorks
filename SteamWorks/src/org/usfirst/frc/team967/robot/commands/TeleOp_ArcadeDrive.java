package org.usfirst.frc.team967.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team967.robot.Robot;

/**
 *
 */
public class TeleOp_ArcadeDrive extends Command {

    public TeleOp_ArcadeDrive() {
    	requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.arcadeDriveLookUp(Robot.oi.getXbox1().getRawAxis(1), Robot.oi.getXbox1().getRawAxis(4));
//    	Robot.driveSubsystem.arcadeDriveCurved(Robot.oi.getXbox1().getRawAxis(1), Robot.oi.getXbox1().getRawAxis(4));
//    	Robot.driveSubsystem.arcadeDrive(Robot.oi.getXbox1().getRawAxis(1), (-Robot.oi.getXbox1().getRawAxis(4)*.75));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
//    	end();
    }
}
