package frc.robot.subsystems;

import static frc.robot.Constants.IndexerConstants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase {

  private CANSparkMax intakeMotor;

  public Indexer() {
    intakeMotor = new CANSparkMax(INDEXER_ID, MotorType.kBrushless);
    intakeMotor.restoreFactoryDefaults();
    intakeMotor.setIdleMode(IdleMode.kBrake);
    intakeMotor.enableVoltageCompensation(INDEXER_MAX_VOLTAGE);
    intakeMotor.setSmartCurrentLimit(INDEXER_MAX_CURRENT);
    intakeMotor.burnFlash();
  }

  public void setSpeed(double speed) {
    intakeMotor.set(speed);
  }

  public void stop() {
    this.setSpeed(0);
  }
}
