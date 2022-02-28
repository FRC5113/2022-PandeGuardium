package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Limelight;

public class ResetOdemetryCommand extends CommandBase {

  public ResetOdemetryCommand(DriveTrain driveTrain, Limelight limelight) {

    limelight.update();
    Pose2d pos =
        new Pose2d(new Translation2d(limelight.getDistanceToTarget(), 0.0), new Rotation2d(0.0));
    driveTrain.resetOdometry(pos);
  }
}
