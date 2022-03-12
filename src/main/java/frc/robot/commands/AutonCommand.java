package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.IndexerConstants;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

/*
public class ShootCommand extends ParallelCommandGroup {

  public ShootCommand(Shooter shooter, Indexer indexer, Limelight limelight) {
    super(
        new SpinUpCommand(shooter, limelight),
        new SequentialCommandGroup(new WaitCommand(5), new IndexerCommand(indexer)));
  }
}

*/

// public class AutonCommand extends SequentialCommandGroup {
public class AutonCommand extends CommandBase {
  public Shooter shooter;
  public Indexer indexer;
  public Limelight limelight;
  public Intake intake;
  public DriveTrain drivetrain;
  public Timer timer;
  public Timer timer2;
  public int flyWheelSpeed;
  public int desiredSpeed;
  public double intakeStartTime;
  public boolean shouldstarttimer = true;

  public AutonCommand(
      Shooter shooter, Indexer indexer, Limelight limelight, DriveTrain drivetrain, Intake intake) {
    addRequirements(shooter, indexer, limelight, intake, drivetrain);
    this.shooter = shooter;
    this.indexer = indexer;
    this.limelight = limelight;
    this.intake = intake;
    this.drivetrain = drivetrain;
    flyWheelSpeed = 0;
    desiredSpeed = 10000;
    timer = new Timer();
    timer.start();
  }

  @Override
  public void execute() {
    if (flyWheelSpeed < desiredSpeed && shouldstarttimer) {
      flyWheelSpeed += ShooterConstants.rampUpRate;
    }
    // 1.34 seconds to drive 1.5 meters
    if (timer.get() < 2) {
      drivetrain.tankDrive(0.5 * DriveConstants.autonSpeed, -0.5 * DriveConstants.autonSpeed);
      intake.setSpeed(4 * IntakeConstants.INTAKE_SPEED);
    }

    if (timer.get() >= 2 && timer.get() < 2.05) {
      intake.setSpeed(0);
    }
    // 0.7 seconds
    if (timer.get() >= 2.05 && timer.get() < 2.74) {
      drivetrain.tankDrive(0, 0);
      intake.setSpeed(-0.25 * IntakeConstants.INTAKE_SPEED);
    }
    // 0.05 seconds
    if (timer.get() >= 2.74 && timer.get() < 2.79) {
      intake.setSpeed(0);
    }
    // 1.71 seconds to turn 180
    if (timer.get() >= 2.79 && timer.get() < 4.33) {
      drivetrain.tankDrive(-0.5 * DriveConstants.autonSpeed, -0.5 * DriveConstants.autonSpeed);
    }

    if (timer.get() >= 4.33) {
      drivetrain.tankDrive(0, 0);
    }

    if (timer.get() >= 6) {

      desiredSpeed = 15000;
      // limelight.getDesiredSpeed();
      if (shooter.getSpeed() >= desiredSpeed) {
        if (shouldstarttimer) {
          timer2.start();
        }
        shouldstarttimer = false;
        if (timer2.get() > 7.5) {
          flyWheelSpeed -= ShooterConstants.rampDownRate;
        }
      }
    }

    if (timer.get() >= 7.5 && timer.get() < 14) {
      indexer.setSpeed(IndexerConstants.INDEXER_SPEED);
      intake.setSpeed(IntakeConstants.INTAKE_SPEED);
    }

    if (timer.get() >= 14) {
      indexer.setSpeed(0);
      intake.setSpeed(0);
      shooter.setSpeed(0);
    }

    // shooter.setSpeed(flyWheelSpeed); // put this back in
  }

  @Override
  public boolean isFinished() {
    return shouldstarttimer && flyWheelSpeed <= 0;
  }

  // @Override
  public void end() {
    intake.setSpeed(0);
    indexer.setSpeed(0);
    // shooter.coast(); put this back in
  }
}
