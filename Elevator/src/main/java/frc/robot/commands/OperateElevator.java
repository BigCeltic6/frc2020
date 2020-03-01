// 
// Decompiled by Procyon v0.5.36
// 

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
//import frc.robot.subsystems.Drivetrain;

public class OperateElevator extends Command
{
    public static float POWER_PERCENT;
    
    public OperateElevator() {
        this.requires((Subsystem)Robot.m_elevator);
    }
    
    protected void initialize() {
    }
    
   protected void execute() {
    int direction = Robot.m_oi.printDpad();

    if (direction == 0) { // DPAD UP button is pressed
        OperateElevator.POWER_PERCENT = 1.0f;
    } else if (direction == 180) { // DPAD DOWN button is pressed
        OperateElevator.POWER_PERCENT = -1.0f;
    }
    else {
        OperateElevator.POWER_PERCENT = 0.0f;
    }

    Robot.m_elevator.runElevatorPower(OperateElevator.POWER_PERCENT);
    // You can interpret the D-pad as an axis as well:
   // double dpadYAxisValue = Math.cos(Math.toRadians(direction));
    //double dpadXAxisValue = Math.sin(Math.toRadians(direction));
    
    //someMotorController.set(dpadYAxisValue);
 
    }
    
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    }
    
    protected void interrupted() {
    }
    
    static {
        OperateElevator.POWER_PERCENT = 1.0f;
    }
}
