package frc.robot.commands;

import static frc.robot.Constants.IndexerConstants.*;
import static frc.robot.Constants.IntakeConstants.*;
import static frc.robot.Constants.ShooterConstants.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class IntakeForShooterCommand extends CommandBase {

  private Intake mIntake;
  private Indexer mIndexer;
  private Shooter mShooter;
  private boolean mShouldFinish;
  private Timer timer;
  private boolean mIsAuton;

  public IntakeForShooterCommand(
      Intake intake, Indexer indexer, Shooter shooter, boolean shouldFinish, boolean isAuton) {
    System.out.println("running new intake command");
    addRequirements(intake);
    mIntake = intake;
    mIndexer = indexer;
    mShooter = shooter;
    mShouldFinish = shouldFinish;
    mIsAuton = isAuton;
    timer = new Timer();
    timer.start();
    System.out.println(shouldFinish + "!!!!!!!!!!!!");
  }

  @Override
  public void execute() {
    // System.out.println("running intake command");
    mIntake.setSpeed(INTAKE_SPEED);
    mIndexer.setSpeed(INDEXER_SPEED);
    System.out.println(timer.get());
  }

  @Override
  public boolean isFinished() {
    if (mShouldFinish) {
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
