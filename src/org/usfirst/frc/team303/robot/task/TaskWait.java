package org.usfirst.frc.team303.robot.task;

import edu.wpi.first.wpilibj.Timer;

public class TaskWait implements Task {
	
	boolean firstRun;
	Timer t;
	double timeThreshold; //in seconds
	
	public TaskWait(double time) {
		timeThreshold = time;
		firstRun = true;
		t = new Timer();
		t.stop();
	}
	
	@Override
	public void run() {
		if(firstRun) {
			firstRun = false;
			t.start();
		}
	}

	@Override
	public boolean isFinished() {
		boolean end = t.get()>=timeThreshold;
		
		if(end) {
			firstRun = true;
			t.stop();
			t.reset();
		}
		
		return end;
	}

}
