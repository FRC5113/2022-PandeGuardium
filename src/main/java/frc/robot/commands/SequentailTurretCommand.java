package frc.robot.commands;

import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Turret;
public class SequentailTurretCommand extends SequentialCommandGroup {

    public SequentailTurretCommand(DriveTrain drivetrain, Limelight limelight, Turret turret) {
        super(new CenterTargetRobot(drivetrain, limelight), new ResetOdemetryCommand(drivetrain, limelight),new TurretCommand(turret, drivetrain));
    }
    
}
