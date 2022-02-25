package frc.robot.commands;

import static frc.robot.Constants.LimelightConstants.*;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Limelight;

public class CenterTargetRobot extends PIDCommand {

  public CenterTargetRobot(DriveTrain driveTrain, Limelight limelight) {
    super(
        new PIDController(kP, kI, kD),
        limelight::getTx,
        0.0,
        output -> driveTrain.tankDrive(-output, output),
        driveTrain);
  }

  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
