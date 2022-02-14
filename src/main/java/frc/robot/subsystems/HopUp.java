package frc.robot.subsystems;

import static frc.robot.Constants.HopUpConstants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HopUp extends SubsystemBase {

    private CANSparkMax hopper;

    public HopUp() {

        hopper = new CANSparkMax(HOPUP_ID, MotorType.kBrushless);
        configureMotor(hopper);

    }

    private void configureMotor(CANSparkMax motor) {
        motor.restoreFactoryDefaults();
        motor.setIdleMode(IdleMode.kCoast);
        motor.setSmartCurrentLimit(HOPUP_MAX_CURRENT);
        motor.enableVoltageCompensation(HOPUP_MAX_VOLTAGE);
        motor.burnFlash();
    }

    public void setSpeed() {
        hopper.set(0.8);
        SmartDashboard.putNumber("hopperCurrent", hopper.getOutputCurrent());
    }

    public void stopHopping() {
        hopper.set(0);
    }

}