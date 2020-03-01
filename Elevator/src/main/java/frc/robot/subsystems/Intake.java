package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.OperateIntake;

public class Intake extends Subsystem
{
    PWMVictorSPX INTAKE_MOTOR;
    private static Intake instance;
    
    public Intake() {
        this.INTAKE_MOTOR = new PWMVictorSPX(4);
    }
    
    public static Intake getInstance() {
        if (Intake.instance == null) {
            Intake.instance = new Intake();
        }
        return Intake.instance;
    }
    
    public void runintake(final double speed) {
        this.INTAKE_MOTOR.set(speed);
    }
    
    public void initDefaultCommand() {
        this.setDefaultCommand((Command)new OperateIntake());
    }
    
    public void button() {
    }
    
    static {
        Intake.instance = null;
    }
}
