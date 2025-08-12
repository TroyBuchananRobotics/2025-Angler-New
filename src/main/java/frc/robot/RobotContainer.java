// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import frc.robot.commands.DriveCommands;
import frc.robot.commands.ScoringCommands;
import frc.robot.constants.DriveConstants;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.AlgaeClaw;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.CoralClaw;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Wrist;

//test commit
//breh test 2

public class RobotContainer {

    private final Elevator m_elevator = new Elevator();
    private final Wrist m_wrist = new Wrist();
    private final CoralClaw m_coralClaw = new CoralClaw();
    private final AlgaeClaw m_algaeClaw = new AlgaeClaw();


    private final Telemetry logger = new Telemetry(DriveConstants.MAX_SPEED);

    private final CommandXboxController driverController = new CommandXboxController(0);
    private final CommandGenericHID operatorController = new CommandGenericHID(1);

    public final CommandSwerveDrivetrain m_drivetrain = TunerConstants.createDrivetrain();

    public RobotContainer() {
        configureBindings();
    }

    private void configureBindings() {
        // Note that X is defined as forward according to WPILib convention,
        // and Y is defined as to the left according to WPILib convention.
        m_drivetrain.setDefaultCommand(
                DriveCommands.fieldOrientedDrive(
                        m_drivetrain,
                        () -> driverController.getLeftY(),
                        () -> driverController.getLeftX(),
                        () -> driverController.getRightX()));
    


        // reset the field-centric heading on left bumper press
        operatorController.button(12).onTrue(ScoringCommands.goL1(m_elevator, m_wrist));
        operatorController.button(9).onTrue(ScoringCommands.goL2(m_elevator, m_wrist));
        operatorController.button(6).onTrue(ScoringCommands.goL3(m_elevator, m_wrist));
        operatorController.button(3).onTrue(ScoringCommands.goL4(m_elevator, m_wrist));
        operatorController.button(11).onTrue(ScoringCommands.goProcessor(m_elevator, m_wrist));
        operatorController.button(2).onTrue(ScoringCommands.goBarge(m_elevator, m_wrist));
        operatorController.button(10).onTrue(ScoringCommands.goHome(m_elevator, m_wrist));
        operatorController.button(8).onTrue(ScoringCommands.goLow(m_elevator, m_wrist));
        operatorController.button(5).onTrue(ScoringCommands.goHigh(m_elevator, m_wrist));
        
        driverController.povRight().onTrue(m_drivetrain.runOnce(() -> m_drivetrain.seedFieldCentric()));
        driverController.rightTrigger().onTrue(m_coralClaw.SetPower(2.5)).onFalse(m_coralClaw.stop());
        driverController.leftTrigger().onTrue(m_coralClaw.SetPower(-2.5)).onFalse(m_coralClaw.SetPower(-0.8));
        m_drivetrain.registerTelemetry(logger::telemeterize);
        driverController.rightBumper().onTrue(m_algaeClaw.SetVoltage(2.5)).onFalse(m_algaeClaw.stop());
        driverController.leftBumper().onTrue(m_algaeClaw.SetVoltage(-2.5));
    }

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }
}
