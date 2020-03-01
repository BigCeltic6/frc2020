// 
// Decompiled by Procyon v0.5.36
// 

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SPI;
//import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ArcadeDrive;

public class Drivetrain extends Subsystem
{
    PWMVictorSPX FRONT_LEFT_DRIVE_MOTOR;
    PWMVictorSPX BACK_LEFT_DRIVE_MOTOR;
    PWMVictorSPX FRONT_RIGHT_DRIVE_MOTOR;
    PWMVictorSPX BACK_RIGHT_DRIVE_MOTOR;
    //SpeedControllerGroup rightDrive;
    //SpeedControllerGroup leftDrive;
    ADXRS450_Gyro gyro;
	public double measure;

    
    public Drivetrain() {
        this.FRONT_LEFT_DRIVE_MOTOR = new PWMVictorSPX(0);
        this.BACK_LEFT_DRIVE_MOTOR = new PWMVictorSPX(1);
        this.FRONT_RIGHT_DRIVE_MOTOR = new PWMVictorSPX(2);
        this.BACK_RIGHT_DRIVE_MOTOR = new PWMVictorSPX(3);
        this.gyro = new ADXRS450_Gyro(SPI.Port.kMXP);
    }
    
    public void runRightDrive(final double output) {
        this.FRONT_RIGHT_DRIVE_MOTOR.set(-output);
        this.BACK_RIGHT_DRIVE_MOTOR.set(-output);
    }
    
    public void runLeftDrive(final double output) {
        this.FRONT_LEFT_DRIVE_MOTOR.set(output);
        this.BACK_LEFT_DRIVE_MOTOR.set(output);
    }

    public double findDriveAngle() {
        final double measure = this.gyro.getAngle();
        
        return measure;
    }
    
    public void initDefaultCommand() {
        this.setDefaultCommand((Command)new ArcadeDrive());
    }


}
