package org.usfirst.frc.team303.robot;

import java.util.*;
import org.usfirst.frc.team303.robot.task.Task;
import org.usfirst.frc.team303.robot.task.TaskTurnToAngle;
import org.usfirst.frc.team303.robot.task.TaskWait;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous {
	
	private ArrayList<Task> taskList = new ArrayList<>();
	private int count = 0;
	Drivebase drivebase = Robot.drivebase;
	public void add(Task task) {
		taskList.add(task);
	}
	
	public void runAuto() {
		System.out.println("STARTED" + taskList.size() +"Count" + count);
		SmartDashboard.putNumber("ArrSize", taskList.size());
		SmartDashboard.putNumber("Count", count);

		
		
		if (taskList.size() > count) {
			System.out.println("Task Started" + count);
			Task currentTask = taskList.get(count);
			currentTask.run();
			if (currentTask.isFinished()) {
				count++;
				drivebase.zeroEncoder();
				System.out.println("FINISHED");
			}
		}
	}
	
	public void clearArr() {
		taskList.clear();
	}
	
	public void turn180() {
		add(new TaskTurnToAngle(90,0.7,MotorDirection.Right));
		add(new TaskWait(0.5));
		add(new TaskTurnToAngle(90,0.7,MotorDirection.Right));	
	}
	
}

