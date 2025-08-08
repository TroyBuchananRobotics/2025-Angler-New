package frc.robot.constants;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class AlgaeClawConstants {
        public final static int MotorID = 17;

        public static final InvertedValue invertedMode = InvertedValue.Clockwise_Positive;
        public static final NeutralModeValue neutralMode = NeutralModeValue.Brake;

        public static final double statorCurrentLimit = 30.0;
        public static final double supplyCurrentLimit = 20.0;
}
