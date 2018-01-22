package org.usfirst.frc.team303.robot.task;

public interface Task {
	
	public void run();
	public boolean isFinished();
	
	public static double[] driveStraightAngle(double powSetpoint, double angleDifference, double tuningConstant) {                                                                                                                      //memes
		return new double[] {(powSetpoint + (angleDifference*tuningConstant)), (powSetpoint - (angleDifference*tuningConstant))};
	}
	

}
