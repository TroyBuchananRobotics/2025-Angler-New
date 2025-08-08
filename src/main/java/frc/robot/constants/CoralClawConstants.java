package frc.robot.constants;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class CoralClawConstants {
    public static final int MotorID = 16;

    public static final InvertedValue invertedMode = InvertedValue.Clockwise_Positive;
    public static final NeutralModeValue neutralMode = NeutralModeValue.Brake;

    public static final double statorCurrentLimit = 50.0;
    public static final double supplyCurrentLimit = 20.0;


}
