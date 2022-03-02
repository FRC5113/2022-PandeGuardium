package frc.robot.commands;

import static frc.robot.Constants.IndexerConstants.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class IndexerCommand extends CommandBase {

  private Indexer mIndexer;
  private boolean mShouldFinish; // used for auton sequential command
  private Timer timer; // used to count a second when making sure the ball is being shot

  public IndexerCommand(Indexer indexer, boolean shouldFinish) {
    addRequirements(indexer);
    mIndexer = indexer;
    mShouldFinish = shouldFinish;

    timer = new Timer();
    System.out.println("Running indexer");
  }

  @Override
  public void execute() {
    mIndexer.setSpeed(INDEXER_SPEED);
  }

  @Override
  public boolean isFinished() {
    if (mShouldFinish) {
      // timer.get() is in seconds
      if (timer.get() > 1) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    mIndexer.stop();
  }
}
