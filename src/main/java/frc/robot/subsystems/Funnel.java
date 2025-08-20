package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import au.grapplerobotics.ConfigurationFailedException;
import au.grapplerobotics.LaserCan;
import au.grapplerobotics.interfaces.LaserCanInterface.RangingMode;
import au.grapplerobotics.interfaces.LaserCanInterface.TimingBudget;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.FunnelConstants;

public class Funnel extends SubsystemBase {
    
    private static final TalonFX m_motor = new TalonFX(FunnelConstants.MotorID);
   

    public Funnel(){
        configureMotor();

        

        
    }



    public Command setPower(double desiredVolts){
        return runOnce(() -> m_motor.setVoltage(desiredVolts));
    }
    public void setPowerNotCmd(double desiredVolts){
        m_motor.setVoltage(desiredVolts);
    }

    public Command stop(){
        return runOnce(() -> m_motor.stopMotor());
    }

    private void configureMotor(){
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.withMotorOutput(new MotorOutputConfigs()
            .withNeutralMode(FunnelConstants.neutralMode)
            .withInverted(FunnelConstants.invertedMode));
        config.withCurrentLimits(new CurrentLimitsConfigs()
            .withStatorCurrentLimitEnable(true)
            .withStatorCurrentLimit(FunnelConstants.statorCurrentLimit)
            .withSupplyCurrentLimitEnable(true)
            .withSupplyCurrentLimit(FunnelConstants.supplyCurrentLimit));
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Funnel Current", m_motor.getStatorCurrent().getValueAsDouble());
    }

}
