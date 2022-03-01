package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class SpinUpCommand extends CommandBase {

  private double speed;

  private final Shooter shooter;

  public SpinUpCommand(Shooter shooter, double speed) {
    addRequirements(shooter);
    this.shooter = shooter;
    this.speed = speed;
  }

  @Override
  public void execute() {
    shooter.setSpeed(speed);
  }

  @Override
  public void end(boolean interrupted) {
    shooter.coast();
  }
}
