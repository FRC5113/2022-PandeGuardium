package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

/*
public class ShootCommand extends ParallelCommandGroup {

  public ShootCommand(Shooter shooter, Indexer indexer, Limelight limelight) {
    super(
        new SpinUpCommand(shooter, limelight),
        new SequentialCommandGroup(new WaitCommand(5), new IndexerCommand(indexer)));
  }
}

*/

public class AutonCommand extends SequentialCommandGroup {

  public AutonCommand(
      Shooter shooter, Indexer indexer, Limelight limelight, DriveTrain drivetrain) {
    super(
        new DriveAutonCommand(drivetrain),
        new OuttakeSlightlyCommand(indexer),
        new SpinUpCommand(shooter, limelight, false),
        new IndexerCommand(indexer, true));
    // new SpinDownCommand(shooter));
  }
}
