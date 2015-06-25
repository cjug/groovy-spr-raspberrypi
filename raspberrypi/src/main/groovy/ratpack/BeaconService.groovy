package ratpack

import com.pi4j.io.gpio.*
import javax.inject.Inject

class BeaconService {

    static def gpio
    static def blueLED
    static def greenLED
    static def redLED

    @Inject
    BeaconService() {
        if (!gpio) gpio = GpioFactory.getInstance()
        if (!blueLED) {
            blueLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "Blue LED", PinState.LOW)
            blueLED.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF)
        }
        if (!greenLED) {
            greenLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27, "Green LED", PinState.LOW)
            greenLED.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF)
        }
        if (!redLED) {
            redLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26, "Red LED", PinState.LOW)
            redLED.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF)
        }
    }

    void turnOnBlue() {
        greenLED.low()
        redLED.low()
        blueLED.toggle()
    }

    void turnOnGreen() {
        blueLED.low()
        redLED.low()
        greenLED.toggle()
    }

    void turnOnRed() {
        blueLED.low()
        greenLED.low()
        redLED.toggle()
    }

    void turnOffAll() {
        blueLED.low()
        greenLED.low()
        redLED.low()
    }

    void blinkRed() {
        5.times {
            turnOnRed()
            sleep(100)
            turnOffAll()
            sleep(100)
        }
    }

}