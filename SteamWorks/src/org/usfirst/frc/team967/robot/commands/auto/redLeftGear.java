package org.usfirst.frc.team967.robot.commands.auto;

import org.usfirst.frc.team967.robot.RobotConstraints;
import org.usfirst.frc.team967.robot.commands.Auto_Delay;
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
    	addSequential(new ZeroEncoders());
    	//make sure encoders are zeroed
    	addSequential(new TeleOp_DriveShiftHigh(false));
    	//low gear
    	addSequential(new TeleOp_GearBoxSet(false));
    	//low gear
    	addSequential(new Auto_Straight_Drive(-4150, RobotConstraints.Auto_Speed_Half));//4150
    	//drive forward
    	addSequential(new PIDTurnToAngle(65));//65
    	//turn
    	addSequential(new ZeroEncoders());
    	
    	addSequential(new Auto_Straight_Drive(-750, RobotConstraints.Auto_Speed_Half));//850 //750
    	//drive forward
    	addSequential(new TeleOp_GearBoxSet(true));
    	//open gear box
    	addSequential(new ZeroEncoders());
    	
    	addSequential(new Auto_Straight_Drive(2500, RobotConstraints.Auto_Speed_Half));
    	//drive back
    	addSequential(new TeleOp_GearBoxSet(false));
    	//open gear box
    	addSequential(new PIDTurnToAngle(0));
    	//turn
    	addSequential(new TeleOp_DriveShiftHigh(true));
    	//open gear box
    	addSequential(new ZeroEncoders());
    	
    	addSequential(new Auto_Straight_Drive(-12000, RobotConstraints.Auto_Speed_Full));
    	//drive forward
    	
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
