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
  public boolean runningIntake = false;

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
    if (flyWheelSpeed < desiredSpeed && !runningIntake) {
      flyWheelSpeed += ShooterConstants.rampUpRate;
    }

    if (timer.get() < 1.5) {
      drivetrain.tankDrive(-DriveConstants.autonSpeed, DriveConstants.autonSpeed);
    } else if (timer.get() >= 1.5) {
      drivetrain.tankDrive(0, 0);
      desiredSpeed = limelight.getDesiredSpeed();
      if (shooter.getSpeed() >= desiredSpeed) {
        if (!runningIntake) {
          timer2.start();
        }
        runningIntake = true;
        intake.setSpeed(IntakeConstants.INTAKE_SPEED);
        indexer.setSpeed(IndexerConstants.INDEXER_SPEED);
        if (timer2.get() > 3) {
          flyWheelSpeed -= ShooterConstants.rampDownRate;
        }
      }
    }

    shooter.setSpeed(flyWheelSpeed);
  }

  @Override
  public boolean isFinished() {
    return runningIntake && flyWheelSpeed <= 0;
  }

  // @Override
  public void end() {
    intake.setSpeed(0);
    shooter.coast();
  }
}
