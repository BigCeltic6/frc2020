package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class OperateIntake extends Command
{
    //private static final float INTAKE_POWER_PERCENT = 1.0f;
    
    public OperateIntake() {
        this.requires((Subsystem)Robot.m_intake);
    }
    
    protected void initialize() {
    }
    
    protected void execute() {
        final boolean leftTrigger = Robot.m_oi.getDriveLeftTrigger();
        final boolean rightTrigger = Robot.m_oi.getDriveRightTrigger();
        //final boolean backButton = Robot.m_oi.getDriveBackButton();
        double engage;
        if (leftTrigger) {
            engage = 0.8;
        }
        else if (rightTrigger) {
            engage = -1.0;
        }
       // else if (backButton) {
        //    engage = 1.142857;
       // }
        else {
            engage = 0.0;
        }
        Robot.m_intake.runintake(0.699999988079071 * engage);
    }
    
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    }
    
    protected void interrupted() {
    }
}
