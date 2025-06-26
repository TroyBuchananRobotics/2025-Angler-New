package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Wrist extends SubsystemBase {
    private final TalonFX m_motor = new TalonFX(0);
    private final CANcoder m_encoder = new CANcoder(1);
    
}
