package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ClimbCancelExtendCommand extends CommandBase {
  private Climber climber;

  public ClimbCancelExtendCommand(Climber climber) {
    addRequirements(climber);
    this.climber = climber;
  }

  public void execute() {
    climber.stopLeftExtender();
    climber.stopRightExtender();
  }
}
