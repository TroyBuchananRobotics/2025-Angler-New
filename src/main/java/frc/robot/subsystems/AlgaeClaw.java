package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.AlgaeClawConstants;

public class AlgaeClaw extends SubsystemBase {

    TalonFX m_motor = new TalonFX(AlgaeClawConstants.MotorID);

    public Command SetVoltage(double voltage){
        return runOnce(()-> m_motor.setVoltage(voltage));
    }
    public Command stop(){
        return runOnce(()-> m_motor.stopMotor());
    }

    public boolean hasAlgae(){
        return (m_motor.getStatorCurrent().getValueAsDouble()>20.0);
    }
    
    public AlgaeClaw(){
        configureMotor();
    }

    private void configureMotor(){
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.withMotorOutput(new MotorOutputConfigs()
            .withNeutralMode(AlgaeClawConstants.neutralMode)
            .withInverted(AlgaeClawConstants.invertedMode));
        config.withCurrentLimits(new CurrentLimitsConfigs()
        .withStatorCurrentLimitEnable(true)
        .withStatorCurrentLimit(AlgaeClawConstants.statorCurrentLimit)
        .withSupplyCurrentLimitEnable(true)
        .withSupplyCurrentLimit(AlgaeClawConstants.supplyCurrentLimit));
    }
}

