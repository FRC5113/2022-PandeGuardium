package frc.robot.commands;

import static frc.robot.Constants.IndexerConstants.*;
import static frc.robot.Constants.IntakeConstants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeOnlyCommand extends CommandBase {

  private Intake mIntake;

  public IntakeOnlyCommand(Intake intake) {
    addRequirements(intake);
    mIntake = intake;

    System.out.println("Running indexer only");
  }

  @Override
  public void execute() {
    mIntake.setSpeed(INTAKE_SPEED);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    mIntake.stop();
  }
}
