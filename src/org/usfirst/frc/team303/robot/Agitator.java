package org.usfirst.frc.team303.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Agitator {
	
	public WPI_TalonSRX agitatorMotor = new WPI_TalonSRX(RobotMap.AGITATOR_ID);

	public Agitator() {
		agitatorMotor.setInverted(RobotMap.AGITATOR_INV);
	}
	
	public void set(double power) {
		agitatorMotor.set(power);
	}
	
	public double getSpeed() {
		return agitatorMotor.get();
	}
}
