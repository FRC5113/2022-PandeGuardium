package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
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

public class ShootCommand extends SequentialCommandGroup {

  public ShootCommand(Shooter shooter, Indexer indexer, Intake intake, Limelight limelight) {
    super(
        /*new OuttakeSlightlyCommand(indexer),*/
        new SpinUpCommand(shooter, limelight, true),
        new IntakeForShooterCommand(intake, indexer, shooter, false));
    // new SpinDownCommand(shooter));
  }
}
