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
    	addSequential(new Auto_Drive_Distance(-3800));
    	//drive forward
    	addSequential(new Auto_Drive_Distance(2000));
    	//drive back
    	addSequential(new TeleOp_GearBoxSet(false));
    	//close gear box
    	
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
