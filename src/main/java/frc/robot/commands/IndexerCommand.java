package frc.robot.commands;

import static frc.robot.Constants.IndexerConstants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class IndexerCommand extends CommandBase {

  private Indexer mIndexer;

  public IndexerCommand(Indexer indexer) {
    addRequirements(indexer);
    mIndexer = indexer;
  }

  @Override
  public void execute() {
    mIndexer.setSpeed(INDEXER_SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    mIndexer.stop();
  }
}
