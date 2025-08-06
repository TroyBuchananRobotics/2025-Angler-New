package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.ClimberConstants;

public class Climber extends SubsystemBase {
    TalonFX m_motor = new TalonFX(ClimberConstants.kMotorID);
    MotionMagicVoltage m_request = new MotionMagicVoltage(0);

    public Climber(){
        configureMotor();
    }


    public Command setPosition(double desirePos){
        return runOnce(() -> m_motor.setControl(m_request.withPosition(desirePos)));
    }

    public Command idleMotor(){
        return runOnce(() -> m_motor.stopMotor());
    }






    private void configureMotor(){
        TalonFXConfiguration motorConfig = new TalonFXConfiguration();

        motorConfig.withCurrentLimits(new CurrentLimitsConfigs()
        .withStatorCurrentLimit(ClimberConstants.statorCurrentLimit)
        .withStatorCurrentLimitEnable(true)
        .withSupplyCurrentLimit(ClimberConstants.supplyCurrentLimit)
        .withSupplyCurrentLimitEnable(true));

        motorConfig.withSlot0(new Slot0Configs()
        .withKP(ClimberConstants.kP)
        .withKS(ClimberConstants.kS)
        .withKV(ClimberConstants.kV));

        motorConfig.withSoftwareLimitSwitch(new SoftwareLimitSwitchConfigs()
        .withForwardSoftLimitEnable(true)
        .withForwardSoftLimitThreshold(ClimberConstants.forwardSoftLimit)
        .withReverseSoftLimitEnable(true)
        .withReverseSoftLimitThreshold(ClimberConstants.reverseSoftLimit));

        motorConfig.withMotorOutput(new MotorOutputConfigs()
        .withNeutralMode(ClimberConstants.neutralMode)
        .withInverted(ClimberConstants.invertedMode));

        m_motor.getConfigurator().apply(motorConfig);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Climber Position", m_motor.getPosition().getValueAsDouble());
    }


}
