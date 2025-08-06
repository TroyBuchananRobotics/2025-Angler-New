package frc.robot.subsystems;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.CoralClawConstants;

public class CoralClaw extends SubsystemBase {
    private final TalonFX m_motor = new TalonFX(CoralClawConstants.MotorID);

    public Command SetPower(double DesiredVolts){
        return runOnce(()-> m_motor.setVoltage(DesiredVolts));
    }

    public Command stop(){
        return runOnce(()-> m_motor.stopMotor());
    }
}
