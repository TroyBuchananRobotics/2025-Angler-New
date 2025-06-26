package frc.robot.constants;


import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public final class ClimberConstants {
    public static final int kMotorID = 18;
    
    public static final double kS = 0.1;
    public static final double kV = 0.12;
    public static final double kP = 20.0;

    public static final double forwardSoftLimit = 150.0;
    public static final double reverseSoftLimit = -10.0;
    
    public static final double statorCurrentLimit = 50.0;
    public static final double supplyCurrentLimit = 20.0;

    public static NeutralModeValue neutralMode = NeutralModeValue.Brake;
    public static InvertedValue invertedMode = InvertedValue.CounterClockwise_Positive;

}
