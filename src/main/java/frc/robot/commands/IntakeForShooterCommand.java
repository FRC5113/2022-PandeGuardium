package frc.robot.commands;

import static frc.robot.Constants.IndexerConstants.*;
import static frc.robot.Constants.IntakeConstants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class IntakeForShooterCommand extends CommandBase {

  private Intake mIntake;
  private Indexer mIndexer;
  private Shooter mShooter;

  public IntakeForShooterCommand(Intake intake, Indexer indexer, Shooter shooter) {
    System.out.println("running new intake command");
    addRequirements(intake);
    mIntake = intake;
    mIndexer = indexer;
    mShooter = shooter;
  }

  @Override
  public void execute() {
    // System.out.println("running intake command");
    mIntake.setSpeed(INTAKE_SPEED);
    mIndexer.setSpeed(INDEXER_SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    mIntake.stop();
    mIndexer.stop();
    mShooter.coast();
  }
}
