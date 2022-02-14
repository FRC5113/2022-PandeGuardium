package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Turret;
import static frc.robot.Constants.TurretConstants.TurretP;
import static frc.robot.Constants.TurretConstants.TurretI;
import static frc.robot.Constants.TurretConstants.TurretD;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import com.kauailabs.navx.frc.AHRS;

public class TurretCommand extends PIDCommand{
    public TurretCommand (Turret turret, DriveTrain drivetrain) { 
        super (new PIDController(TurretP, TurretI, TurretD), 
        turret::getTurretAngle, 
        360-(Math.atan((drivetrain.getPose().getY())/(drivetrain.getPose().getX()))), 
        output ->  turret.setSpeed(output), turret);
    }
}
