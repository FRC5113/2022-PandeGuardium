package frc.robot.commands;

import static frc.robot.Constants.IndexerConstants.*;
import static frc.robot.Constants.IntakeConstants.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

public class IndexAllCommand extends CommandBase {

  private Indexer mIndexer;
  private Intake mIntake;
  private boolean mShouldFinish; // used for auton sequential command
  private Timer timer; // used to count a second when making sure the ball is being shot

  public IndexAllCommand(Indexer indexer, Intake intake, boolean shouldFinish) {
    addRequirements(indexer, intake);
    mIndexer = indexer;
    mIntake = intake;
    mShouldFinish = shouldFinish;

    timer = new Timer();
    timer.start();
    System.out.println("Running indexer");
  }

  @Override
  public void execute() {
    mIndexer.setSpeed(INDEXER_SPEED);
    mIntake.setSpeed(INTAKE_SPEED);
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
    mIntake.stop();
  }
}
