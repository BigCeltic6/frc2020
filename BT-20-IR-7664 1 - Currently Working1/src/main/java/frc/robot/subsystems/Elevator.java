/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSd license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.OperateElevatord;

public class Elevator extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
    PWMVictorSPX RIGHT_OperateElevatord_MOTOR;
    PWMVictorSPX LEFT_OperateElevatord_MOTOR;
    SpeedControllerGroup OperateElevatord;
    public static OperateElevatord instance;

    //Motor Indication Within Elevator
  public void OperateElevatord() {          //pwmvictrsrx
    this.RIGHT_OperateElevatord_MOTOR = new PWMVictorSPX(6);
    this.LEFT_OperateElevatord_MOTOR = new PWMVictorSPX(7);
    }

        public void OperateElevatord(final double speed) {
    this.OperateElevatord.set(speed);
    this.LEFT_OperateElevatord_MOTOR.set(-speed);

  }

  public void runOperateElevatord(final double speed) {
    this.OperateElevatord.set(speed);
    this.LEFT_OperateElevatord_MOTOR.set(-speed);
    }

    public void initdefaultCommand() {
        this.setDefaultCommand((Command) new frc.robot.commands.OperateElevatord());
    }

{
}

public void runElevator(final double d) {
}}