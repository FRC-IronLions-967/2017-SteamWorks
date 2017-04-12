package org.usfirst.frc.team967.robot.commands.auto;

import org.usfirst.frc.team967.robot.RobotConstraints;
import org.usfirst.frc.team967.robot.commands.Auto_Drive_Distance;
import org.usfirst.frc.team967.robot.commands.Auto_Straight_Drive;
import org.usfirst.frc.team967.robot.commands.Auto_resetYaw;
import org.usfirst.frc.team967.robot.commands.PIDTurnToAngle;
import org.usfirst.frc.team967.robot.commands.TeleOp_DriveShiftHigh;
import org.usfirst.frc.team967.robot.commands.TeleOp_GearBoxSet;
import org.usfirst.frc.team967.robot.commands.TeleOp_Shoot;
import org.usfirst.frc.team967.robot.commands.TeleOp_ShooterFeed;
import org.usfirst.frc.team967.robot.commands.ZeroEncoders;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class redRightShoot extends CommandGroup {

    public redRightShoot() {
    	addSequential(new Auto_resetYaw());
    	//reset Yaw
    	addSequential(new TeleOp_DriveShiftHigh(false));
    	//low gear
    	addSequential(new TeleOp_GearBoxSet(false));
    	//low gear
    	addSequential(new ZeroEncoders());
    	//make sure encoders are zero
    	addSequential(new Auto_Straight_Drive(-3900, RobotConstraints.Auto_Speed_Half));
//    	addSequential(new Auto_Drive_Distance(-3900, .75));//3900//3850 on blue left gear
    	//drive forward
    	addSequential(new Auto_resetYaw());
    	//reset Yaw
    	addSequential(new PIDTurnToAngle(-RobotConstraints.Auto_Angle_AirShip));
    	//turn
    	addSequential(new ZeroEncoders());
    	//make sure encoders are zero
    	addSequential(new Auto_Straight_Drive(-1050, RobotConstraints.Auto_Speed_Half));
    	//drive forward
    	addSequential(new TeleOp_GearBoxSet(true));
    	//open gear box
    	addSequential(new Auto_Straight_Drive(1500, RobotConstraints.Auto_Speed_Half));
    	//drive back
    	addSequential(new TeleOp_GearBoxSet(false));
    	//close gear box
    	addSequential(new PIDTurnToAngle(0));
    	//turn
    	addSequential(new Auto_Straight_Drive(600, RobotConstraints.Auto_Speed_Half));
    	//drive back
    	addParallel(new TeleOp_Shoot());
    	//shoot
    	addSequential(new PIDTurnToAngle(-50));
    	//turn
    	addSequential(new Auto_Straight_Drive(3500, RobotConstraints.Auto_Speed_Half));
    	//drive back
    	addSequential(new TeleOp_ShooterFeed());
    	
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
