/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.OperateIntake;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;

public class Intake extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
    VictorSP TOP_INTAKE_MOTOR;
    VictorSP BOTTOM_INTAKE_MOTOR;
    SpeedControllerGroup intake;
    private static Intake instance;

  public Intake() {
    this.TOP_INTAKE_MOTOR = new VictorSP(4);
    this.BOTTOM_INTAKE_MOTOR = new VictorSP(5);
    }

  public static Intake getInstance() {
    if (Intake.instance == null) {
        Intake.instance = new Intake();
        }
    return Intake.instance;
    }


  public void runIntake(final double speed) {
    this.TOP_INTAKE_MOTOR.set(speed);
    this.BOTTOM_INTAKE_MOTOR.set(-speed);
    }

    public void initDefaultCommand() {
        this.setDefaultCommand((Command)new OperateIntake());
    }

}
