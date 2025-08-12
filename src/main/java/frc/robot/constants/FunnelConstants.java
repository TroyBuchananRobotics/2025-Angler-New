package frc.robot.constants;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class FunnelConstants {
    
    public static final int MotorID = 20;

    public static final InvertedValue invertedMode = InvertedValue.CounterClockwise_Positive;
    public static final NeutralModeValue neutralMode = NeutralModeValue.Brake;

    public static final double statorCurrentLimit = 30.0;
    public static final double supplyCurrentLimit = 20.0;
}
