package kz.chesschicken.betahorses.init

import kz.chesschicken.betahorses.entity.EntityHorse
import kz.chesschicken.betahorses.entity.RenderHorse
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegister
import net.modificationstation.stationapi.api.common.event.EventListener
import net.modificationstation.stationapi.api.common.event.ListenerPriority
import net.modificationstation.stationapi.api.common.event.entity.EntityRegister
import net.modificationstation.stationapi.api.common.event.mod.PostInit
import net.modificationstation.stationapi.api.common.mod.entrypoint.Entrypoint
import net.modificationstation.stationapi.api.common.registry.ModID
import java.net.URL

class EntityHorseListener {
    @Entrypoint.ModID var MOD_ID: ModID? = null


    companion object {

    }

    @EventListener
    fun registerEntity(event: EntityRegister) {
        event.register.accept(EntityHorse::class.java, "Horse", 123)
    }

    @EventListener
    fun registerEntityRenderer(event: EntityRendererRegister)
    {
        event.renderers[EntityHorse::class.java] = RenderHorse(0.8F)
    }

    @EventListener(priority = ListenerPriority.LOW)
    fun doNothing(event: PostInit)
    {
        Runnable {
            var url: URL = URL("")
        }
    }






}