package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveAutonTimerCommand extends CommandBase {

  private DriveTrain driveTrain;
  private Timer timer;

  public DriveAutonTimerCommand(DriveTrain driveTrain) {
    addRequirements(driveTrain);

    

    this.driveTrain = driveTrain;
  }

  @Override
  public void initialize() {
    timer = new Timer();
    timer.start();
  }

  @Override
  public void execute() {
    driveTrain.tankDrive(0.2, -0.2);
    // driveTrain.curvatureDrive(scale(leftValue.getAsDouble()),
    // scale(rightValue.getAsDouble()));
  }

  @Override
  public boolean isFinished() {
    return timer.get() >= 1; // adjust to go forward or backwards
  }

  @Override
  public void end(boolean interrupted) {
    driveTrain.tankDrive(0, 0);
  }
}
