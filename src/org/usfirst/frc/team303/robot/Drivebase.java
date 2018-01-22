package org.usfirst.frc.team303.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivebase {
	
	private WPI_TalonSRX rightBack = new WPI_TalonSRX(RobotMap.BACK_RIGHT);
	private WPI_TalonSRX rightFront = new WPI_TalonSRX(RobotMap.FRONT_RIGHT);
	private WPI_TalonSRX leftFront = new WPI_TalonSRX(RobotMap.FRONT_LEFT);
	private WPI_TalonSRX leftBack = new WPI_TalonSRX(RobotMap.BACK_LEFT);
	
	SpeedControllerGroup rightMotors = new SpeedControllerGroup(rightBack, rightFront);
	SpeedControllerGroup leftMotors = new SpeedControllerGroup(leftBack, leftFront);
	DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);


	public Drivebase() {
		rightBack.setInverted(RobotMap.REAR_RIGHT_INV);
		leftBack.setInverted(RobotMap.REAR_LEFT_INV);
		rightFront.setInverted(RobotMap.FRONT_RIGHT_INV);
		leftFront.setInverted(RobotMap.FRONT_LEFT_INV);
		rightBack.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		leftFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
	}
	
	public void drive(double left, double right) {
		drive.tankDrive(left, right);
	}
	
	public double getSpeed(MotorDirection direction){
		if (direction == MotorDirection.Left){
			return leftFront.get();
		}
		else if (direction == MotorDirection.Right){
			return rightBack.get();
		}
		return 0.0;

	}
	public void zeroEncoder() {
		System.out.println("Encoders: Zeroed");
		leftFront.setSelectedSensorPosition(0, 0, 0);
		rightBack.setSelectedSensorPosition(0, 0, 0);

	}
	
	public int getLeftEncoder() {
		SmartDashboard.putNumber("Left Power", leftFront.getMotorOutputVoltage());
		return leftFront.getSelectedSensorPosition(0);
	}
	
	public int getRightEncoder() {
		SmartDashboard.putNumber("Right Power", rightFront.getMotorOutputVoltage());
		return rightBack.getSelectedSensorPosition(0);
	}

	
	public boolean driveBothEnc(int distance) {
		
		System.out.println("LEFT ENCODER: " + getLeftEncoder());
		if (getLeftEncoder() < distance) {
			System.out.println("FALSE");
			drive(0.55, 0.55);
			return false;
		} else {
			drive(0,0);
			System.out.println("TRUE");
			zeroEncoder();
			return true;
		}
	}
	
	public boolean driveLeftEnc(int distance) {
		System.out.println(getLeftEncoder());
		if (getLeftEncoder() < distance) {
			drive(0, 0.55);
			System.out.println("TURNING LEFT = TRUE");
			return false;
		} else {
			drive(0,0);
			zeroEncoder();
			System.out.println("TURNING LEFT = FALSE");
			return true;
		}
	}
	
	public boolean driveRightEnc(int distance) {
		System.out.println(getLeftEncoder());
		if (getRightEncoder() < distance) {
			drive(0, 0.4);
			return false;
		} else {
			drive(0,0);
			return true;
		}
	}
	
	
	
	/*
	public void setEncoder(double distance){
		leftFront.set(ControlMode.Position , distance);
		SmartDashboard.putNumber("encoder position", rightBack.getSelectedSensorPosition(0));
	}*/
	
	public void stopMotors() {
		drive(0,0);
	}
}
