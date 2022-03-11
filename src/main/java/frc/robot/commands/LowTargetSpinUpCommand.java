package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;

public class LowTargetSpinUpCommand extends CommandBase {
  private final Shooter shooter;
  private double flyWheelSpeed;
  private double desiredSpeed;

  public LowTargetSpinUpCommand(Shooter shooter) {
    addRequirements(shooter);
    this.shooter = shooter;
    flyWheelSpeed = 0;
    desiredSpeed = ShooterConstants.lowSpinSpeed;
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
    return flyWheelSpeed >= desiredSpeed;
  }

  @Override
  public void end(boolean interrupted) {
    flyWheelSpeed = 0;
  }
}
