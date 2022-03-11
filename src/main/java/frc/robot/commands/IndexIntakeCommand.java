package frc.robot.commands;

import static frc.robot.Constants.IndexerConstants.*;
import static frc.robot.Constants.IntakeConstants.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

/** A class that runs the intake, indexer, or both. Make sure you use the correct parameters. */
public class IndexIntakeCommand extends CommandBase {

  private Indexer mIndexer;
  private Intake mIntake;
  private boolean shouldFinish; // used for auton sequential command
  private boolean useIntake; // should run the intake
  private boolean useIndexer; // should run the indexer
  private Timer timer; // used to count a second when making sure the ball is being shot

  public IndexIntakeCommand(
      Indexer indexer, Intake intake, boolean shouldFinish, boolean useIndexer, boolean useIntake) {
    addRequirements(indexer, intake);
    mIndexer = indexer;
    mIntake = intake;
    this.shouldFinish = shouldFinish;
    this.useIndexer = useIndexer;
    this.useIntake = useIntake;

    timer = new Timer();
    timer.start();
    // System.out.println("Running indexer");
  }

  @Override
  public void execute() {
    if (useIndexer) {
      mIndexer.setSpeed(INDEXER_SPEED);
    }
    if (useIntake) {
      mIntake.setSpeed(INTAKE_SPEED);
    }
  }

  @Override
  public boolean isFinished() {
    if (shouldFinish) {
      // timer.get() is in seconds
      if (timer.get() > 1) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    if (useIndexer) {
      mIndexer.stop();
    }
    if (useIntake) {
      mIntake.stop();
    }
  }
}
