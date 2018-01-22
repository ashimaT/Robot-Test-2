package org.usfirst.frc.team303.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavX implements PIDOutput { //this class controls the PID for the navX as well as the AHRS class itself
	public AHRS navXObj;
	PIDController turnController;
	double rate; //this is the output
	double setPoint = 0;
	//static double kDefaultCollisionThreshold_DeltaG = 0.73f;  

	public NavX() {
		navXObj = new AHRS(SPI.Port.kMXP);
		navXObj.setPIDSourceType(PIDSourceType.kDisplacement);
		
		turnController = new PIDController(0.06, 0.008, 0.11, navXObj, this); 
		turnController.setInputRange(-180.0f, 180.0f);
		turnController.setOutputRange(-1, 1);
		turnController.setAbsoluteTolerance(4.0f);
		turnController.setContinuous(true);
	}
		
	public void setSetpoint(double setpoint) {
		setPoint = setpoint;
		turnController.setSetpoint(setpoint);
	}
	
	public double getYaw() {
		return navXObj.getYaw();
	}

	@Override
	public void pidWrite(double output) {		
			rate = output;
	}
	
	public double getPidOutput() {
		return rate; 
	}
	
	
	
	
}
