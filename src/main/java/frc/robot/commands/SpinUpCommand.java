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

  public SpinUpCommand(Shooter shooter, Limelight limelight, boolean shouldNotStop) {
    addRequirements(shooter);
    this.shooter = shooter;
    this.limelight = limelight;
    // shooter.coast();
    flyWheelSpeed = shooter.getSpeed();
    // dirty hack to add some distance
    // On saturday, note that the ball overshoots at
    // smaller distances, indicating that the constant
    // may be too much and should be merged into the
    // main function. At further distances with the change
    // it apears to work properly.
    // Without the change, the balls would almost always
    // hit the rim, with it being unclear (we didn't test)
    // how it affects it at shorter distances.

    // over all the suggestion may be to increase the x
    // term of the polynomial (or even the x^2) so that
    // the extra ~300 - 500 (the range that we need to add)
    // so it hits most of the time. At 500 and some ranges
    // where it was hitting the backboard.
    desiredSpeed = limelight.getDesiredSpeed(); // + 500;
    // this.shouldNotStop = shouldNotStop;
    // System.out.println("Running spinup command");
  }

  @Override
  public void execute() {
    if (flyWheelSpeed < desiredSpeed + ShooterConstants.rampUpRate + 5) {
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
