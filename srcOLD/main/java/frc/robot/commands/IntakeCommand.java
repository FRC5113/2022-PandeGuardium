package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

import static frc.robot.Constants.IntakeConstants.*;

public class IntakeCommand extends CommandBase {

    private Intake intake;

    public IntakeCommand(Intake intake) {
        addRequirements(intake);
        this.intake = intake;
    }

    @Override
    public void execute() {
        this.intake.setSpeed(INTAKE_SPEED);
    }

    @Override
    public void end(boolean interrupted) {
        this.intake.stop();
    }

}