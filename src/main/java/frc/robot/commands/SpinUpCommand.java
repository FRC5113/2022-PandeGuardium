package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopUp;
import frc.robot.subsystems.Shooter;

public class SpinUpCommand extends CommandBase {

    private double speed;

    private final Shooter shooter;
    private final HopUp hopper;

    public SpinUpCommand(Shooter shooter, HopUp hopper, double speed) {
        addRequirements(shooter, hopper);
        this.shooter = shooter;
        this.hopper = hopper;
        this.speed = speed;
    }

    @Override
    public void execute() {
        shooter.setSpeed(speed);
        hopper.setSpeed();
    }

    @Override
    public void end(boolean interrupted) {
        shooter.coast();
        hopper.stopHopping();
    }

}