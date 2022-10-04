package frc.robot.commands;

import static frc.robot.Constants.IndexerConstants.*;
import static frc.robot.Constants.IntakeConstants.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.enums.IntakeSystemMotors;
import frc.robot.enums.ShouldStop;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

/** A class that runs the intake, indexer, or both. Make sure you use the correct parameters. */
public class IndexIntakeCommand extends CommandBase {

  private Indexer mIndexer;
  private Intake mIntake;
  private Shooter mShooter;
  // private ShouldStop shouldStop; // used for auton sequential command
  private IntakeSystemMotors useMotors;
  private Timer timer; // used to count a second when making sure the ball is being shot

  public IndexIntakeCommand(
      Indexer indexer,
      Intake intake,
      Shooter shooter,
      IntakeSystemMotors useMotors,
      ShouldStop shouldStop) {
    addRequirements(indexer, intake);
    mIndexer = indexer;
    mIntake = intake;
    mShooter = shooter;
    this.useMotors = useMotors;
  }

  @Override
  public void execute() {
    if (useMotors.isForward()) {
      mIndexer.setSpeed(INDEXER_SPEED);
      mIntake.setSpeed(INTAKE_SPEED);
    } else {
      mIndexer.setSpeed(-INDEXER_SPEED);
      mIntake.setSpeed(-INTAKE_SPEED);
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  // @Override
  public void end(boolean interrupted) {
    mIndexer.stop();
    mIntake.stop();
    mShooter.coast();
  }

  public void end() {
    mIndexer.stop();
    mIntake.stop();
    mShooter.coast();
  }
}
