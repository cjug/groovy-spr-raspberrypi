@Grab('io.ratpack:ratpack-groovy:0.9.17')
import static ratpack.groovy.Groovy.ratpack
import ratpack.*
import ratpack.server.Service
import ratpack.server.StartEvent

ratpack {
    bindings {

        module BeaconModule

        bindInstance Service, new Service() {
            @Override
            void onStart(StartEvent event) throws Exception {
                event.registry.get(BeaconService).blinkRed()
            }
        }
    }
    handlers { BeaconService beaconService ->
        get("blue") {
            beaconService.turnOnBlue()
            render "Blue led toggled!"
        }
        get("green") {
            beaconService.turnOnGreen()
            render "Green led toggled!"
        }
        get("red") {
            beaconService.turnOnRed()
            render "Red led toggled!"
        }
        get("2f234454-cf6d-4a0f-adf2-f4911ba9ffa6") {
            beaconService.turnOnBlue()
            render "Beacon one found!"
        }
        get("68b97ecd-862f-485c-9c69-50844d7d33d5") {
            beaconService.turnOnGreen()
            render "Beacon two found!"
        }
        get("blinkRed") {
            beaconService.blinkRed()
            render "Blinking red!"
        }
    }
}