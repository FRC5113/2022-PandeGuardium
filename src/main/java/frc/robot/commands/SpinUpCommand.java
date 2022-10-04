package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class SpinUpCommand extends CommandBase {
  private final Shooter shooter;
  private final Limelight limelight;
  private double flyWheelSpeed;
  private double desiredSpeed;

  public SpinUpCommand(Shooter shooter, Limelight limelight) {
    addRequirements(shooter);
    this.shooter = shooter;
    this.limelight = limelight;
    flyWheelSpeed = 0;

    desiredSpeed = 25000; 
  }

  @Override
  public void execute() {

    if (flyWheelSpeed < desiredSpeed) {
      flyWheelSpeed += ShooterConstants.rampUpRate;
    }
    shooter.setSpeed(flyWheelSpeed);
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
