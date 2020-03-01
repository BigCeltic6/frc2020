// 
// Decompiled by Procyon v0.5.36
// 

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.AutonomousCommand;

public class PIDDrivetrain extends Subsystem
{
    TalonFX FRONT_LEFT_DRIVE_FALCON;
    TalonFX BACK_LEFT_DRIVE_FALCON;
    TalonFX FRONT_RIGHT_DRIVE_FALCON;
    TalonFX BACK_RIGHT_DRIVE_FALCON;
    SpeedControllerGroup rightDrive;
    SpeedControllerGroup leftDrive;
  
    TalonFX piddrivetrain;
    TalonFXControlMode controlMode = TalonFXControlMode.PercentOutput;
    
    public PIDDrivetrain() {
        this.FRONT_LEFT_DRIVE_FALCON = new TalonFX(0);
        this.BACK_LEFT_DRIVE_FALCON = new TalonFX(1);
        this.FRONT_RIGHT_DRIVE_FALCON = new TalonFX(2);
        this.BACK_RIGHT_DRIVE_FALCON = new TalonFX(3);

    }
    
    public void runRightDrive(final double output) {
        this.FRONT_RIGHT_DRIVE_FALCON.set(controlMode, -output);
        this.BACK_RIGHT_DRIVE_FALCON.set(controlMode,-output);
    }
    
    public void runLeftDrive(final double output) {
        this.FRONT_LEFT_DRIVE_FALCON.set(controlMode, output);
        this.BACK_LEFT_DRIVE_FALCON.set(controlMode, output);
    }
    

    public void initDefaultCommand() {
        this.setDefaultCommand((Command)new AutonomousCommand());
    }
}
