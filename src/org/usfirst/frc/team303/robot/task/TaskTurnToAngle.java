package org.usfirst.frc.team303.robot.task;

import org.usfirst.frc.team303.robot.Drivebase;
import org.usfirst.frc.team303.robot.MotorDirection;
import org.usfirst.frc.team303.robot.NavX;
import org.usfirst.frc.team303.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TaskTurnToAngle implements Task {
	Drivebase drivebase = new Drivebase();
	double angle;
	double power;
	boolean started = false;
	MotorDirection direction = null;
	
	
	public TaskTurnToAngle(double angle, double power, MotorDirection direction) {     
		if(direction == MotorDirection.Left){
			angle = -angle;
		}
		this.angle = angle;
		this.power = power;
		this.direction = direction;
	}
	public void run()
	{
		if (!started) {
			drivebase.zeroEncoder();
			Robot.navX.navXObj.zeroYaw();
			started = true;
		}
		if (direction == MotorDirection.Left)
		{
			drivebase.drive(power, -power);
			SmartDashboard.putNumber("Left Speed", power);
			SmartDashboard.putNumber("Right Speed", -power);
			System.out.println("Left" + drivebase.getSpeed(MotorDirection.Left));
			SmartDashboard.putNumber("Left" , drivebase.getSpeed(MotorDirection.Left));
		}
		else if (direction == MotorDirection.Right)
		{
			drivebase.drive(-power, power);
			SmartDashboard.putNumber("Left Speed", -power);
			SmartDashboard.putNumber("Right Speed", power);

			System.out.println("Left" + drivebase.getSpeed(MotorDirection.Right));
			SmartDashboard.putNumber("Left" , drivebase.getSpeed(MotorDirection.Right));
		}
	}
	public boolean isFinished()
	{
		
		System.out.println("Like,,,, Yaw\t     " + Robot.navX.navXObj.getYaw());
		SmartDashboard.putNumber("Like,,,, Yaw", Robot.navX.getYaw());

			if (direction == MotorDirection.Left){
				return(Robot.navX.navXObj.getYaw() <= angle);
			} else if (direction == MotorDirection.Right) {
				return (Robot.navX.navXObj.getYaw() >= angle);
			} else {
				return true;
			}
	}
	
}
