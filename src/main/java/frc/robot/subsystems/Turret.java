package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI;

import static frc.robot.Constants.TurretConstants.*;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;


import com.kauailabs.navx.frc.AHRS;


public class Turret extends SubsystemBase
{

    private final CANSparkMax turretMotor;
    private final AHRS turretGyro;
    private final DifferentialDriveOdometry odometry;

    public Turret() 
    {
        turretMotor = new CANSparkMax(turret_ID, MotorType.kBrushless);
        configureMotor(turretMotor);

        turretGyro = new AHRS(SPI.Port.kMXP);
        turretGyro.enableLogging(true);
        odometry = new DifferentialDriveOdometry(turretGyro.getRotation2d());

    
    }
    private void configureMotor(CANSparkMax motor) 
    {
        motor.restoreFactoryDefaults();
        motor.setIdleMode(IdleMode.kBrake);
        motor.setSmartCurrentLimit(TURRET_MAX_CURRENT);
        motor.enableVoltageCompensation(TURRET_MAX_VOLTAGE);
        motor.setInverted(true);
        motor.burnFlash();
    }    

    public void setSpeed(double speed)
    {
        turretMotor.set(speed);
    }

    public void stop() 
    {
        turretMotor.set(0);
    }
    public double getTurretAngle() {
        return turretGyro.getRotation2d().getDegrees();
    }

    // public double getOdometry(){
    //     return odometry;
    // }
}