package org.usfirst.frc.team967.robot;

import org.usfirst.frc.team967.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class OI {
    private Joystick xbox1 = new Joystick(1);
    private Joystick xbox2 = new Joystick(2);
    
    double leftTrigger;
    double rightTrigger;			
    
    public AxisButton xbox1_lT = new AxisButton(xbox1, 2, .75, 1);
    public AxisButton xbox1_RT = new AxisButton(xbox1, 3, .75, 1);
    public AxisButton xbox2_y1 = new AxisButton(xbox2, 1, .2, 2);
    public AxisButton xbox2_R1 = new AxisButton(xbox2, 1, .2, 2);
    
    public POVButton xbox1povN = new POVButton(xbox1, 0, 0);
    public POVButton xbox1povNE = new POVButton(xbox1, 0, 45);
    public POVButton xbox1povE = new POVButton(xbox1, 0, 90);
    public POVButton xbox1povSE = new POVButton(xbox1, 0, 135);
    public POVButton xbox1povS = new POVButton(xbox1, 0, 180);
    public POVButton xbox1povSW = new POVButton(xbox1, 0, 225);
    public POVButton xbox1povW = new POVButton(xbox1, 0, 270);
    public POVButton xbox1povNW = new POVButton(xbox1, 0, 315);
    
    public POVButton xbox2povN = new POVButton(xbox2, 0, 0);
    public POVButton xbox2povNE = new POVButton(xbox2, 0, 45);
    public POVButton xbox2povE = new POVButton(xbox2, 0, 90);
    public POVButton xbox2povSE = new POVButton(xbox2, 0, 135);
    public POVButton xbox2povS = new POVButton(xbox2, 0, 180);
    public POVButton xbox2povSW = new POVButton(xbox2, 0, 225);
    public POVButton xbox2povW = new POVButton(xbox2, 0, 270);
    public POVButton xbox2povNW = new POVButton(xbox2, 0, 315);

    public OI() {
    	
    	//*******************************************************************
    	// Setting up the variables to the buttons on controller 1
//    	JoystickButton xbox1_a = new JoystickButton(xbox1, 1);
    	JoystickButton xbox1_b = new JoystickButton(xbox1, 2);
    	JoystickButton xbox1_x = new JoystickButton(xbox1, 3);
    	JoystickButton xbox1_y = new JoystickButton(xbox1, 4);
//    	JoystickButton xbox1_lb = new JoystickButton(xbox1, 5);
//    	JoystickButton xbox1_rb = new JoystickButton(xbox1, 6);
//    	JoystickButton xbox1_back = new JoystickButton(xbox1, 7);
//    	JoystickButton xbox1_start = new JoystickButton(xbox1, 8);
//    	JoystickButton xbox1_leftStickButton = new JoystickButton(xbox1, 9);
//    	JoystickButton xbox1_rightStickButton = new JoystickButton(xbox1, 10);
    	
    	//xbox1.getPOV();//0=north, 90=east, 180=south, 45=NE, ect.
    	
    	//**********************************************************************
    	//Setting up the variables to the buttons on controller 2
//    	JoystickButton xbox2_a = new JoystickButton(xbox2, 1);
//    	JoystickButton xbox2_b = new JoystickButton(xbox2, 2);
//    	JoystickButton xbox2_x = new JoystickButton(xbox2, 3);
//    	JoystickButton xbox2_y = new JoystickButton(xbox2, 4);
//    	JoystickButton xbox2_lb = new JoystickButton(xbox2, 5);
//    	JoystickButton xbox2_rb = new JoystickButton(xbox2, 6);
//    	JoystickButton xbox2_back = new JoystickButton(xbox2, 7);
//    	JoystickButton xbox2_start = new JoystickButton(xbox2, 8);
//    	JoystickButton xbox2_leftStickButton = new JoystickButton(xbox2, 9);
//    	JoystickButton xbox2_rightStickButton = new JoystickButton(xbox2, 10);
    	//JoystickButton xbox2_lt = new JoystickButton(xbox2, 11);   	
    	
    	
    	//*********************************************************************
    	//Setting the button variables to the commands for controller number 1
   
    	//xbox1_start.whenPressed(new PTOShiftOn());
    	
//    	xbox1_a.whenPressed(new TeleOp_ShiftDriveLow());
//    	xbox1_a.whenReleased(new TeleOp_ShiftDriveHigh());
    	xbox1_b.whenPressed(new TeleOp_Shoot());
    	xbox1_b.whenReleased(new TeleOp_StopShooting());
    	xbox1_x.whenPressed(new TeleOp_shootSpeedUp());
    	xbox1_y.whenPressed(new TeleOp_ShootSpeedDown());
//    	xbox1_lb.whenPressed(command);
//    	xbox1_rb.whenPressed(command);
//    	xbox1_back.whenPressed(command);
//    	xbox1_start.whenPressed(command);
//    	xbox1_leftStickButton.whenPressed(command);
//    	xbox1_rightStickButton.whenPressed(command);

    	
    	//**********************************************************************
    	//Setting the button variables to the commands for controller number 2
    	
    	//xbox2_start.whenPressed(new ClimberToExtended());
    	
//    	xbox2_a.whenPressed(new TeleOp_Climb());
//    	xbox2_b.whenPressed(new TeleOp_Shoot());
//    	xbox2_x.whenPressed(command);
//    	xbox2_y.whenPressed(command);
//    	xbox2_lb.whenPressed(command);
//    	xbox2_rb.whenPressed(command);
//    	xbox2_back.whenPressed(command);
//    	xbox2_start.whenPressed(command);
//    	xbox2_leftStickButton.whenPressed(command);
//    	xbox2_rightStickButton.whenPressed(command);
    	
    }
    
    public void log(){

    }
    
    public Joystick getXbox1() {
    	return xbox1;
    }
    public Joystick getXbox2() {
    	return xbox2;
    }
}