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
    	//double x = -Robot.oi.getXbox1().getRawAxis(4);
    	Robot.driveSubsystem.arcadeDriveCurved(Robot.oi.getXbox1().getRawAxis(1), Robot.oi.getXbox1().getRawAxis(4));
//    	 0.010815180689100581 - 0.7353174468956218x + 8.812554716989258x2 - 11.619421463995128x3 + 4.534123196152433x4

    	/* should work now, trying new equation
    	 * excel is giving really weird equations, they don't show the same on any other graph	
      	double x = Math.abs(Robot.oi.getXbox1().getRawAxis(4));
    	if(Robot.oi.getXbox1().getRawAxis(4) > 0){
    		Robot.driveSubsystem.arcadeDrive(Robot.oi.getXbox1().getRawAxis(1), (double)-(0.00001*(Math.pow(x, 5)) + 0.0008*(Math.pow(x, 4)) - 0.0169*(Math.pow(x, 3)) + 0.1733*(Math.pow(x,  2)) - 0.673*(x) - 0.1945));
    	}
    	else{
    		Robot.driveSubsystem.arcadeDrive(Robot.oi.getXbox1().getRawAxis(1), (double)(0.00001*(Math.pow(x, 5)) + 0.0008*(Math.pow(x, 4)) - 0.0169*(Math.pow(x, 3)) + 0.1733*(Math.pow(x,  2)) - 0.673*(x) - 0.1945));
    	}
    	*/
    	
    	/** working version 
    	Robot.driveSubsystem.arcadeDrive(Robot.oi.getXbox1().getRawAxis(1), (-Robot.oi.getXbox1().getRawAxis(4)*.75));
    	**/
    	
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
