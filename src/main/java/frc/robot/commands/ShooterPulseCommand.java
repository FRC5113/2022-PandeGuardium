package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

// Pulses the flywheel, adding momentum as needed (see equation)
public class ShooterPulseCommand extends CommandBase {

  private double speed;
  private Shooter shooter;

  public ShooterPulseCommand(Shooter shooter, double speed) {
    addRequirements(shooter);
    this.shooter = shooter;
    this.speed = speed;
  }

  @Override
  public void execute() {
    if ((shooter.getPulseTime() / 500) % 20
        <= 6) { // 0-3, 10-13, desmos: \operatorname{mod}\left((x/.5),20\right)\ \le6
      if (shooter.getSpeed() > speed + 500) shooter.coast();
      else shooter.setSpeed(speed);
    } else shooter.coast();
  }
}
