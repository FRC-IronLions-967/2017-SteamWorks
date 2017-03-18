package org.usfirst.frc.team967.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team967.robot.commands.*;

/**
 *
 */
public class blueCenterGear extends CommandGroup {

    public blueCenterGear() {
    	addSequential(new TeleOp_DriveShiftHigh(false));
    	//low gear
    	addSequential(new TeleOp_GearBoxSet(true));
    	//open gear box
    	addSequential(new Auto_Drive_Distance(-3700, .75));
    	//drive forward
    	addSequential(new Auto_Drive_Distance(2000, .75));
    	//drive back
    	addSequential(new TeleOp_GearBoxSet(false));
    	//close gear box
    	addSequential(new PIDTurnToAngle(90));
    	//turn 90
    	addSequential(new TeleOp_DriveShiftHigh(true));
    	//high gear
    	addSequential(new Auto_Drive_Distance(-4000, .75));
    	//drive back
    	addSequential(new TeleOp_DriveShiftHigh(false));
    	//low gear
    	addSequential(new PIDTurnToAngle(0));
    	//turn 0
    	addSequential(new TeleOp_DriveShiftHigh(true));
    	//high gear
    	addSequential(new Auto_Drive_Distance(-12000, 1));
    	//drive back
    	
    	
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
