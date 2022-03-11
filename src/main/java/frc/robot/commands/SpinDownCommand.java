package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;

public class SpinDownCommand extends CommandBase {
  private final Shooter shooter;
  private double flyWheelSpeed;
  // private double desiredSpeed;

  public SpinDownCommand(Shooter shooter) {
    System.out.println("Spinning down");
    addRequirements(shooter);
    this.shooter = shooter;
    flyWheelSpeed = shooter.getSpeed();
    // shooter.coast();
  }

  @Override
  public void execute() {
    if (flyWheelSpeed <= 0) {
      flyWheelSpeed += ShooterConstants.rampDownRate;
    }
    if (flyWheelSpeed >= 0) {
      flyWheelSpeed -= ShooterConstants.rampDownRate;
    }
    if (flyWheelSpeed <= 0) {
      shooter.coast();
      flyWheelSpeed = 0;
    }
    shooter.setSpeed(flyWheelSpeed);

    // shooter.coast();
  }

  public boolean isFinished() {
    // System.out.println("!!!!!" + (flyWheelSpeed == 0)); // =kjyt5432  <- xbox controller
    return flyWheelSpeed <= flyWheelSpeed;
  }

  @Override
  public void end(boolean interrupted) {
    shooter.coast();
  }

  // @Override
  public void end() {
    shooter.coast();
  }
}
