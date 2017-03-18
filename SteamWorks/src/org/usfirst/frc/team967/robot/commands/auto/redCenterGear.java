package org.usfirst.frc.team967.robot.commands.auto;

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
public class redCenterGear extends CommandGroup {

    public redCenterGear() {
    	addSequential(new Auto_resetYaw());
    	//reset Yaw
    	addSequential(new ZeroEncoders());
    	//make sure encoders are zeroed
    	addSequential(new TeleOp_DriveShiftHigh(false));
    	//low gear
    	addSequential(new TeleOp_GearBoxSet(false));
    	//open gear box
    	addSequential(new Auto_Straight_Drive(-3700, .75));
    	//drive forward
    	addSequential(new TeleOp_GearBoxSet(true));
    	//close gear box
    	addSequential(new Auto_Straight_Drive(2000, .75));
    	//drive back
    	addSequential(new TeleOp_GearBoxSet(false));
    	//close gear box
    	addSequential(new PIDTurnToAngle(-90));//-90
    	//turn 90
    	addSequential(new TeleOp_DriveShiftHigh(true));
    	//high gear
    	addSequential(new Auto_Straight_Drive(-3500, .75));
    	//drive back
    	addSequential(new TeleOp_DriveShiftHigh(false));
    	//low gear
    	addSequential(new PIDTurnToAngle(0));//0
    	//turn straight
    	addSequential(new TeleOp_DriveShiftHigh(true));
    	//high gear
    	addSequential(new Auto_Straight_Drive(-12000, 1));
    	//drive back
    	
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
