package frc.robot.commands;

import static frc.robot.Constants.IndexerConstants.*;
import static frc.robot.Constants.IntakeConstants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

public class IntakeCommand extends CommandBase {

  private Intake mIntake;
  private Indexer mIndexer;

  public IntakeCommand(Intake intake, Indexer indexer) {
    System.out.println("running new intake command");
    addRequirements(intake);
    mIntake = intake;
    mIndexer = indexer;
  }

  @Override
  public void execute() {
    System.out.println("running intake command");
    mIntake.setSpeed(INTAKE_SPEED);
    // mIndexer.setSpeed(INDEXER_SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    mIntake.stop();
    mIndexer.stop();
  }
}
