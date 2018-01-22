package org.usfirst.frc.team303.robot.task;

import org.usfirst.frc.team303.robot.Drivebase;
import org.usfirst.frc.team303.robot.MotorDirection;
import org.usfirst.frc.team303.robot.NavX;
import org.usfirst.frc.team303.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TaskTurn180 implements Task {
	Drivebase drivebase = new Drivebase();
	double power;
	boolean started = false;
	MotorDirection direction = null;
	
	public TaskTurn180() {
		this(0.4);
	}
	
	public TaskTurn180(double power) {     		
		this.power = power;
	}
	
	
	public void run() {
		if (!started) {
			drivebase.zeroEncoder();
			Robot.navX.navXObj.zeroYaw();
			started = true;
		}
		
		drivebase.drive(power, -power);
	}
	public boolean isFinished() {
		//return(Robot.navX.navX.getYaw() >= 170);	
		return true;
	}
	
}
