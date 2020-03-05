package frc.robot.commands;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
//import frc.robot.subsystems.Drivetrain;


 

public class AutonomousCommand extends Command
{
    public static float POWER_PERCENT;
    private final double kDriveTick2Feet = 1.0 / 4069 * 6 * Math.PI / 12;
    private final double kDriveTick2Deg = 360.0 / 512 * 26 / 42 * 18 / 60 * 18 / 84;

    private Encoder encoder = new Encoder(0, 1, false, EncodingType.k4X);

    private double setpoint = 1;
    private double lastTimestamp = 0;
    private double errorSum = 0;
    private double lastError = 0;    

    private double iLimit = 1;
    //THESE NEED TO BE TUNED TO MATCH OUR ROBOT
    private double kP = 0.1;
    private double kI = 0.1;
    private double kD = 0.1;
    
    private boolean posRight = false, posCentre = false, posLeft = false;
    private boolean stepInAuto = false;
    private double elapsedTime, startTime, reverseDrive = 1;
    

   
    


    public AutonomousCommand() {
        this.requires((Subsystem)Robot.m_drivetrain);
        this.requires((Subsystem)Robot.m_intake);
    
    }
    
    protected void initialize() {
        
    }
    

   protected void execute() {            
        

       startTime = Robot.m_startTime;
       elapsedTime = Timer.getFPGATimestamp() - startTime;

    if(elapsedTime <= 5) { 

       if(posRight == true) 
       {    
            //HAVE IT TURN TO THE LEFT AND GO FORWARD A BIT
            //45° left and drive 5.87610681568 feet and then turn back 45°

            //SET SETPOINT TO DISTANCE WANTED TO GO (in feet)
            setpoint = 6;

            // get sensor position
            double sensorPosition = encoder.get() * kDriveTick2Feet;

            // calculations
            double error = setpoint - sensorPosition;
            double dt = Timer.getFPGATimestamp() - lastTimestamp;

            if (Math.abs(error) < iLimit) 
            {
            errorSum += error * dt;
            }

            double errorRate = (error - lastError) / dt;

            double outputSpeed = kP * error + kI * errorSum + kD * errorRate;

            // output to motors
            //IF REVERSE DROVE IS = -1 THEN THE MOTORS WILL DRIVE BACKWARDS 
            Robot.m_drivetrain.runLeftDrive(-outputSpeed * reverseDrive);
            Robot.m_drivetrain.runRightDrive(outputSpeed * reverseDrive);

            // update last- variables
            lastTimestamp = Timer.getFPGATimestamp();
            lastError = error;
            if(error == 1 && reverseDrive == 1 && stepInAuto == true) 
            {
                //Have collection hatch come down at 5 feet and start intake
                

            }
            else
            if(error == 0 && reverseDrive == 1 && stepInAuto == true) 
            {
                //Have collection hatch come down at 5 feet and start intake
                stepInAuto = false;
                //Have it turn back straight
                setpoint = 5;

            }
            else
            if(error == 0 && reverseDrive == 1 && stepInAuto == false) 
            {
                //have intake stop and raise hatch

                //HAVE IT TURN NOW? or Just go reverse on what we did to get there? (FIXME)
                //Currently set to turn then go straight back

                //Turn a little top head back towards the centre of the arena
                setpoint = 15;
                reverseDrive = -1;

            }
       }
       else
       if(posCentre == true) 
       {         
            
            //SET SETPOINT TO DISTANCE WANTED TO GO (in feet)
            setpoint = 10;

                // get sensor position
                double sensorPosition = encoder.get() * kDriveTick2Feet;

                // calculations
                double error = setpoint - sensorPosition;
                double dt = Timer.getFPGATimestamp() - lastTimestamp;

                if (Math.abs(error) < iLimit) 
                {
                errorSum += error * dt;
                }

                double errorRate = (error - lastError) / dt;

                double outputSpeed = kP * error + kI * errorSum + kD * errorRate;

                // output to motors
                //IF REVERSE DROVE IS = -1 THEN THE MOTORS WILL DRIVE BACKWARDS 
                Robot.m_drivetrain.runLeftDrive(-outputSpeed * reverseDrive);
                Robot.m_drivetrain.runRightDrive(outputSpeed * reverseDrive);

                // update last- variables
                lastTimestamp = Timer.getFPGATimestamp();
                lastError = error;
                if(error == 5 && reverseDrive == 1) 
                {
                    //Have collection hatch come down at 5 feet and start intake

                }
                else
                if(error == 0 && reverseDrive == 1) 
                {
                    //have intake stop and raise hatch
                    setpoint = 15;
                    reverseDrive = -1;

                }          
       }
       else
       if(posLeft == true) 
       {

            //HAVE IT TURN TO THE LEFT AND GO FORWARD A BIT
            //45° left and drive 5.87610681568 feet and then turn back 45°

            //SET SETPOINT TO DISTANCE WANTED TO GO (in feet)
            setpoint = 6;

            // get sensor position
            double sensorPosition = encoder.get() * kDriveTick2Feet;

            // calculations
            double error = setpoint - sensorPosition;
            double dt = Timer.getFPGATimestamp() - lastTimestamp;

            if (Math.abs(error) < iLimit) 
            {
            errorSum += error * dt;
            }

            double errorRate = (error - lastError) / dt;

            double outputSpeed = kP * error + kI * errorSum + kD * errorRate;

            // output to motors
            //IF REVERSE DROVE IS = -1 THEN THE MOTORS WILL DRIVE BACKWARDS 
            Robot.m_drivetrain.runLeftDrive(-outputSpeed * reverseDrive);
            Robot.m_drivetrain.runRightDrive(outputSpeed * reverseDrive);

            // update last- variables
            lastTimestamp = Timer.getFPGATimestamp();
            lastError = error;
            if(error == 1 && reverseDrive == 1 && stepInAuto == true) 
            {
                //Have collection hatch come down at 5 feet and start intake
                

            }
            else
            if(error == 0 && reverseDrive == 1 && stepInAuto == true) 
            {
                //Have collection hatch come down at 5 feet and start intake
                stepInAuto = false;
                //Have it turn back straight
                setpoint = 10;

            }
            else
            if(error == 0 && reverseDrive == 1 && stepInAuto == false) 
            {
                //have intake stop and raise hatch

                //HAVE IT TURN NOW? or Just go reverse on what we did to get there? (FIXME)
                //Currently set to turn then go straight back

                //Turn a little to head back towards the centre of the arena
                setpoint = 15;
                reverseDrive = -1;

            }
       }
    }

        
            
              /*  //SET SETPOINT TO DISTANCE WANTED TO GO (in feet)

                // get sensor position
                double sensorPosition = encoder.get() * kDriveTick2Feet;

                // calculations
                double error = setpoint - sensorPosition;
                double dt = Timer.getFPGATimestamp() - lastTimestamp;

                if (Math.abs(error) < iLimit) 
                {
                errorSum += error * dt;
                }

                double errorRate = (error - lastError) / dt;

                double outputSpeed = kP * error + kI * errorSum + kD * errorRate;

                // output to motors
                Robot.m_drivetrain.runLeftDrive(-outputSpeed);
                Robot.m_drivetrain.runRightDrive(outputSpeed);

                // update last- variables
                lastTimestamp = Timer.getFPGATimestamp();
                lastError = error;
            

                //GYro turning to a set degrees
                //Have setpoint set to negatived for left turn and positive for right turn
            
            
                //Make setpoint be negative for an intake and positive for outake

                //Code for the motors to run intake/outtake

                       */         
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
