package frc.robot.commands;

import static frc.robot.Constants.IndexerConstants.*;
import static frc.robot.Constants.IntakeConstants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

public class OuttakeCommand extends CommandBase {

  private Intake mIntake;
  private Indexer mIndexer;

  public OuttakeCommand(Intake intake, Indexer indexer) {
    addRequirements(intake, indexer);
    mIntake = intake;
    mIndexer = indexer;
  }

  @Override
  public void execute() {
    mIntake.setSpeed(-1 * INTAKE_SPEED);
    mIndexer.setSpeed(-1 * INDEXER_SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    mIntake.stop();
  }
}
