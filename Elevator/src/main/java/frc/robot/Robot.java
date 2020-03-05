/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.AutonomousTurnCommand;
import frc.robot.commands.OperateElevator;
import frc.robot.commands.OperateIntake;
import frc.robot.commands.OperatePneumatics;
//import frc.robot.subsystems.PIDDrivetrain;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Encoder;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static OI m_oi;
  public static Drivetrain m_drivetrain;
  public static ArcadeDrive m_arcadeDrive;
  public static Intake m_intake;
  public static OperateIntake m_operateIntake;
  public static Elevator m_elevator;
  public static OperateElevator m_operateElevator;
  public static Pneumatics m_pneumatics;
  public static OperatePneumatics m_operatePneumatics;
  //public static PIDDrivetrain m_pidDrivetrain;

  public static double m_startTime;

  public static final double kAngleSetpoint = 0.0;
  public static final double kPgyro = 0.005; // propotional turning constant
  public static final double kVoltsPerDegreePerSecond = 0.0128;
  public static final SPI.Port kGyroPort = SPI.Port.kOnboardCS0;

  public static final double kSamplePeriod = 0.001;
  public static final double kCalibrationSampleTime = 5.0;
  public static final double kDegreePerSecondPerLSB = 0.0125;

  public static ADXRS450_Gyro m_gyro = new ADXRS450_Gyro();

  Command m_autonomousCommand;
  Command m_autonoumousTurnCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    Robot.m_oi = new OI();
    m_chooser.setDefaultOption("Default Auto", new AutonomousTurnCommand());
    m_chooser.addOption("Turn Auto", new AutonomousCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    m_gyro.calibrate();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_startTime = Timer.getFPGATimestamp();
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (this.m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    double turningValue = (kAngleSetpoint - m_gyro.getAngle()) * kPgyro;
		// Invert the direction of the turn if we are going backwards
		//turningValue = Math.copySign(turningValue, m_joystick.getY());
    //m_myRobot.arcadeDrive(m_joystick.getY(), turningValue);
    SmartDashboard.putNumber("Potentiometer Value", m_gyro.getAngle());
    SmartDashboard.putNumber("Turning Value", turningValue);
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  static{
    Robot.m_drivetrain = new Drivetrain();
    Robot.m_intake = new Intake();
    Robot.m_elevator = new Elevator();
    Robot.m_pneumatics = new Pneumatics();
    //Robot.m_pidDrivetrain = new PIDDrivetrain();

  }
}
