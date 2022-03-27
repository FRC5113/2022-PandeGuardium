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
public class OneBallsAuton extends CommandBase {
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

  public OneBallsAuton(
      Shooter shooter, Indexer indexer, Limelight limelight, DriveTrain drivetrain, Intake intake) {
    addRequirements(shooter, indexer, limelight, intake, drivetrain);
    this.shooter = shooter;
    this.indexer = indexer;
    this.limelight = limelight;
    this.intake = intake;
    this.drivetrain = drivetrain;
    flyWheelSpeed = 0;
    desiredSpeed = 10000;
  }

  @Override
  public void initialize() {
    timer = new Timer();
    timer2 = new Timer();
    timer.start();
  }

  @Override
  public void execute() {
    if (flyWheelSpeed < desiredSpeed && shouldstarttimer) {
      flyWheelSpeed += ShooterConstants.rampUpRate;
    }
    // 1.34 seconds to drive 1.5 meters
    if (timer.get() < 2) {
      drivetrain.tankDrive(-0.5 * DriveConstants.autonSpeed, -0.5 * DriveConstants.autonSpeed);
    }
    if (timer.get() >= 2) {
      drivetrain.tankDrive(0, 0);
      desiredSpeed = limelight.getDesiredSpeed(); // 15000
      // limelight.getDesiredSpeed();
      if (flyWheelSpeed >= desiredSpeed) {
        if (shouldstarttimer) {
          timer2.start();
        }
        shouldstarttimer = false;
      }
      if (timer2.get() > 4) {
        flyWheelSpeed -= ShooterConstants.rampDownRate;
        if (flyWheelSpeed < 0) {
          flyWheelSpeed = 0;
        }
      }
    }

    if (flyWheelSpeed >= desiredSpeed && timer.get() < 14) {
      indexer.setSpeed(IndexerConstants.INDEXER_SPEED);
      intake.setSpeed(IntakeConstants.INTAKE_SPEED);
    }

    if (timer.get() >= 14) {
      indexer.setSpeed(0);
      intake.setSpeed(0);
      shooter.setSpeed(0);
    }

    shooter.setSpeed(flyWheelSpeed); // put this back in
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
