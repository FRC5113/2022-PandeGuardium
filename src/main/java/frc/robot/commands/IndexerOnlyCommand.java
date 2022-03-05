package frc.robot.commands;

import static frc.robot.Constants.IndexerConstants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class IndexerOnlyCommand extends CommandBase {

  private Indexer mIndexer;

  public IndexerOnlyCommand(Indexer indexer) {
    addRequirements(indexer);
    mIndexer = indexer;
    System.out.println("Running indexer only");
  }

  @Override
  public void execute() {
    mIndexer.setSpeed(INDEXER_SPEED);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    mIndexer.stop();
  }
}
