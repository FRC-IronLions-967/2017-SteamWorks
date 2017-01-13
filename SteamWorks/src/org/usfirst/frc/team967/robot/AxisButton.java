package org.usfirst.frc.team967.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class AxisButton extends Button {
	Joystick joy;
	int Axis;
	double SetpointValue;
	double lastValue;
	double currentValue;
	int Feature;
	boolean exitStatus;
	
	
	public AxisButton(Joystick stick, int axis, double setpointvalue, int feature){
		joy = stick;
		Axis = axis;
		SetpointValue = setpointvalue;
		Feature = feature;
		currentValue = joy.getRawAxis(axis);
	}
	
	public boolean get(){
		
		switch (Feature){
			case 1: if(joy.getRawAxis(Axis) > SetpointValue){
						exitStatus = true;
					}	
					else{
						exitStatus = false;
					}
					break;
			case 2: if(lastValue == currentValue){
						exitStatus = false;
					}
					else{
						exitStatus = true;
					}
					break;
		}
		lastValue = currentValue;
		return exitStatus;	
	}
}