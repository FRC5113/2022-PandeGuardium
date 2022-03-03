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
  private boolean shouldNotStop = false;

  public SpinUpCommand(Shooter shooter, Limelight limelight, boolean shouldNotStop) {
    addRequirements(shooter);
    this.shooter = shooter;
    this.limelight = limelight;
    flyWheelSpeed = shooter.getSpeed();
    double distance = 12.66;
    desiredSpeed = limelight.getDesiredSpeed();
    this.shouldNotStop = shouldNotStop;
    // System.out.println("Running spinup command");
  }

  @Override
  public void execute() {
    if (flyWheelSpeed < desiredSpeed) {
      flyWheelSpeed += ShooterConstants.rampUpRate;
    }
    shooter.setSpeed(flyWheelSpeed);
    // shooter.setSpeed(6380); // Why is this not ramping
  }

  @Override
  public boolean isFinished() {
    if (flyWheelSpeed >= desiredSpeed) {
      System.out.println("DONESPINUP");
    }
    return flyWheelSpeed >= desiredSpeed;
  }

  @Override
  public void end(boolean interrupted) {
    flyWheelSpeed = 0;
  }
}
