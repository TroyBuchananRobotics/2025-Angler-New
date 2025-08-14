package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralClaw;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Funnel;
import frc.robot.subsystems.Wrist;

public class IntakeCoral extends Command {

    private Funnel m_funnel;
    private Elevator m_elevator;
    private Wrist m_wrist;
    private CoralClaw m_coralClaw;

    public IntakeCoral(Funnel funnel, Elevator elevator, Wrist wrist, CoralClaw coralClaw){
        m_funnel = funnel;
        m_elevator = elevator;
        m_wrist = wrist;
        m_coralClaw = coralClaw;
        addRequirements(m_coralClaw, m_elevator, m_funnel, m_wrist);
    }

    @Override
    public void initialize() {
        
        

    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        // TODO Auto-generated method stub
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        // TODO Auto-generated method stub
        return super.isFinished();
    }
}

