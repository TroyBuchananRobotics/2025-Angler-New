package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.CoralClawConstants;

public class CoralClaw extends SubsystemBase {
    private final TalonFX m_motor = new TalonFX(CoralClawConstants.MotorID);

    public CoralClaw(){
        configureMotors();
    }

    public Command SetPower(double DesiredVolts){
        return runOnce(()-> m_motor.setVoltage(DesiredVolts));
    }

    public Command stop(){
        return runOnce(()-> m_motor.stopMotor());
    }


    private void configureMotors(){
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.withMotorOutput(new MotorOutputConfigs()
            .withInverted(CoralClawConstants.invertedMode)
            .withNeutralMode(CoralClawConstants.neutralMode));
        config.withCurrentLimits(new CurrentLimitsConfigs()
            .withStatorCurrentLimitEnable(true)
            .withSupplyCurrentLimitEnable(true)
            .withSupplyCurrentLimit(CoralClawConstants.supplyCurrentLimit)
            .withStatorCurrentLimit(CoralClawConstants.statorCurrentLimit));
        m_motor.getConfigurator().apply(config);
    }
}
