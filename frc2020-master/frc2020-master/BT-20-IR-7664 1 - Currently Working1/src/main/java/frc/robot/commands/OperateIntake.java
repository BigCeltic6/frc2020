/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class OperateIntake extends CommandBase {

    public static float INTAKE_POWER_PERCENT;

  public OperateIntake(Intake m_intake) {
    this.requires((Subsystem)Robot.drivetrain);

    // Use addRequirements() here to declare subsystem dependencies.
    
  }

    public OperateIntake() {
	}

	private void requires(Subsystem intake) {
    }

    // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    final boolean leftTrigger = Robot.m_robotcontainer.getDriveLeftTrigger();
    final boolean rightTrigger = Robot.m_robotcontainer.getDriveRightTrigger();
    final boolean backButton = Robot.m_robotcontainer.getDriveBackButton();
    double engage;
    if (leftTrigger) {
        engage = 0.8;
    }
    else if (rightTrigger) {
        engage = -1.0;
    }
    else if (backButton) {
        engage = 1.142857;
    }
    else {
        engage = 0.0;
    }
        ((Intake) Robot.intake).runIntake(0.699999988079071 * engage);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  static {
    OperateIntake.INTAKE_POWER_PERCENT = 0.7f;
}

}