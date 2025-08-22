// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.BooleanSupplier;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.events.EventTrigger;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


import frc.robot.commands.DriveCommands;
import frc.robot.commands.IntakeCoral;
import frc.robot.commands.ScoringCommands;
import frc.robot.constants.DriveConstants;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.AlgaeClaw;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.CoralClaw;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Funnel;
import frc.robot.subsystems.Wrist;

//test commit
//breh test 2

public class RobotContainer {

    private final SendableChooser<Command> autoChooser;
    private final Elevator m_elevator = new Elevator();
    private final Wrist m_wrist = new Wrist();
    private final CoralClaw m_coralClaw = new CoralClaw();
    private final AlgaeClaw m_algaeClaw = new AlgaeClaw();
    private final Funnel m_funnel = new Funnel();

    private final Telemetry logger = new Telemetry(DriveConstants.MAX_SPEED);

    private final CommandXboxController driverController = new CommandXboxController(0);
    private final CommandGenericHID operatorController = new CommandGenericHID(1);

    public final CommandSwerveDrivetrain m_drivetrain = TunerConstants.createDrivetrain();

    public RobotContainer() {

        autoChooser = AutoBuilder.buildAutoChooser();
        SmartDashboard.putData("Auto Chooser: ", autoChooser);

        
        
        configureButtonBindings();
        configureEvents();
    }

    private void configureEvents(){

        
        new EventTrigger("Coral Outtake").onTrue(m_coralClaw.SetPower(2.5)).onFalse(m_coralClaw.stop());
        new EventTrigger("Coral Intake").onTrue(new IntakeCoral(m_funnel, m_elevator, m_wrist, m_coralClaw))
            .onFalse(ScoringCommands.goHome(m_elevator, m_wrist));
        new EventTrigger("L1").onTrue(ScoringCommands.goL1(m_elevator, m_wrist))
            .onFalse(ScoringCommands.goHome(m_elevator, m_wrist));
        new EventTrigger("L2").onTrue(ScoringCommands.goL2(m_elevator, m_wrist))
            .onFalse(ScoringCommands.goHome(m_elevator, m_wrist));
        new EventTrigger("L3").onTrue(ScoringCommands.goL3(m_elevator, m_wrist))
            .onFalse(ScoringCommands.goHome(m_elevator, m_wrist));

    }
    
    private void configureButtonBindings() {
        // Note that X is defined as forward according to WPILib convention,
        // and Y is defined as to the left according to WPILib convention.
        m_drivetrain.setDefaultCommand(
                DriveCommands.fieldOrientedDrive(
                        m_drivetrain,
                        () -> driverController.getLeftY(),
                        () -> driverController.getLeftX(),
                        () -> driverController.getRightX()));
    


        //if only left trigger is held go to L1 / if algae move to processor  
        driverController.leftTrigger()
            .onTrue(new ConditionalCommand(
                ScoringCommands.goProcessor(m_elevator, m_wrist), 
                ScoringCommands.goL1(m_elevator, m_wrist), 
                m_algaeClaw::hasAlgae))
            .onFalse(ScoringCommands.goHome(m_elevator, m_wrist));
        
        //if left trigger and button 9 are pressed move to L2 / if right bumper move Low Algae
        driverController.leftTrigger().and(operatorController.button(9))
            .onTrue(ScoringCommands.goL2(m_elevator, m_wrist))
            .onFalse(ScoringCommands.goHome(m_elevator, m_wrist));
        driverController.rightBumper().and(operatorController.button(9))
            .onTrue(ScoringCommands.goLow(m_elevator, m_wrist).alongWith(m_algaeClaw.SetVoltage(2.5)))
            .onFalse(ScoringCommands.goHome(m_elevator, m_wrist).alongWith(m_algaeClaw.stop()));
        
        //if left trigger and button 6 are pressed move to L3 / if right bumper move High Algae
        driverController.leftTrigger().and(operatorController.button(6))
            .onTrue(ScoringCommands.goL3(m_elevator, m_wrist))
            .onFalse(ScoringCommands.goHome(m_elevator, m_wrist));
        driverController.rightBumper().and(operatorController.button(6))
            .onTrue(ScoringCommands.goHigh(m_elevator, m_wrist).alongWith(m_algaeClaw.SetVoltage(2.5)))
            .onFalse(ScoringCommands.goHome(m_elevator, m_wrist).alongWith(m_algaeClaw.stop()));
        
        //if left trigger and button 3 are pressed move to L4 / If algae go barge
        driverController.leftTrigger().and(operatorController.button(3))
            .onTrue(new ConditionalCommand(
                ScoringCommands.goBarge(m_elevator, m_wrist),
                ScoringCommands.goL4(m_elevator, m_wrist), 
                m_algaeClaw::hasAlgae))
            .onFalse(ScoringCommands.goHome(m_elevator, m_wrist));

        //D-Pad right resets gyro
        driverController.povRight().onTrue(m_drivetrain.runOnce(() -> m_drivetrain.seedFieldCentric()));
        
        //right trigger, universal outtake
        driverController.rightTrigger().onTrue(new ConditionalCommand(
                m_algaeClaw.SetVoltage(2.5), 
                m_coralClaw.SetPower(2.5), 
                m_algaeClaw::hasAlgae))
            .onFalse(m_coralClaw.stop().alongWith(m_algaeClaw.stop()));
                
        //left bumper coral intake
        driverController.leftBumper()
            .onTrue(new IntakeCoral(m_funnel, m_elevator, m_wrist, m_coralClaw))
            .onFalse(ScoringCommands.goHome(m_elevator, m_wrist));

        /* 
        driverController.leftBumper()
            .onTrue(ScoringCommands.goLoad(m_elevator, m_wrist, m_funnel, m_coralClaw))
            .onFalse(m_coralClaw.SetPower(-0.8).alongWith(ScoringCommands.goHome(m_elevator, m_wrist)).alongWith(m_funnel.stop()));
*/
        m_drivetrain.registerTelemetry(logger::telemeterize);
    }

    public Command getAutonomousCommand() {
        return autoChooser.getSelected();
    }
}
