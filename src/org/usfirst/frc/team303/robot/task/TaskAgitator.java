package org.usfirst.frc.team303.robot.task;

import org.usfirst.frc.team303.robot.Agitator;
import org.usfirst.frc.team303.robot.Drivebase;
import org.usfirst.frc.team303.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

public class TaskAgitator implements Task {
	Timer timer = new Timer();
	public double time = 0;
	public double power = 0;
	public boolean started = false;
	private Agitator agitator = Robot.agitator;
	
	public TaskAgitator() {
		this(0.5);
	}
	
	public TaskAgitator(double power) {
		this(power, 1.0);
	}
	
	public TaskAgitator(double power, double time) {
		this.power = power;
		this.time = time;
	}
	
	public void run() {
		if (!started) {
			timer.reset();
			timer.start();
			started = true;
		}
		
		agitator.set(power);
	}
	
	public boolean isFinished() {
		if (timer.get() >= time) {
			agitator.set(0);
			return true;
		} else {
			return false;
		}
	}
	
	
}
