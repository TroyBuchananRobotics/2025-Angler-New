package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.WristConstants;
import frc.robot.constants.ScoringConstants.Home;

public class Wrist extends SubsystemBase {
    private final TalonFX m_motor = new TalonFX(WristConstants.kMotorID);
    private final CANcoder m_encoder = new CANcoder(1);
    private final MotionMagicVoltage m_request = new MotionMagicVoltage(Home.kWrist);
    
    public Wrist(){
        configureMotors();
    }

    public Command setPosition(double pos){
        return runOnce(() -> m_request.withPosition(pos));
    }







    private void configureMotors(){
        TalonFXConfiguration motorConfig = new TalonFXConfiguration();
     
        motorConfig.withCurrentLimits(new CurrentLimitsConfigs()
            .withStatorCurrentLimit(WristConstants.statorCurrentLimit)
            .withStatorCurrentLimitEnable(true)
            .withSupplyCurrentLimit(WristConstants.supplyCurrentLimit)
            .withSupplyCurrentLimitEnable(true));
       
        motorConfig.withMotorOutput(new MotorOutputConfigs()
            .withInverted(WristConstants.invertedMode)
            .withNeutralMode(WristConstants.neutralMode));

        motorConfig.withSlot0(new Slot0Configs()
            .withKP(WristConstants.kP)
            .withKD(WristConstants.kD)
            .withKS(WristConstants.kS)
            .withKV(WristConstants.kV));

        motorConfig.withSoftwareLimitSwitch(new SoftwareLimitSwitchConfigs()
            .withForwardSoftLimitEnable(true)
            .withForwardSoftLimitThreshold(WristConstants.forwardSoftLimit)
            .withReverseSoftLimitEnable(true)
            .withReverseSoftLimitThreshold(WristConstants.reverseSoftLimit));
        
        motorConfig.withMotionMagic(new MotionMagicConfigs()
            .withMotionMagicAcceleration(WristConstants.motionMagicAccel)
            .withMotionMagicCruiseVelocity(WristConstants.motionMagicCruiseVelo));
    }

    
}
