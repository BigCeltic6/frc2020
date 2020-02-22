/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.OperateIntake;
import frc.robot.commands.OperateElevatord;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Elevator;


/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
    private final Drivetrain m_drivetrain = new Drivetrain();
    private final Intake m_intake = new Intake();
    private final Elevator m_elevator = new Elevator();

    private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
    private final ArcadeDrive m_ArcadeDrive = new ArcadeDrive(m_drivetrain);
    private final OperateIntake m_OperateIntake = new OperateIntake(m_intake);
    private final OperateElevatord m_OperateElevatord = new OperateElevatord(m_elevator);

    Joystick drivePad;
    Joystick toolPad;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        this.drivePad = new Joystick(0);
        this.toolPad = new Joystick(1);

        // Configure the button bindings
        configureButtonBindings();
    }

    public ArcadeDrive getM_ArcadeDrive() {
        return m_ArcadeDrive;
    }

    public OperateIntake getM_OperateIntake() {
        return m_OperateIntake;
    }

    public OperateElevatord getM_OperateElevatord() {
        return m_OperateElevatord;
    }


    public double getDriveRightY() {
    final double joy = this.drivePad.getRawAxis(3);
    if (Math.abs(joy) < 0.05) {
        return 0.0;
    }
    return joy;
}

public double getDriveLeftY() {
    final double joy = this.drivePad.getRawAxis(1);
    if (Math.abs(joy) < 0.05) {
        return 0.0;
    }
    return joy;
}

public double getDriveRightX() {
    final double joy = this.drivePad.getRawAxis(2);
    if (Math.abs(joy) < 0.05) {
        return 0.0;
    }
    return joy;
}

public double getDriveLeftX() {
    final double joy = this.drivePad.getRawAxis(0);
    if (Math.abs(joy) < 0.05) {
        return 0.0;
    }
    return joy;
}

public int printDpad() {
    return this.drivePad.getPOV(0);
}

public boolean getDriveDPadX() {
    return this.drivePad.getRawButton(4);
}

public boolean getDriveDPadY() {
    return this.drivePad.getRawButton(5);
}

public boolean getDriveRightTrigger() {
    return this.drivePad.getRawButton(8);
}

public boolean getDriveLeftTrigger() {
    return this.drivePad.getRawButton(7);
}

public boolean getDriveRightBumper() {
    return this.drivePad.getRawButton(6);
}

public boolean getDriveLeftBumper() {
    return this.drivePad.getRawButton(5);
}

public boolean getDriveXButton() {
    return this.drivePad.getRawButton(1);
}

public boolean getDriveAButton() {
    return this.drivePad.getRawButton(2);
}

public boolean getDriveBButton() {
    return this.drivePad.getRawButton(3);
}

public boolean getDriveYButton() {
    return this.drivePad.getRawButton(4);
}

public boolean getDriveStartButton() {
    return this.drivePad.getRawButton(10);
}

public boolean getDriveBackButton() {
    return this.drivePad.getRawButton(9);
}

public boolean getDriveLeftAnalogButton() {
    return this.drivePad.getRawButton(11);
}

public boolean getDriveRightAnalogButton() {
    return this.drivePad.getRawButton(12);
}

public double getToolRightY() {
    final double joy = this.toolPad.getRawAxis(3);
    if (Math.abs(joy) < 0.05) {
        return 0.0;
    }
    return joy;
}

public double getToolLeftY() {
    final double joy = this.toolPad.getRawAxis(1);
    if (Math.abs(joy) < 0.05) {
        return 0.0;
    }
    return joy;
}

public double getToolRightX() {
    final double joy = this.toolPad.getRawAxis(2);
    if (Math.abs(joy) < 0.05) {
        return 0.0;
    }
    return joy;
}

public double getToolLeftX() {
    final double joy = this.toolPad.getRawAxis(0);
    if (Math.abs(joy) < 0.05) {
        return 0.0;
    }
    return joy;
}

public double getToolDPadX() {
    return this.toolPad.getPOVCount();
}

public double getToolDPadY() {
    return this.toolPad.getPOV(5);
}

public boolean getToolRightTrigger() {
    return this.toolPad.getRawButton(8);
}

public boolean getToolLeftTrigger() {
    return this.toolPad.getRawButton(7);
}

public boolean getToolRightBumper() {
    return this.toolPad.getRawButton(6);
}

public boolean getToolLeftBumper() {
    return this.toolPad.getRawButton(5);
}

public boolean getToolXButton() {
    return this.toolPad.getRawButton(1);
}

public boolean getToolAButton() {
    return this.toolPad.getRawButton(2);
}

public boolean getToolBButton() {
    return this.toolPad.getRawButton(3);
}

public boolean getToolYButton() {
    return this.toolPad.getRawButton(4);
}

public boolean getToolStartButton() {
    return this.toolPad.getRawButton(10);
}

public boolean getToolBackButton() {
    return this.toolPad.getRawButton(9);
}

public boolean getToolLeftAnalogButton() {
    return this.toolPad.getRawButton(11);
}

public boolean getToolRightAnalogButton() {
    return this.toolPad.getRawButton(12);
}
  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
      
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
