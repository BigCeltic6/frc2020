package frc.robot.commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
//import frc.robot.subsystems.Drivetrain;

public class AutonomousTurnCommand extends Command
{
    public static float POWER_PERCENT;
    
    public AutonomousTurnCommand() {
        this.requires((Subsystem)Robot.m_drivetrain);
        this.requires((Subsystem)Robot.m_intake);
    
    }
    
    protected void initialize() {
        
    }
    

   protected void execute() {
        //double startTime = Robot.startTime;
        //double time = Timer.getFPGATimestamp();

       double gyro = Robot.m_drivetrain.measure;
       double startTime = Robot.m_startTime;
       double elapsedTime= Timer.getFPGATimestamp() - startTime;
       double error = 90 - gyro;
       double back = 90 + gyro;
       


        if (elapsedTime < 1) {
        Robot.m_drivetrain.runLeftDrive(-AutonomousCommand.POWER_PERCENT);
        Robot.m_drivetrain.runRightDrive(AutonomousCommand.POWER_PERCENT);
        Robot.m_intake.runintake(0);

       } 
       else if (elapsedTime < 2) {
        Robot.m_intake.runintake(0);
        Robot.m_drivetrain.runLeftDrive(-AutonomousCommand.POWER_PERCENT* error);
        Robot.m_drivetrain.runRightDrive(AutonomousCommand.POWER_PERCENT * error);

       }
       else if (elapsedTime  < 3) {
        Robot.m_drivetrain.runLeftDrive(-AutonomousCommand.POWER_PERCENT);
        Robot.m_drivetrain.runRightDrive(AutonomousCommand.POWER_PERCENT);
        Robot.m_intake.runintake(0);

       }

        else if (elapsedTime < 4) {
            Robot.m_intake.runintake(0);
            Robot.m_drivetrain.runLeftDrive(-AutonomousCommand.POWER_PERCENT* back);
            Robot.m_drivetrain.runRightDrive(AutonomousCommand.POWER_PERCENT * back);
    
           }

       else if (elapsedTime < 5) {
        Robot.m_drivetrain.runLeftDrive(0);
        Robot.m_drivetrain.runRightDrive(0);
        Robot.m_intake.runintake(0.699999988079071);

       }
       
       else{   
        Robot.m_drivetrain.runLeftDrive(0);
        Robot.m_drivetrain.runRightDrive(0);
        Robot.m_intake.runintake(0);
       }

    }
    
    protected boolean isFinished() { 
        return false;
    }
    
    protected void end() {
    }
    
    protected void interrupted() {
    }
    
    static {
        AutonomousCommand.POWER_PERCENT = 0.5f;
    }
}
