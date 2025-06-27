package frc.robot.constants;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public final class ElevatorConstants {
    public static final int leader_MotorID = 13;
    public static final int follower_MotorID = 14;

    public static final double leaderStatorCurrentLimit = 65.0;
    public static final double leaderSupplyCurrentLimit = 40.0;

    public static final double leaderKP = 10.0;
    public static final double leaderKD = 0.1;
    public static final double leaderKS = 0.13;
    public static final double leaderKV = 0.12;
    public static final double leaderKG = 0.55;

    public static final double leaderForwardSoftLimit = 22.15;
    public static final double leaderReverseSoftLimit = 0.1;

    public static final NeutralModeValue neutralMode = NeutralModeValue.Brake;
    public static final InvertedValue invertedMode = InvertedValue.Clockwise_Positive;

    public static final double leaderMotionCruiseVelo = 40.0;
    public static final double leaderMotionAccel = 100.0;
    public static final double leaderMotionExpo_kV = 0.12;
    public static final double leaderMotionExpo_kA = 0.1;

    public static final double followerStatorCurrentLimit = 65.0;
    public static final double followerSupplyCurrentLimit = 40.0;
}
