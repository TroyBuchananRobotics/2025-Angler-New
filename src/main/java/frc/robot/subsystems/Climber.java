package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.ClimberConstants;

public class Climber extends SubsystemBase {
    TalonFX m_motor = new TalonFX(ClimberConstants.kMotorID);

    public Climber(){
        configureMotor();
    }

    private void configureMotor(){
        TalonFXConfiguration motorConfig = new TalonFXConfiguration();
        motorConfig.withCurrentLimits(new CurrentLimitsConfigs()
        .withStatorCurrentLimit(ClimberConstants.statorCurrentLimit)
        .withStatorCurrentLimitEnable(true)
        .withSupplyCurrentLimit(ClimberConstants.supplyCurrentLimit)
        .withSupplyCurrentLimitEnable(true));
        motorConfig.withSlot0(new Slot0Configs()
        .withKP(ClimberConstants.KP)
        .withKS(ClimberConstants.KS)
        .withKV(ClimberConstants.KV));
        motorConfig.withSoftwareLimitSwitch(new SoftwareLimitSwitchConfigs()
        .withForwardSoftLimitEnable(true)
        .withForwardSoftLimitThreshold(ClimberConstants.forwardSoftLimit)
        .withReverseSoftLimitEnable(true)
        .withReverseSoftLimitThreshold(ClimberConstants.reverseSoftLimit));
    }


}
