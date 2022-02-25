package frc.robot.subsystems;

import static frc.robot.Constants.LEDConstants.LED_PORT;
import static frc.robot.Constants.LEDConstants.LED_LENGTH;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LED extends SubsystemBase {

    private AddressableLED m_led;
    private AddressableLEDBuffer m_ledBuffer;

    private int m_rainbowFirstPixelHue;

    public LED() {
        m_led = new AddressableLED(LED_PORT);

        m_ledBuffer = new AddressableLEDBuffer(LED_LENGTH);
        m_led.setLength(m_ledBuffer.getLength());

        m_led.setData(m_ledBuffer);
        m_led.start();
    }

    public void rainbow() {
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
            final var hue = (m_rainbowFirstPixelHue + (i * 180 / m_ledBuffer.getLength())) % 180;
            // Set the value
            m_ledBuffer.setHSV(i, hue, 255, 128);
        }
        m_rainbowFirstPixelHue += 3;
        m_rainbowFirstPixelHue %= 180;

        // updates the value of the physical LED
        m_led.setData(m_ledBuffer);
    }
}
