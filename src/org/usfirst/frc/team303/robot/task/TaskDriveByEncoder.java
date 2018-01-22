package org.usfirst.frc.team303.robot.task;

import org.usfirst.frc.team303.robot.Drivebase;
import org.usfirst.frc.team303.robot.MotorDirection;
import org.usfirst.frc.team303.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TaskDriveByEncoder implements Task {
	
	public boolean started = false;
	public Drivebase drivebase;
	public int distance = 0;
	public double power = 0;
	public MotorDirection direction = MotorDirection.Both;
	/*public static final int BOTH = 0;
	public static final int RIGHT = 1;
	public static final int LEFT = 2;
	*/
	public TaskDriveByEncoder(int distance) {
		this(distance, 0.5);
	}
	
	public TaskDriveByEncoder(int distance, double power) {
		this(distance, power, MotorDirection.Both);
	}
	
	public TaskDriveByEncoder(int distance, double power, MotorDirection direction) {
		this.distance = distance;
		this.power = power;
		this.direction = direction;
		drivebase = Robot.drivebase;
	}
	
	public void run() {
		System.out.println("running encoders");
		if (!started) {
			drivebase.zeroEncoder();
			Robot.navX.navXObj.zeroYaw();
			started = true;
		}
		switch(direction)
		{
		case Both:
			SmartDashboard.putString("Direction", "Both");
			double[] pow = Task.driveStraightAngle(power, Robot.navX.getYaw(), 0.01);
			drivebase.drive(-pow[0], -pow[1]);
			
			System.out.println("POWER Right: " + -pow[0] +"\n POWER Left"+ -pow[1]);
			SmartDashboard.putNumber("RPOWER", -pow[0]);
			SmartDashboard.putNumber("LPOWER", -pow[1]);
			break;
			
		case Right:
		
			SmartDashboard.putString("Direction", "Right");
			drivebase.drive(0,- power);
			System.out.println("POWER: " + power);
			
			break;
		
		case Left:
			SmartDashboard.putString("Direction", "Left");
			drivebase.drive(-power, 0);
			System.out.println("POWER: " + power);
			break;
		
			default:
				System.out.println("If you are reading this youre too late");
		}
		/*
		if (direction == BOTH) {
			SmartDashboard.putString("Direction", "Both");
			drivebase.drive(power, power);
			System.out.println("POWER: " + power);
		} else if (direction == RIGHT) {
			SmartDashboard.putString("Direction", "Right");
			drivebase.drive(0, power);
			System.out.println("POWER: " + power);
		} else if (direction == LEFT) {
			SmartDashboard.putString("Direction", "Left");
			drivebase.drive(power, 0);
			System.out.println("POWER: " + power);
		}*/
				
	}
	
	public boolean isFinished() {	
		System.out.println("DISTANCE: " + distance + "LEFT ENCODER:" + drivebase.getLeftEncoder());
		if (direction == MotorDirection.Both || direction == MotorDirection.Left) { 
			System.out.println(distance >= drivebase.getLeftEncoder());
			return (distance <= -drivebase.getLeftEncoder());
		} else if (direction == MotorDirection.Right) {
			return (distance <= -drivebase.getRightEncoder());
		} else {
			return false;
		}		
	}
}
