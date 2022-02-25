package frc.robot.commands;

import static frc.robot.Constants.IntakeConstants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeCommand extends CommandBase {

  private Intake mIntake;

  public IntakeCommand(Intake intake) {
    addRequirements(intake);
    mIntake = intake;
  }

  @Override
  public void execute() {
    mIntake.setSpeed(INTAKE_SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    mIntake.stop();
  }
}
