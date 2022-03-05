package frc.robot.commands;

import static frc.robot.Constants.IndexerConstants.*;
import static frc.robot.Constants.IntakeConstants.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class StopAllMotersCommand extends CommandBase {

  Indexer mIndexer;
  Intake mIntake;
  Shooter mShooter;
  DriveTrain mDriveTrain;
  Timer timer;

  public StopAllMotersCommand(
      Indexer indexer, Intake intake, Shooter shooter, DriveTrain driveTrain) {
    addRequirements(indexer, intake, shooter, driveTrain);
    mIndexer = indexer;
    mIntake = intake;
    mShooter = shooter;
    mDriveTrain = driveTrain;
    timer = new Timer();
    timer.start();
    System.out.println("STOPPING ALL!");
  }

  @Override
  public void execute() {
    if (timer.get() <= 1) {
      mIntake.setSpeed(0);
      mIndexer.setSpeed(0);
      mShooter.setSpeed(0);
      mDriveTrain.tankDrive(0, 0);
    }
  }

  @Override
  public boolean isFinished() {
    return timer.get() > 1;
  }

  @Override
  public void end(boolean interrupted) {
    mIntake.stop();
  }
}
