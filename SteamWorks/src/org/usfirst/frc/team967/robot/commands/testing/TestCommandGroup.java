package org.usfirst.frc.team967.robot.commands.testing;

import org.usfirst.frc.team967.robot.Robot;
import org.usfirst.frc.team967.robot.commands.Auto_Drive_Distance;
import org.usfirst.frc.team967.robot.commands.PIDTurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestCommandGroup extends CommandGroup {
	private double c, a;
	private int b;
    public TestCommandGroup() {
    	if(Robot.cameraSubsystem.AutoButtonValue()){
       		c = 1500;
    		b = 90;
    		a = 1500;
    	}else{
    		c = 3000;
    		b = 180;
    		a = 500;
    	}
    	addSequential(new Auto_Drive_Distance(c));
    	addSequential(new PIDTurnToAngle(b));
    	addSequential(new Auto_Drive_Distance(a));
        
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
