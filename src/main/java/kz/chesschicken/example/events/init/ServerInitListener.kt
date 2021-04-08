package kz.chesschicken.example.events.init

import net.modificationstation.stationapi.api.common.event.EventListener
import net.modificationstation.stationapi.api.common.event.mod.Init
import net.modificationstation.stationapi.api.common.util.Null

import net.modificationstation.stationapi.api.common.mod.entrypoint.Entrypoint
import net.modificationstation.stationapi.api.common.registry.ModID
import org.apache.logging.log4j.Logger


class ServerInitListener {

    @Entrypoint.ModID val MOD_ID: ModID = Null.get()

    @Entrypoint.Logger val LOGGER: Logger = Null.get()

    @EventListener
    private fun serverInit(event: Init) {
        LOGGER.error(MOD_ID.toString())
    }

}