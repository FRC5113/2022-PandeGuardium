package frc.robot.commands;

import static frc.robot.Constants.IndexerConstants.*;
import static frc.robot.Constants.IntakeConstants.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.enums.IntakeSystemMoters;
import frc.robot.enums.ShouldStop;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

/** A class that runs the intake, indexer, or both. Make sure you use the correct parameters. */
public class IndexIntakeCommand extends CommandBase {

  private Indexer mIndexer;
  private Intake mIntake;
  private ShouldStop shouldStop; // used for auton sequential command
  private IntakeSystemMoters useMotors;
  private Timer timer; // used to count a second when making sure the ball is being shot

  public IndexIntakeCommand(
      Indexer indexer, Intake intake, IntakeSystemMoters useMotors, ShouldStop shouldStop) {
    addRequirements(indexer, intake);
    mIndexer = indexer;
    mIntake = intake;
    this.useMotors = useMotors;
    this.shouldStop = shouldStop;

    timer = new Timer();
    timer.start();
    // System.out.println("Running indexer");
  }

  @Override
  public void execute() {
    if (useMotors.usingIndexer() && useMotors.isForward()) {
      mIndexer.setSpeed(INDEXER_SPEED);
    }
    if (useMotors.usingIntake() && useMotors.isForward()) {
      mIntake.setSpeed(INTAKE_SPEED);
    }

    if (useMotors.usingIndexer() && useMotors.isBackward()) {
      mIndexer.setSpeed(-INDEXER_SPEED);
    }
    if (useMotors.usingIntake() && useMotors.isBackward()) {
      mIntake.setSpeed(-INTAKE_SPEED);
    }
  }

  @Override
  public boolean isFinished() {
    if (shouldStop.shouldStop()) {
      // timer.get() is in seconds
      if (timer.get() > 1.5) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    if (useMotors.usingIndexer()) {
      mIndexer.stop();
    }
    if (useMotors.usingIntake()) {
      mIntake.stop();
    }
  }
}
