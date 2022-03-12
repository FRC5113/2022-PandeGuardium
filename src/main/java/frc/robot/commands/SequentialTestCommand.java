package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.enums.ShouldStop;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class SequentialTestCommand extends SequentialCommandGroup {
  public SequentialTestCommand(
      Shooter shooter, Indexer indexer, Intake intake, DriveTrain driveTrain) {
    super(
        new DriveAutonTimerCommand(driveTrain),
        new IntakeForShooterCommand(intake, indexer, shooter, ShouldStop.Yes));
  }
}
