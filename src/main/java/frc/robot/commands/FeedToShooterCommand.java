package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopUp;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class FeedToShooterCommand extends CommandBase{

    private double speed;

    private final HopUp hopper;
    private final Indexer indexer;

    public FeedToShooterCommand(HopUp hopper, Indexer indexer, double speed) {
        addRequirements(hopper);
        this.hopper = hopper;
        this.indexer = indexer;
        this.speed = speed;
    }
   
    @Override
    public void execute() {
        hopper.setSpeed();
        indexer.setSpeed(false);
    }

    @Override
    public void end(boolean interuppted) {
        hopper.stopHopping();
        indexer.stopIndexing();
    }

}
