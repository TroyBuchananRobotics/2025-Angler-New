package frc.robot.constants;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class WristConstants {
    public static final int kMotorID = 15;
    public static final double statorCurrentLimit = 60.0;
    public static final double supplyCurrentLimit = 30.0;
    public static final double forwardSoftLimit = 30.0;
    public static final double reverseSoftLimit = 1.0;


    public static final double kP = 50.0;
    public static final double kD = 0.1;
    public static final double kS = 0.15;
    public static final double kV = .118;

    public static final NeutralModeValue neutralMode = NeutralModeValue.Brake;
    public static final InvertedValue invertedMode = InvertedValue.Clockwise_Positive;

    public static final double motionMagicCruiseVelo = 50.0;
    public static final double motionMagicAccel = 150.0;
}
