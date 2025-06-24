package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
    TalonFX m_leader = new TalonFX(13);
    TalonFX m_follower = new TalonFX(14);

    public Elevator(){
        configureMotors();
        m_follower.setControl(new Follower(13, true));
    }

    private void configureMotors(){
        TalonFXConfiguration leaderConfig = new TalonFXConfiguration();
        leaderConfig.withCurrentLimits(new CurrentLimitsConfigs()
            .withStatorCurrentLimit(65)
            .withStatorCurrentLimitEnable(true)
            .withSupplyCurrentLimit(40)
            .withStatorCurrentLimitEnable(true));
        leaderConfig.withSlot0(new Slot0Configs()
            .withKP(10)
            .withKD(0.1)
            .withKS(0.13)
            .withKV(0.12)
            .withKG(0.55));
        leaderConfig.withSoftwareLimitSwitch(new SoftwareLimitSwitchConfigs()
            .withForwardSoftLimitEnable(true)
            .withForwardSoftLimitThreshold(22.15)
            .withReverseSoftLimitEnable(true)
            .withReverseSoftLimitThreshold(0.1));
        leaderConfig.withMotorOutput(new MotorOutputConfigs()
            .withInverted(InvertedValue.Clockwise_Positive)
            .withNeutralMode(NeutralModeValue.Brake));
        m_leader.getConfigurator().apply(leaderConfig);

        TalonFXConfiguration followerConfig = new TalonFXConfiguration();
        followerConfig.withCurrentLimits(new CurrentLimitsConfigs()
            .withStatorCurrentLimit(65)
            .withStatorCurrentLimitEnable(true)
            .withSupplyCurrentLimit(40)
            .withStatorCurrentLimitEnable(true));
        
        m_follower.getConfigurator().apply(followerConfig);        

    }
    
}
