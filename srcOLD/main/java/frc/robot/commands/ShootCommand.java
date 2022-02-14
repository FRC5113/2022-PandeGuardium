package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.HopUp;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class ShootCommand extends ParallelCommandGroup{

    public ShootCommand(Shooter shooter, HopUp hopper, Indexer indexer, double speed) {
        super(new SpinUpCommand(shooter,hopper, speed), new SequentialCommandGroup(new WaitCommand(5), new FeedToShooterCommand(hopper, indexer, speed)));
    }
    
}
