package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopUp;
import frc.robot.subsystems.Indexer;


public class IndexCommand extends CommandBase {

    private final Indexer indexer;
    private boolean reverse;

    public IndexCommand(Indexer indexer) {
        addRequirements(indexer);
        this.indexer = indexer;
        this.reverse = false;

    }

    public IndexCommand(Indexer indexer, boolean reverse) {
        addRequirements(indexer);
        this.indexer = indexer;
        this.reverse = reverse;

    }

    @Override
    public void execute() {
        System.out.println("Running");
        indexer.setSpeed(reverse);
    }

    @Override
    public void end(boolean interuppted) {
        indexer.stopIndexing();
    }

}