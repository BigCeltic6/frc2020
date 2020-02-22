// 
// Decompiled by Procyon v0.5.36
// 

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDrive extends CommandBase
{
    public static float POWER_PERCENT;
    
    public ArcadeDrive(Drivetrain m_drivetrain) {
        this.requires((Subsystem)Robot.drivetrain);
   }
    
    public ArcadeDrive() {
	}

	//public ArcadeDrive(Drivetrain m_drivetrain) {
	private void requires(Subsystem drivetrain) {
    }


	//private void requires(Subsystem drivetrain) {
   //}

    public void initialize() {
    }
    
    public void execute() {
        final double pedal = Robot.m_robotcontainer.getDriveLeftY();
        final double steer = -Robot.m_robotcontainer.getDriveRightX();
        final boolean pedalPush = Robot.m_robotcontainer.getDriveLeftAnalogButton();
        final boolean steerPush = Robot.m_robotcontainer.getDriveRightAnalogButton();
        if (pedalPush) {
            ArcadeDrive.POWER_PERCENT = 1.0f;
        } else if (steerPush) {
            ArcadeDrive.POWER_PERCENT = 0.7f;
        }
        Robot.drivetrain.runLeftDrive(-ArcadeDrive.POWER_PERCENT * (pedal + steer));
        Robot.drivetrain.runRightDrive(ArcadeDrive.POWER_PERCENT * (-pedal + steer));
    }
    
    public boolean isFinished() {
        return false;
    }
    
    protected void end() {
    }
    
    protected void interrupted() {
    }
    
    static {
        ArcadeDrive.POWER_PERCENT = 0.7f;
    }
}
