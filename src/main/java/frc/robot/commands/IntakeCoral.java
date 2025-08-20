package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.ScoringConstants;
import frc.robot.constants.ScoringConstants.Home;
import frc.robot.constants.ScoringConstants.IntakeConstants;
import frc.robot.subsystems.CoralClaw;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Funnel;
import frc.robot.subsystems.Wrist;

public class IntakeCoral extends Command {

    private Funnel m_funnel;
    private Elevator m_elevator;
    private Wrist m_wrist;
    private CoralClaw m_coralClaw;

    private boolean Lc1Triggered = false;

    public IntakeCoral(Funnel funnel, Elevator elevator, Wrist wrist, CoralClaw coralClaw){
        m_funnel = funnel;
        m_elevator = elevator;
        m_wrist = wrist;
        m_coralClaw = coralClaw;
        addRequirements(m_coralClaw, m_elevator, m_funnel, m_wrist);
    }

    @Override
    public void initialize() {
        m_elevator.setPositionNotCmd(IntakeConstants.kElevator);
        m_wrist.setPositionNotCmd(IntakeConstants.kWrist);
        m_funnel.setPowerNotCmd(IntakeConstants.kFunnelVoltage);
        m_coralClaw.SetPowerNotCmd(3.5);
    }

    @Override
    public void execute() {
        if (m_coralClaw.getMeasurement1()< IntakeConstants.klaserMeasurment){
            m_coralClaw.SetPowerNotCmd(2.5);
            Lc1Triggered = true;
        }
        if (m_coralClaw.hasCoral() && Lc1Triggered){
            m_coralClaw.stop();
            m_funnel.stop();
        }
    }


    @Override
    public void end(boolean interrupted) {
        m_wrist.setPositionNotCmd(Home.kWrist);
        m_elevator.setPositionNotCmd(Home.kElevator);
    }

    @Override
    public boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }
}

