// 
// Decompiled by Procyon v0.5.36
// 

package frc.robot.commands;

//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class OperatePneumatics extends Command
{
    public OperatePneumatics() {
        this.requires((Subsystem)Robot.m_pneumatics);
    }
    
    protected void initialize() {
    }
    
    protected void execute() {
        final boolean button_a = Robot.m_oi.getDriveAButton();
        final boolean button_b = Robot.m_oi.getDriveBButton();
        //final boolean button_x = Robot.m_oi.getDriveXButton();
        //final boolean button_y = Robot.m_oi.getDriveYButton();
        //final boolean tStartButton = Robot.m_oi.getToolStartButton();
        //final int dpadAngle = Robot.m_oi.printDpad();
        if (button_a) {
            Robot.m_pneumatics.firstForward();
        }
        else if (button_b) {
            Robot.m_pneumatics.firstReverse();
        }
        /*if (button_x) {
            Robot.pneumatic.secondForward();
        }
        else if (button_y) {
            Robot.pneumatic.secondReverse();
        }
        if (dpadAngle == 180) {
            Robot.pneumatic.thirdForward();
        }
        */
    }
    
    //public static void pause() {
   //     Timer.delay(1.0);
    //}
    
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    }
    
    protected void interrupted() {
    }
}
