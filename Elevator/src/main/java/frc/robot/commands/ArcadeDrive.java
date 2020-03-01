// 
// Decompiled by Procyon v0.5.36
// 

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
//import frc.robot.subsystems.Drivetrain;

public class ArcadeDrive extends Command
{
    public static float POWER_PERCENT;
    
    public ArcadeDrive() {
        this.requires((Subsystem)Robot.m_drivetrain);
    }
    
    protected void initialize() {
    }
    

   protected void execute() {
       final double pedal = Robot.m_oi.getDriveLeftY();
       final double steer = -Robot.m_oi.getDriveRightX();
       final boolean pedalPush = Robot.m_oi.getDriveLeftAnalogButton();
       final boolean steerPush = Robot.m_oi.getDriveRightAnalogButton();
        if (pedalPush) {
            ArcadeDrive.POWER_PERCENT = 1.0f;
        }
        else if (steerPush) {
            ArcadeDrive.POWER_PERCENT = 0.7f;
        }
        Robot.m_drivetrain.runLeftDrive(-ArcadeDrive.POWER_PERCENT * (pedal + steer));
        Robot.m_drivetrain.runRightDrive(ArcadeDrive.POWER_PERCENT * (-pedal + steer));
    }
    
    protected boolean isFinished() { 
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
