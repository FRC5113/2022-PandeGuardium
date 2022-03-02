package frc.robot.commands;

import static frc.robot.Constants.IndexerConstants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class OuttakeSlightlyCommand extends CommandBase {

  private Indexer mIndexer;
  private int currentTicks = 0;

  /**
   * Move the ball back slightly to avoid premature firing
   *
   * @param indexer
   */
  public OuttakeSlightlyCommand(Indexer indexer) {
    addRequirements(indexer);
    mIndexer = indexer;
    // currentTicks = 0;
    System.out.println("Running outtake slightly");
  }

  @Override
  public void execute() {
    mIndexer.setSpeed(-1 * INDEXER_SPEED);
    currentTicks += 1;
  }

  @Override
  public boolean isFinished() {
    return currentTicks > PERIODIC_TICKS;
  }

  @Override
  public void end(boolean interrupted) {
    mIndexer.stop();
  }
}
