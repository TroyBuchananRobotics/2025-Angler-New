package frc.robot.commands;

import javax.lang.model.element.ElementVisitor;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.ScoringConstants;
import frc.robot.constants.ScoringConstants.Barge;
import frc.robot.constants.ScoringConstants.High;
import frc.robot.constants.ScoringConstants.Home;
import frc.robot.constants.ScoringConstants.L1;
import frc.robot.constants.ScoringConstants.L2;
import frc.robot.constants.ScoringConstants.L3;
import frc.robot.constants.ScoringConstants.L4;
import frc.robot.constants.ScoringConstants.Load;
import frc.robot.constants.ScoringConstants.Lollipop;
import frc.robot.constants.ScoringConstants.Low;
import frc.robot.constants.ScoringConstants.Processor;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Wrist;

public class ScoringCommands {
    public static Command goHome( Elevator elevator, Wrist wrist){
        return(elevator.setPosition(Home.kElevator).alongWith(wrist.setPosition(Home.kWrist)));
    }
    
    public static Command goL1(Elevator elevator, Wrist wrist){
        return(elevator.setPosition(L1.kElevator).alongWith(wrist.setPosition(L1.kWrist)));
    }

    public static Command goL2(Elevator elevator, Wrist wrist){
        return(elevator.setPosition(L2.kElevator).alongWith(wrist.setPosition(L2.kWrist)));
    }

    public static Command goL3(Elevator elevator, Wrist wrist){
        return(elevator.setPosition(L3.kElevator).alongWith(wrist.setPosition(L3.kWrist)));
    }

    public static Command goL4(Elevator elevator, Wrist wrist){
        return(elevator.setPosition(L4.kElevator).alongWith(wrist.setPosition(L4.kWrist)));
    }

    public static Command goLoad(Elevator elevator, Wrist wrist){
        return(elevator.setPosition(Load.kElevator).alongWith(wrist.setPosition(Load.kWrist)));
    }

    public static Command goHigh(Elevator elevator, Wrist wrist){
        return(elevator.setPosition(High.kElevator).alongWith(wrist.setPosition(High.kWrist)));
    }

    public static Command goLow(Elevator elevator, Wrist wrist){
        return(elevator.setPosition(Low.kElevator).alongWith(wrist.setPosition(Low.kWrist)));
    }
    
    public static Command goBarge(Elevator elevator, Wrist wrist){
        return(elevator.setPosition(Barge.kElevator).alongWith(wrist.setPosition(Barge.kWrist)));
    }

    public static Command goProcessor(Elevator elevator, Wrist wrist){
        return(elevator.setPosition(Processor.kElevator).alongWith(wrist.setPosition(Processor.kWrist)));
    }

    public static Command goLollipop(Elevator elevator, Wrist wrist){
        return(elevator.setPosition(Lollipop.kElevator).alongWith(wrist.setPosition(Lollipop.kWrist)));
    }
}
