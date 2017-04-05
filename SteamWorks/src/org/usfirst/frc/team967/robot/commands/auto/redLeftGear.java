package org.usfirst.frc.team967.robot.commands.auto;

import org.usfirst.frc.team967.robot.commands.Auto_Delay;
import org.usfirst.frc.team967.robot.commands.Auto_Drive_Distance;
import org.usfirst.frc.team967.robot.commands.Auto_Straight_Drive;
import org.usfirst.frc.team967.robot.commands.Auto_resetYaw;
import org.usfirst.frc.team967.robot.commands.PIDTurnToAngle;
import org.usfirst.frc.team967.robot.commands.TeleOp_DriveShiftHigh;
import org.usfirst.frc.team967.robot.commands.TeleOp_GearBoxSet;
import org.usfirst.frc.team967.robot.commands.ZeroEncoders;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class redLeftGear extends CommandGroup {

    public redLeftGear() {
    	addSequential(new Auto_resetYaw());
    	//reset Yaw
    	addSequential(new Auto_Delay(1));
    	
    	addSequential(new ZeroEncoders());
    	//make sure encoders are zeroed
    	addSequential(new Auto_Delay(1));
    	
    	addSequential(new TeleOp_DriveShiftHigh(false));
    	//low gear
    	addSequential(new TeleOp_GearBoxSet(false));
    	//low gear
    	addSequential(new Auto_Delay(1));
    	//wait for zero
    	addSequential(new Auto_Straight_Drive(3500, .5));//4150
    	//drive forward
    	addSequential(new Auto_resetYaw());
    	//reset Yaw
    	addSequential(new PIDTurnToAngle(180));//65
    	//turn
    	addSequential(new Auto_Delay(1));
    	
    	addSequential(new Auto_Straight_Drive(7000, .5));//850 //750
    	//drive forward
    	addSequential(new TeleOp_GearBoxSet(true));
    	//open gear box
/*    	addSequential(new Auto_Straight_Drive(4500, -.5));
    	//drive back
    	addSequential(new TeleOp_GearBoxSet(false));
    	//open gear box
    	addSequential(new PIDTurnToAngle(0));
    	//turn
    	addSequential(new TeleOp_DriveShiftHigh(true));
    	//open gear box
    	addSequential(new Auto_Straight_Drive(12000, 1));
    	//drive forward
    	
 */  	
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
