package frc.robot.commands;

import static frc.robot.Constants.IndexerConstants.*;
import static frc.robot.Constants.IntakeConstants.*;
import static frc.robot.Constants.ShooterConstants.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.enums.ShouldStop;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class IntakeForShooterCommand extends CommandBase {

  private Intake mIntake;
  private Indexer mIndexer;
  private Shooter mShooter;
  private ShouldStop shouldStop;
  private Timer timer;

  public IntakeForShooterCommand(
      Intake intake, Indexer indexer, Shooter shooter, ShouldStop shouldStop) {
    System.out.println("running new intake command");
    addRequirements(intake, indexer, shooter);
    mIntake = intake;
    mIndexer = indexer;
    mShooter = shooter;
    this.shouldStop = shouldStop;
    timer = new Timer();
    timer.start();
    System.out.println(shouldStop.shouldStop() + "!!!!!!!!!!!!");
  }

  @Override
  public void execute() {
    // System.out.println("running intake command");
    mIntake.setSpeed(INTAKE_SPEED);
    mIndexer.setSpeed(INDEXER_SPEED);
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
    mIntake.stop();
    mIndexer.stop();
    // mShooter.coast();
    /*
    if (mShooter.getSpeed() >= 0 && mIsAuton) {
      mShooter.setSpeed(mShooter.getSpeed() - rampDownRate);
    }
    */
  }
}
