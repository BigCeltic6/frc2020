/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//BOTTOM MOTORS AREN"T IN CODE/NOT ATTACHED TO FRONT IN CASE WE WANT TO USE SEPERATLY

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.OperateElevatord;
import frc.robot.commands.OperateIntake;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Drivetrain.talonDrive;
//import frc.robot.subsystems.Intake;
//import frc.robot.subsystems.Drivetrain;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot 
{
  //public static final BallIntake ballIntake;
  //public static final HatchIntake hatchIntake;
  //public static final Pneumatics pneumatic;
  public static Drivetrain drivetrain;
  public static RobotContainer m_robotcontainer;
  public static ArcadeDrive m_ArcadeDrive;
  public static OperateIntake m_OperateIntake;
  public static Intake intake;
  public static Constants m_constants;
  public static OperateElevatord m_OperateElevatord;
  public static Elevator elevator;
 
  private Command m_autonomousCommand;
  SendableChooser<Command> m_chooser;

  private RobotContainer m_robotContainer;
 
  private final TalonFXControlMode CONTROLMODE = TalonFXControlMode.PercentOutput;
  private double startTime;
  private final WPI_TalonSRX topLeft = new WPI_TalonSRX(0);
  private final WPI_TalonSRX topRight = new WPI_TalonSRX(2);
  private final WPI_VictorSPX bottomLeft = new WPI_VictorSPX(1);
  private final WPI_VictorSPX bottomRight = new WPI_VictorSPX(3);

  private final WPI_TalonSRX topIntakeMotor = new WPI_TalonSRX(4);
  private final WPI_TalonSRX bottomIntakeMotor = new WPI_TalonSRX(5);

  private final double kDriveTick2Feet = 1.0 / 4069 * 6 * Math.PI / 12;
  private final double kDriveTick2Deg = 306.0 / 512 * 26 / 42 * 18 / 60 * 18 / 84;
  
  private final Encoder encoder = new Encoder(0, 1, false, EncodingType.k4X);


  final double kP = 0.5; //Constants here need to be fixed to our robots mass!!!!! (FIXME)
  final double kI = 0.5;
  final double kD = 0.1;
  final double iLimit = 1;

  double distance = 10;
  double setpoint = 45;
  double errorSum = 0;
  double lastTimestamp = 0;
  double lastError = 0;
  double outputSpeed = 0;

  boolean turnLeft = false;
  boolean turnRight = true;
  boolean isDriving = false;
  boolean isTurning = true;
  boolean isOuttaking = false;

  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    //Setting Motors to invert
    topLeft.setInverted(true);
    topRight.setInverted(true);
    topIntakeMotor.setInverted(true);

    //initialize Encoders
    topLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    topRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    topIntakeMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);

    //Reset encoder values
    topLeft.setSelectedSensorPosition(0,0,10);
    topRight.setSelectedSensorPosition(0,0,10);
    topIntakeMotor.setSelectedSensorPosition(0,0,10);

    //Start Compressor
      //FIXME = true;
    //Compressor.start();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.

    SmartDashboard.putNumber("encoder value",encoder.get() * kDriveTick2Feet);
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("Intake Encoder Value", topIntakeMotor.getSelectedSensorPosition() * kDriveTick2Deg);
    SmartDashboard.putNumber("Left Drive Encoder Value", topLeft.getSelectedSensorPosition() * kDriveTick2Feet);
    SmartDashboard.putNumber("Right Drive Encoder Value", topRight.getSelectedSensorPosition() * kDriveTick2Feet);
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
    enableMotors(false);
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    
    encoder.reset();
    errorSum = 0;
    lastError = 0;
    lastTimestamp = Timer.getFPGATimestamp();
    enableMotors(true);

    topLeft.setSelectedSensorPosition(0,0,10);
    topRight.setSelectedSensorPosition(0,0,10);
    topIntakeMotor.setSelectedSensorPosition(0,0,10);

    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }


  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

    //Driving in Auto
    if(isDriving == true && isTurning == false && isOuttaking == false) {
      //get sensor postion
      double sensorPosition = encoder.get() * kDriveTick2Feet;

      //calculations
      double error = setpoint - sensorPosition;
      double dt = Timer.getFPGATimestamp() - lastTimestamp;

      if(Math.abs(error)<iLimit){
      errorSum += error * dt;
      }

      double errorRate = (error - lastError) / dt;

      outputSpeed = kP * error + kI * errorSum + kD * errorRate;

      //OUTPUT SPEED IS USED FOR THE GENTLE STOP

      
      Robot.drivetrain.runLeftDrive(outputSpeed);
      Robot.drivetrain.runRightDrive(outputSpeed);
    }
    
else
    //Turning in auto
    if(isDriving == false && isTurning == true && isOuttaking == false) {
      //get sensor postion
      double sensorPosition = encoder.get() * kDriveTick2Deg;

      //calculations
      double error = setpoint - sensorPosition;
      double dt = Timer.getFPGATimestamp() - lastTimestamp;

      if(Math.abs(error)<iLimit){
      errorSum += error * dt;
      }

      double errorRate = (error - lastError) / dt;

      outputSpeed = kP * error + kI * errorSum + kD * errorRate;

      //OUTPUT SPEED IS USED FOR THE GENTLE STOP
 
      if(turnLeft == true){
        Robot.drivetrain.runLeftDrive(-outputSpeed);
        Robot.drivetrain.runRightDrive(outputSpeed);
      }
      else
      if(turnRight == true){
        Robot.drivetrain.runLeftDrive(outputSpeed);
        Robot.drivetrain.runRightDrive(-outputSpeed);
      }
    }

     //Outtaking in auto
     if(isDriving == false && isTurning == false && isOuttaking == true) {

    }

    //Macro Procedure If Statements
    if(outputSpeed == 0 && isTurning == true && turnRight == true) 
    {
      turnRight = false;
      isTurning = false;
      setpoint = Math.sqrt(2 * distance * distance);
      isDriving = true;      
    }
    else
    if(outputSpeed == 0 && isDriving == true && setpoint == Math.sqrt(2 * distance * distance)) 
    {
      isDriving = false;
      turnLeft = true;
      setpoint = 45;
      isTurning = true;
    }
    else
    if(outputSpeed == 0 && isTurning == true && turnLeft == true) 
    {
      turnLeft = false;
      isTurning = false;
      setpoint = distance - 1;
      isDriving = true;
    }


    //Udate last time variables
    lastTimestamp = Timer.getFPGATimestamp();  

    final double leftPosition = topLeft.getSelectedSensorPosition() * kDriveTick2Feet;
    final double rightPosition = topRight.getSelectedSensorPosition() * kDriveTick2Feet;
  

   



  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  

  private void enableMotors(final boolean on) {

    NeutralMode mode;

    if(on) 
    {
      mode = NeutralMode.Brake;
    }
    else 
    {
      mode = NeutralMode.Coast;
    }

    topLeft.setNeutralMode(mode);
    topRight.setNeutralMode(mode);
    bottomLeft.setNeutralMode(mode);
    bottomRight.setNeutralMode(mode);  
    topIntakeMotor.setNeutralMode(mode);
    bottomIntakeMotor.setNeutralMode(mode);

  }
}
