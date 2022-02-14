package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopUp;
import frc.robot.subsystems.Shooter;

public class HopperMove extends CommandBase {


    private final HopUp hopper;

    public HopperMove( HopUp hopper) {
        addRequirements(hopper);
        this.hopper = hopper;
    }

    @Override
    public void execute() {
        hopper.setSpeed();
    }

    @Override
    public void end(boolean interrupted) {
        hopper.stopHopping();
    }

}