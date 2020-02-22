 
//Decompiled by Procyon v0.5.36
 

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.ArcadeDrive;

public class Drivetrain extends SubsystemBase
{
    VictorSP Motor /*Indication*/;
    PWMVictorSPX FRONT_LEFT_DRIVE_MOTOR;
    PWMVictorSPX BACK_LEFT_DRIVE_MOTOR;
    PWMVictorSPX FRONT_RIGHT_DRIVE_MOTOR;
    PWMVictorSPX BACK_RIGHT_DRIVE_MOTOR;

    SpeedControllerGroup rightDrive;
    SpeedControllerGroup leftDrive;
    
   // Secondary Victor Motor Indication;   
    public Drivetrain() {
        this.FRONT_LEFT_DRIVE_MOTOR = new PWMVictorSPX(0);
        this.BACK_LEFT_DRIVE_MOTOR = new PWMVictorSPX(1);
        this.FRONT_RIGHT_DRIVE_MOTOR = new PWMVictorSPX(2);
        this.BACK_RIGHT_DRIVE_MOTOR = new PWMVictorSPX(3);
    }
    
    public void runRightDrive(final double output) {
        this.FRONT_RIGHT_DRIVE_MOTOR.set(-output);
        this.BACK_RIGHT_DRIVE_MOTOR.set(-output);
    }
    
    public void runLeftDrive(final double output) {
        this.FRONT_LEFT_DRIVE_MOTOR.set(output);
        this.BACK_LEFT_DRIVE_MOTOR.set(output);
    }
    
    public void initDefaultCommand() {
        this.setDefaultCommand((Command)new ArcadeDrive());
    }
}