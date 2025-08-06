package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.SoftwareLimitSwitchConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.ElevatorConstants;
import frc.robot.constants.ScoringConstants.Home;

//Someone needs to add stuff here for realz

public class Elevator extends SubsystemBase {
    TalonFX m_leader = new TalonFX(ElevatorConstants.leader_MotorID,"*");
    TalonFX m_follower = new TalonFX(ElevatorConstants.follower_MotorID,"*");
    MotionMagicVoltage m_request = new MotionMagicVoltage(Home.kElevator);
    

    public Elevator(){
        configureMotors();
        m_follower.setControl(new Follower(13, true));

    }

    public Command setPosition(double setPose){
        return runOnce(()-> m_leader.setControl( m_request.withPosition(setPose)));
    }

    public Command idleMode(){
        return runOnce(()-> m_leader.stopMotor());
    }

    private void configureMotors(){
        TalonFXConfiguration leaderConfig = new TalonFXConfiguration();
        leaderConfig.withCurrentLimits(new CurrentLimitsConfigs()
            .withStatorCurrentLimit(ElevatorConstants.leaderStatorCurrentLimit)
            .withStatorCurrentLimitEnable(true)
            .withSupplyCurrentLimit(ElevatorConstants.leaderSupplyCurrentLimit)
            .withStatorCurrentLimitEnable(true));
        leaderConfig.withSlot0(new Slot0Configs()
            .withKP(ElevatorConstants.leaderKP)
            .withKD(ElevatorConstants.leaderKD)
            .withKS(ElevatorConstants.leaderKS)
            .withKV(ElevatorConstants.leaderKV)
            .withKG(ElevatorConstants.leaderKG));
        leaderConfig.withSoftwareLimitSwitch(new SoftwareLimitSwitchConfigs()
            .withForwardSoftLimitEnable(true)
            .withForwardSoftLimitThreshold(ElevatorConstants.leaderForwardSoftLimit)
            .withReverseSoftLimitEnable(true)
            .withReverseSoftLimitThreshold(ElevatorConstants.leaderReverseSoftLimit));
        leaderConfig.withMotorOutput(new MotorOutputConfigs()
            .withInverted(InvertedValue.Clockwise_Positive)
            .withNeutralMode(NeutralModeValue.Brake));
        leaderConfig.withMotionMagic(new MotionMagicConfigs()
            .withMotionMagicCruiseVelocity(ElevatorConstants.leaderMotionCruiseVelo)
            .withMotionMagicAcceleration(ElevatorConstants.leaderMotionAccel)
            .withMotionMagicExpo_kV(ElevatorConstants.leaderMotionExpo_kV)
            .withMotionMagicExpo_kA(ElevatorConstants.leaderMotionExpo_kA));
        leaderConfig.withFeedback(new FeedbackConfigs()
            .withFeedbackRotorOffset(-0.31));
        m_leader.getConfigurator().apply(leaderConfig);

        TalonFXConfiguration followerConfig = new TalonFXConfiguration();
        followerConfig.withCurrentLimits(new CurrentLimitsConfigs()
            .withStatorCurrentLimit(ElevatorConstants.followerStatorCurrentLimit)
            .withStatorCurrentLimitEnable(true)
            .withSupplyCurrentLimit(ElevatorConstants.followerSupplyCurrentLimit)
            .withStatorCurrentLimitEnable(true));
        
        m_follower.getConfigurator().apply(followerConfig);        

    }
    @Override 
    public void periodic(){
       SmartDashboard.putNumber("Elevator Position", m_leader.getPosition().getValueAsDouble());
    }
}
