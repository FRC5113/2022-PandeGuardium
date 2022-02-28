package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class ShootCommand extends ParallelCommandGroup {

  public ShootCommand(Shooter shooter, Indexer indexer, Limelight limelight) {
    super(
        new SpinUpCommand(shooter, limelight),
        new SequentialCommandGroup(new WaitCommand(5), new IndexerCommand(indexer)));
  }
}
