package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.OperateElevator;

public class Elevator extends Subsystem
{
    PWMVictorSPX TOP_ELEVATOR_MOTOR;
    PWMVictorSPX BOTTOM_ELEVATOR_MOTOR;

    SpeedControllerGroup Elevatorpower;
     
    public Elevator() {
        this.TOP_ELEVATOR_MOTOR = new PWMVictorSPX(5);
        this.BOTTOM_ELEVATOR_MOTOR = new PWMVictorSPX(6);

    } 
    
    public void runElevatorPower(final double output) {
        this.TOP_ELEVATOR_MOTOR.set(output);
        this.BOTTOM_ELEVATOR_MOTOR.set(output);
    }

    public void initDefaultCommand() {
        this.setDefaultCommand((Command)new OperateElevator());

    }
    
}
