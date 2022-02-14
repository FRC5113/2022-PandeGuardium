package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.DigitalOutput; might be pointless (see below)
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.IndexerConstants.*;

public class Indexer extends SubsystemBase {

    private int BALL_COUNT = 3;

    private CANSparkMax extMotor;
    private CANSparkMax midMotor;

    private boolean indexStarted = false;
    private boolean indexHappening = false;
    private boolean indexDone = false;
    private int state;

    // Digital Sensor port for Beambreak
    // beambreakStart is the sensor that is closest to the turret
    private DigitalInput beamBreakStartInput = new DigitalInput(BEAM_BREAK_INPUT_ID);
    //private DigitalOutput beamBreakStartOutput = new DigitalOutput(BEAM_BREAK_OUTPUT_ID); 
    //might be pointless (see above)

    public Indexer() {
        extMotor = new CANSparkMax(EXT_INDEXER_ID, MotorType.kBrushless);
        midMotor = new CANSparkMax(MID_INDEXER_ID, MotorType.kBrushless);

        configureMotor(extMotor);
        configureMotor(midMotor);

    }

    private void configureMotor(CANSparkMax motor) {
        motor.restoreFactoryDefaults();
        motor.setIdleMode(IdleMode.kBrake);
        motor.setSmartCurrentLimit(INDEXER_MAX_CURRENT);
        motor.enableVoltageCompensation(INDEXER_MAX_VOLTAGE);
        motor.setInverted(true);
        motor.burnFlash();
    }

    public void setSpeed(boolean reverse) {
        if (reverse) {
            extMotor.set(-EXT_INDEXER_SPEED);
            midMotor.set(-MID_INDEXER_SPEED);
        } else {
            extMotor.set(EXT_INDEXER_SPEED);
            midMotor.set(MID_INDEXER_SPEED);
        }
    }

    public void stopIndexing() {
        extMotor.set(0);
        midMotor.set(0);
    }

    public int getBallCount() {
        return BALL_COUNT;
    }

    public void incrementBallCount() {
        BALL_COUNT += 1;
    }

    public void decrementBallCount() {
        BALL_COUNT -= 1;
    }

    // ALL STATE MACHINE STUFF

    public boolean getStartInput() {
        return beamBreakStartInput.get();
    }

    public boolean getIndexingStarted() {
        return indexStarted;
    }

    public void setIndexingStarted() {
        indexStarted = !indexStarted;
    }

    public boolean getIndexHappening() {
        return indexStarted;
    }

    public void setIndexHappening() {
        indexHappening = !indexHappening;
    }

    public boolean getIndexDone() {
        return indexDone;
    }

    public void setIndexDone() {
        indexDone = false;
        indexHappening = false;
        indexStarted = false;
    }

    public void indexStart() {
        indexStarted = true;
        indexHappening = false;
        indexDone = false;
    }

    public void indexHappen() {
        indexStarted = false;
        indexHappening = true;
        indexDone = false;
    }

    public void indexDone() {
        indexStarted = false;
        indexHappening = false;
        indexDone = true;
    }

    public int getState() {

        if (!indexStarted && !indexHappening && !indexDone && getStartInput())
            state = 0;
        else if (indexStarted && !indexHappening && !indexDone && !getStartInput())
            state = 1;
        else if (!indexStarted && indexHappening && !indexDone && getStartInput())
            state = 2;
        else
            state = 3;

        return state;
    }

}