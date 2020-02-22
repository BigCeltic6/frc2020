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
import frc.robot.subsystems.Elevator;

public class OperateElevatord extends CommandBase {

    public static float INTAKE_POWER_PERCENT;

  public OperateElevatord(Elevator m_elevator) {
    this.requires((Subsystem)Robot.drivetrain);

    // Use addRequirements() here to declare subsystem dependencies.
    
  }

    public OperateElevatord() {
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
    final boolean DpadUp = Robot.m_robotcontainer.getDriveDPadX();
    final boolean DpadDown = Robot.m_robotcontainer.getDriveDPadY();
    double engage;

    if (DpadUp) {
        engage = 0.8;
    }
    else if (DpadDown) {
        engage = -1.0;
    }
    else {
        engage = 0.0;
    }
        ((Elevator) Robot.elevator).runElevator(0.699999988079071 * engage);
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
    OperateElevatord.INTAKE_POWER_PERCENT = 0.7f;
}

}