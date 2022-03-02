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
    flyWheelSpeed = shooter.getSpeed();
    desiredSpeed = 1000; // limelight.getDesiredSpeed();
  }

  @Override
  public void execute() {
    if (flyWheelSpeed < desiredSpeed) {
      flyWheelSpeed += ShooterConstants.rampUpRate;
    }
    shooter.setSpeed(flyWheelSpeed);
    // shooter.setSpeed(6380); // Why is this not ramping
  }

  public boolean isFinished() {
    return flyWheelSpeed >= desiredSpeed;
  }

  @Override
  public void end(boolean interrupted) {
    shooter.coast();
  }
}
