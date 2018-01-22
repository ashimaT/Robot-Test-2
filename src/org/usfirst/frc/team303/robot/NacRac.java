package org.usfirst.frc.team303.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NacRac {
	
	public WPI_TalonSRX nacRac = new WPI_TalonSRX(RobotMap.NACRAC_ID);
	
	public NacRac() {
		nacRac.setInverted(RobotMap.NACRAC_INV);
	}
	
	public void power(double power) {
		System.out.println("Power" + get());
		SmartDashboard.putNumber("NacRac Power", get());
		nacRac.set(power);
	}
	public double get()
	{
		return nacRac.get();
	}
}
