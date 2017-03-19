package org.usfirst.frc.team967.robot.commands.testing;

import org.usfirst.frc.team967.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDrive3_4Turn extends Command {

    public ArcadeDrive3_4Turn() {
    	requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.arcadeDrive(0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    /*	double x = Math.abs(Robot.oi.getXbox1().getRawAxis(4));
    	if(x > 0){
    		Robot.driveSubsystem.arcadeDrive(Robot.oi.getXbox1().getRawAxis(1), (double)((.00002*(Math.pow(x, 6))) + 0.0008*(Math.pow(x, 5)) - 0.0111*(Math.pow(x, 4)) + 0.0641*(Math.pow(x, 3)) - 0.1305*(Math.pow(x,  2)) + 0.0923*(x)));
    	}
    	else{
    		Robot.driveSubsystem.arcadeDrive(Robot.oi.getXbox1().getRawAxis(1), (double)((.00002*(Math.pow(x, 6))) + 0.0008*(Math.pow(x, 5)) - 0.0111*(Math.pow(x, 4)) + 0.0641*(Math.pow(x, 3)) - 0.1305*(Math.pow(x,  2)) + 0.0923*(x)));
    	}*/
    	Robot.driveSubsystem.arcadeDrive(Robot.oi.getXbox1().getRawAxis(1), (-Robot.oi.getXbox1().getRawAxis(4)*.75));
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
