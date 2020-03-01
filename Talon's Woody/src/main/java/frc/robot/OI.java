/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

// 

package frc.robot;

//import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.GenericHID;
//import edu.wpi.first.wpilibj.buttons.JoystickButton;
//import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.Joystick;

public class OI
{
    Joystick drivePad;
    Joystick toolPad;
    
    public OI() {
        this.drivePad = new Joystick(0);
        this.toolPad = new Joystick(1);
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
}
