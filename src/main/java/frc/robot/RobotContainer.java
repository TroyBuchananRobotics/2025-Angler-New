// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import frc.robot.commands.DriveCommands;
import frc.robot.commands.ScoringCommands;
import frc.robot.constants.DriveConstants;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Wrist;

//test commit
//breh test 2

public class RobotContainer {

    private final Elevator m_elevator = new Elevator();
    private final Wrist m_wrist = new Wrist();


    private final Telemetry logger = new Telemetry(DriveConstants.MAX_SPEED);

    private final CommandXboxController driverController = new CommandXboxController(0);

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
        driverController.leftBumper().onTrue(m_drivetrain.runOnce(() -> m_drivetrain.seedFieldCentric()));
        driverController.a().onTrue(ScoringCommands.goL1(m_elevator, m_wrist)).onFalse(ScoringCommands.goHome(m_elevator, m_wrist));
        driverController.b().onTrue(ScoringCommands.goL2(m_elevator, m_wrist)).onFalse(ScoringCommands.goHome(m_elevator, m_wrist));
        driverController.x().onTrue(ScoringCommands.goL3(m_elevator, m_wrist)).onFalse(ScoringCommands.goHome(m_elevator, m_wrist));
        driverController.y().onTrue(ScoringCommands.goL4(m_elevator, m_wrist)).onFalse(ScoringCommands.goHome(m_elevator, m_wrist));
        m_drivetrain.registerTelemetry(logger::telemeterize);

    }

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }
}
