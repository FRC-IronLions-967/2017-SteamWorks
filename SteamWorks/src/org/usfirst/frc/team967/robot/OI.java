package org.usfirst.frc.team967.robot;

import org.usfirst.frc.team967.robot.commands.*;
import org.usfirst.frc.team967.robot.commands.testing.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {
    private Joystick xbox1 = new Joystick(1);
    private Joystick customBox = new Joystick(0);
    
    double leftTrigger;
    double rightTrigger;			
    
    public AxisButton xbox1_lT = new AxisButton(xbox1, 2, .75, 1);
    public AxisButton xbox1_RT = new AxisButton(xbox1, 3, .75, 1);

    public POVButton xbox1povN 	= new POVButton(xbox1, 0, 0);
    public POVButton xbox1povNE = new POVButton(xbox1, 0, 45);
    public POVButton xbox1povE 	= new POVButton(xbox1, 0, 90);
    public POVButton xbox1povSE = new POVButton(xbox1, 0, 135);
    public POVButton xbox1povS 	= new POVButton(xbox1, 0, 180);
    public POVButton xbox1povSW = new POVButton(xbox1, 0, 225);
    public POVButton xbox1povW 	= new POVButton(xbox1, 0, 270);
    public POVButton xbox1povNW = new POVButton(xbox1, 0, 315);
    
    public OI() {
    	
    	//*******************************************************************
    	// Setting up the variables to the buttons on controller 1
    	JoystickButton xbox1_a = new JoystickButton(xbox1, 1);
    	JoystickButton xbox1_b = new JoystickButton(xbox1, 2);
    	JoystickButton xbox1_x = new JoystickButton(xbox1, 3);
    	JoystickButton xbox1_y = new JoystickButton(xbox1, 4);
    	JoystickButton xbox1_lb = new JoystickButton(xbox1, 5);
    	JoystickButton xbox1_rb = new JoystickButton(xbox1, 6);
    	JoystickButton xbox1_back = new JoystickButton(xbox1, 7);
    	JoystickButton xbox1_start = new JoystickButton(xbox1, 8);
    	JoystickButton xbox1_leftStickButton = new JoystickButton(xbox1, 9);
    	JoystickButton xbox1_rightStickButton = new JoystickButton(xbox1, 10);
    	
    	//xbox1.getPOV();//0=north, 90=east, 180=south, 45=NE, ect.
    	
    	//**********************************************************************
    	// Setting up the variables to the custom button box
    	JoystickButton customBox1 = new JoystickButton(customBox, 1);
    	JoystickButton customBox2 = new JoystickButton(customBox, 2);
    	JoystickButton customBox3 = new JoystickButton(customBox, 3);
    	JoystickButton customBox4 = new JoystickButton(customBox, 4);
    	JoystickButton customBox5 = new JoystickButton(customBox, 5);
    	JoystickButton customBox6 = new JoystickButton(customBox, 6);
    	JoystickButton customBox7 = new JoystickButton(customBox, 7);
    	JoystickButton customBox8 = new JoystickButton(customBox, 8);
    	    	
    	//*********************************************************************
    	//Setting the button variables to the commands for controller number 1
    	
<<<<<<< HEAD
    	xbox1_a.whenPressed(new PIDTurnToAngle(180));
     	xbox1_b.whenPressed(new PIDTurnToAngle(90));
     	xbox1_x.whenPressed(new PIDTurnToAngle(-90));
     	xbox1_y.whenPressed(new PIDTurnToAngle(0));
     	xbox1_lT.whenPressed(new TeleOp_ShiftDrive("High"));
     	xbox1_lT.whenPressed(new TeleOp_ShiftDrive("Low"));
=======
    	xbox1_a.whenPressed(new Auto_Drive_Distance(50));
     	xbox1_b.whenPressed(new Auto_Drive_Distance(100));
     	xbox1_x.whenPressed(new Auto_Drive_Distance(500));
     	xbox1_y.whenPressed(new Auto_Drive_Distance(1000));
>>>>>>> refs/remotes/origin/master
//      xbox1_lb.whenPressed(new command);
//    	xbox1_rb.whenPressed(new command);
//    	xbox1_back.whenPressed(new command);
//    	xbox1_back.whenReleased(new command);
//    	xbox1_a.whenReleased(new TeleOp_shootSpeedUp());
//    	xbox1_a.whenPressed(new TeleOp_ShiftDriveHigh());
//    	xbox1_b.whenPressed(new TeleOp_ShootSpeedDown());
//    	xbox1_b.whenReleased(new TeleOp_StopShooting());
//    	xbox1_x.whenPressed(new TeleOp_shootSpeedDown());
//    	xbox1_y.whenPressed(new TeleOp_ShootSpeedDown());
//    	xbox1_lb.whenPressed(new command);
//    	xbox1_rb.whenPressed(new command);
    	xbox1_back.whenPressed(new testOutputOn());
    	xbox1_back.whenReleased(new testOutputOff());
//    	xbox1_start.whenPressed(new command);
//    	xbox1_start.whenReleased(new command);
//    	xbox1_leftStickButton.whenPressed(new command);
//    	xbox1_rightStickButton.whenPressed(new command);
//
    	//**********************************************************************
    	//Setting the button variables to the commands for custom box
//    	customBox1.whenPressed(new TeleOp_Shooter_Feed(1));
//    	customBox1.whenReleased(new TeleOp_Shooter_Feed(0));
//    	customBox2.whenPressed(new TeleOp_Intake_In());
//    	customBox2.whenReleased(new TeleOp_Intake_Stop());
//    	customBox3.whenPressed(new TeleOp_Intake_Out());
//    	customBox3.whenReleased(new TeleOp_Intake_Stop());
//    	customBox4.whenPressed(new command);
    	customBox1.whenPressed(new TeleOp_Shooter_Feed(1));
    	customBox1.whenReleased(new TeleOp_Shooter_Feed(0));
    	customBox2.whenPressed(new TeleOp_Intake_In());
    	customBox2.whenReleased(new TeleOp_Intake_Stop());
    	customBox3.whenPressed(new TeleOp_Intake_Out());
    	customBox3.whenReleased(new TeleOp_Intake_Stop());
//    	customBox4.whenPressed(new TeleOp_ShiftDriveHigh());
//    	customBox4.whenReleased(new command);
//    	customBox5.whenPressed(new TeleOp_ShiftDriveLow());
//    	customBox5.whenReleased(new command);
//    	customBox6.whenPressed(new command);
//    	customBox6.whenReleased(new command);
//    	customBox7.whenPressed(new command);
//    	customBox7.whenReleased(new command);
//    	customBox8.whenPressed(new command);
//    	customBox8.whenReleased(new command);
//    	customBox.setOutput(2, true);
    	
//    	SmartDashboard.putData("P Up", new Testing_P_up());
//    	SmartDashboard.putData("P down", new Testing_P_down());
//    	SmartDashboard.putData("I Up", new Testing_I_up());
//    	SmartDashboard.putData("I down", new Testing_I_down());
//    	SmartDashboard.putData("D Up", new Testing_D_up());
//    	SmartDashboard.putData("D down", new Testing_D_down());
//    	SmartDashboard.putData("Stop shooter", new Testing_Stop_Flywheel());
//    	SmartDashboard.putData("Speed Up", new Testing_Speed_up());
//    	SmartDashboard.putData("Speed Down", new Testing_Speed_down());
    }
    
    public void log(){
    
    }
    
    public Joystick getXbox1() {
    	return xbox1;
    }
    public Joystick getBox() {
    	return customBox;
    }
}