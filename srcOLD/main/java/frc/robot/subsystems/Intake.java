package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.IntakeConstants.*;

public class Intake extends SubsystemBase {

    private CANSparkMax intakeMotor;

    public Intake() {
        intakeMotor = new CANSparkMax(INTAKE_ID, MotorType.kBrushless);
        intakeMotor.restoreFactoryDefaults();
        intakeMotor.setIdleMode(IdleMode.kBrake);
        intakeMotor.enableVoltageCompensation(INTAKE_MAX_VOLTAGE);
        intakeMotor.setSmartCurrentLimit(INTAKE_CURRENT_LIMIT);
        intakeMotor.burnFlash();
    }

    public void setSpeed(double speed) {
        intakeMotor.set(speed);
    }

    public void stop() {
        this.setSpeed(0);
    }

}