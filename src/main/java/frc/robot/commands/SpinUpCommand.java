package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.LimelightConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class SpinUpCommand extends CommandBase {

  private final Limelight limelight;
  private final Shooter shooter;
  private double flyWheelSpeed;

  public SpinUpCommand(Shooter shooter, Limelight limelight) {
    addRequirements(shooter);
    this.shooter = shooter;
    this.limelight = limelight;
    // flyWheelSpeed = getTargetFlyWheelSpeed(limelight.getDistanceToTarget());
    flyWheelSpeed = 0;
  }

  public double getTargetFlyWheelSpeed(double metersToTarget) {
    return ShooterConstants.velocityConstant
        * Math.sqrt(
            ((-4.9) * (Math.pow(metersToTarget, 2)))
                / Math.pow(Math.cos(Math.toRadians(ShooterConstants.shootAngle)), 2)
                * (LimelightConstants.targetHeight
                    - (ShooterConstants.ballStartHeight
                        + (metersToTarget
                            * Math.tan(
                                Math.toRadians(
                                    ShooterConstants.shootAngle)))))); // dont question; it works :)
  }

  @Override
  public void execute() {
    flyWheelSpeed += 0.1;
    shooter.setSpeed(flyWheelSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    shooter.coast();
  }
}
