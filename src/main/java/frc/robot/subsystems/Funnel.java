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
    private LaserCan m_lc1 = new LaserCan(FunnelConstants.LaserId1);
    private LaserCan m_lc2 = new LaserCan(FunnelConstants.LaserId2);

    public Funnel(){
        configureMotor();

        //TODO change/test values
        try{
            m_lc1.setRangingMode(RangingMode.SHORT); 
            m_lc1.setRegionOfInterest(new LaserCan.RegionOfInterest(8,8,16,16));
            m_lc1.setTimingBudget(TimingBudget.TIMING_BUDGET_20MS);
        } catch(ConfigurationFailedException e){
            System.out.println("LC1: configuration failed:" + e);
        }

        try{
            m_lc2.setRangingMode(RangingMode.SHORT); 
            m_lc2.setRegionOfInterest(new LaserCan.RegionOfInterest(8,8,16,16));
            m_lc2.setTimingBudget(TimingBudget.TIMING_BUDGET_20MS);
        } catch(ConfigurationFailedException e){
            System.out.println("LC2: configuration failed:" + e);
        }

        
    }

    public int getMeasurement1(){
        LaserCan.Measurement measurement = m_lc1.getMeasurement();
        if (measurement != null && measurement.status == LaserCan.LASERCAN_STATUS_VALID_MEASUREMENT){
            return measurement.distance_mm;
        }
        else{
            return 20; //TODO change test value 
        }
    }

    public int getMeasurement2(){
        LaserCan.Measurement measurement = m_lc2.getMeasurement();
        if (measurement != null && measurement.status == LaserCan.LASERCAN_STATUS_VALID_MEASUREMENT){
            return measurement.distance_mm;
        }
        else{
            return 20; //TODO change test value 
        }
    }

    public Command setPower(double desiredVolts){
        return runOnce(() -> m_motor.setVoltage(desiredVolts));
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
