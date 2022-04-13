package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.enums.ShootTarget;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class SpinUpCommand extends CommandBase {
  private final Shooter shooter;
  private final Limelight limelight;
  private double flyWheelSpeed;
  private double desiredSpeed;
  private ShootTarget target;

  public SpinUpCommand(Shooter shooter, Limelight limelight, ShootTarget target) {
    addRequirements(shooter);
    this.shooter = shooter;
    this.limelight = limelight;
    // shooter.coast();
    // flyWheelSpeed = shooter.getSpeed();
    flyWheelSpeed = 0;
    this.target = target;

    if (target == ShootTarget.LOW_GOAL) {
      desiredSpeed = 20000; // limelight.getDesiredSpeed();
    } else {
      // Must be low goal

      desiredSpeed = limelight.getDesiredSpeed();
      if (limelight.getDistaceToTarget() > 192) {
        desiredSpeed = 25000;
      }
    }
    // this.shouldNotStop = shouldNotStop;
    // System.out.println("Running spinup command");
  }

  @Override
  public void execute() {
    System.out.println("executing shoot command");
    if (flyWheelSpeed < desiredSpeed + ShooterConstants.rampUpRate + 5) {
      flyWheelSpeed += ShooterConstants.rampUpRate;
    }
    shooter.setSpeed(flyWheelSpeed);
    // if (target == ShootTarget.HIGH_GOAL) shooter.setSpeed(5000);
    // else shooter.setSpeed(flyWheelSpeed); // shooter.setSpeed(2000);
    // shooter.setSpeed(6380); // Why is this not ramping
  }

  @Override
  public boolean isFinished() {
    if (flyWheelSpeed >= desiredSpeed) {
      return true;
    }
    return false; // flyWheelSpeed >= desiredSpeed;
  }

  @Override
  public void end(boolean interrupted) {
    if (flyWheelSpeed >= desiredSpeed) {
      shooter.setSpeed(desiredSpeed);
    } else {
      shooter.coast();
    }
    flyWheelSpeed = 0;
    // shooter.coast();
  }
}
