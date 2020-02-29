// 
// Decompiled by Procyon v0.5.36
// 

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.ArcadeDrive;
import edu.wpi.first.wpilibj.PWMVictorSPX;

public class Drivetrain extends SubsystemBase
{
    //Victor Motor Indication
    PWMVictorSPX FRONT_LEFT_DRIVE_MOTOR;
    PWMVictorSPX BACK_LEFT_DRIVE_MOTOR;
    PWMVictorSPX FRONT_RIGHT_DRIVE_MOTOR;
    PWMVictorSPX BACK_RIGHT_DRIVE_MOTOR;

    SpeedControllerGroup rightDrive;
    SpeedControllerGroup leftDrive;
    
    //Secondary Victor Motor Indication
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
    public class talonDrive extends SubsystemBase
    {
        //Talon Indication
        TalonFX FRONT_RIGHT_DRIVE_MOTOR;
        TalonFX FRONT_LEFT_DRIVE_MOTOR;
        TalonFX BACK_RIGHT_DRIVE_MOTOR;
        TalonFX BACK_LEFT_DRIVE_MOTOR;

        SpeedControllerGroup rightDrive;
        SpeedControllerGroup leftDrive;

        TalonFX drivetrain;
        TalonFXControlMode controlMode = TalonFXControlMode.PercentOutput;

            //Talon Motor Controller Indication

        public talonDrive() {
            this.BACK_LEFT_DRIVE_MOTOR = new TalonFX(1);
            this.BACK_RIGHT_DRIVE_MOTOR = new TalonFX(3);
            this.FRONT_LEFT_DRIVE_MOTOR = new TalonFX(0);
            this.FRONT_RIGHT_DRIVE_MOTOR = new TalonFX(2);

            //FRONT_RIGHT_DRIVE_MOTOR.getSelectedSensorPosition();
        }
        
        public void runRightDrive (final double output){
            this.BACK_RIGHT_DRIVE_MOTOR.set(controlMode, -output);
            this.FRONT_RIGHT_DRIVE_MOTOR.set(controlMode, -output);
        }
        public void runLeftDrive (final double output){
            this.BACK_LEFT_DRIVE_MOTOR.set(controlMode, output);
            this.FRONT_LEFT_DRIVE_MOTOR.set(controlMode, output);
        }
        public void tempDrive(final double leftOutput, final double rightOutput) 
        {
            runLeftDrive(leftOutput);
            runRightDrive(rightOutput);

        }

        public void initDefaultCommand() {
            this.setDefaultCommand((Command)new ArcadeDrive());
        }

        //Sensor Detection

        public int getFrontRightDriveMotor(){
            return(this.FRONT_RIGHT_DRIVE_MOTOR.getSelectedSensorPosition());
        }
        public int getFrontLeftDriveMotor(){
            return(this.FRONT_LEFT_DRIVE_MOTOR.getSelectedSensorPosition());
        }
        public int getBackRightDriveMotor(){
            return(this.BACK_RIGHT_DRIVE_MOTOR.getSelectedSensorPosition());
        }
        public int getBackLeftDriveMotor(){
            return(this.BACK_LEFT_DRIVE_MOTOR.getSelectedSensorPosition());
        }
    }
}
