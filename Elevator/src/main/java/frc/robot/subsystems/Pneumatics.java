// 
// Decompiled by Procyon v0.5.36
// 

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.OperatePneumatics;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem
{
    private static Pneumatics instance;
    public static final int COMPRESSOR_PCM_ID = 0;
    //public static final int BOOM_FORWARD_CHANNEL_ID = 3;
    //public static final int BOOM_REVERSE_CHANNEL_ID = 2;
    public static final int WRIST_FORWARD_CHANNEL_ID = 1;
    public static final int WRIST_REVERSE_CHANNEL_ID = 0;
    //public static final int DEAD_FORWARD_CHANNEL_ID = 7;
   // public static final int DEAD_REVERSE_CHANNEL_ID = 6;
    //public static final int MORT_FORWARD_CHANNEL_ID = 5;
    //public static final int MORT_REVERSE_CHANNEL_ID = 4;
    Compressor c;
   // DoubleSolenoid boom;
    DoubleSolenoid wrist;
   // DoubleSolenoid dead;
    //DoubleSolenoid mort;
    
    public Pneumatics() {
        this.c = new Compressor(0);
        //this.boom = new DoubleSolenoid(0, 3, 2);
        this.wrist = new DoubleSolenoid(0, 1, 0);
        //this.dead = new DoubleSolenoid(0, 7, 6);
        //this.mort = new DoubleSolenoid(0, 5, 4);
        this.c.setClosedLoopControl(true);
    }
    
    public void firstForward() {
        this.wrist.set(DoubleSolenoid.Value.kForward);
    }
    
    public void firstReverse() {
        this.wrist.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void firstOff() {
        this.wrist.set(DoubleSolenoid.Value.kOff);
    }
   /* 
    public void secondForward() {
        this.boom.set(DoubleSolenoid.Value.kForward);
    }
    
    public void secondReverse() {
        this.boom.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void secondOff() {
        this.boom.set(DoubleSolenoid.Value.kOff);
    }
    
    public void thirdForward() {
        this.dead.set(DoubleSolenoid.Value.kForward);
    }
    
    public void thirdReverse() {
        this.dead.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void thirdOff() {
        this.dead.set(DoubleSolenoid.Value.kOff);
    }
    
    public void fourthForward() {
        this.mort.set(DoubleSolenoid.Value.kForward);
    }
    
    public void fourthReverse() {
        this.mort.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void fourthOff() {
        this.mort.set(DoubleSolenoid.Value.kOff);
    }
    */
    public static Pneumatics getInstance() {
        if (Pneumatics.instance == null) {
            Pneumatics.instance = new Pneumatics();
        }
        return Pneumatics.instance;
    }
    
    public void initDefaultCommand() {
        this.setDefaultCommand((Command)new OperatePneumatics());
    }
    
    static {
        Pneumatics.instance = null;
    }
}
