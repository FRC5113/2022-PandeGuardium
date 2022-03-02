package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;

public class SpinDownCommand extends CommandBase {
  private final Shooter shooter;
  private double flyWheelSpeed;
  private double desiredSpeed;

  public SpinDownCommand(Shooter shooter) {
    addRequirements(shooter);
    this.shooter = shooter;
    flyWheelSpeed = shooter.getSpeed();
    desiredSpeed = 0;
  }

  @Override
  public void execute() {
    if (flyWheelSpeed > desiredSpeed) {
      flyWheelSpeed -= ShooterConstants.rampDownRate;
    }
    if (flyWheelSpeed < 0) {
      flyWheelSpeed = 0;
    }
    shooter.setSpeed(flyWheelSpeed);
  }

  public boolean isFinished() {
    return flyWheelSpeed == 0;
  }

  @Override
  public void end(boolean interrupted) {
    shooter.coast();
  }
}
