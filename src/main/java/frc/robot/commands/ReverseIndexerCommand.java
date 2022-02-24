package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

import static frc.robot.Constants.IndexerConstants.*;

public class ReverseIndexerCommand extends CommandBase {

    private Indexer indexer;

    public ReverseIndexerCommand(Indexer indexer) {
        addRequirements(indexer);
        this.indexer = indexer;
    }

    @Override
    public void execute() {
        this.indexer.setSpeed(-INDEXER_SPEED);
    }

    @Override
    public void end(boolean interrupted) {
        this.indexer.stop();
    }

}