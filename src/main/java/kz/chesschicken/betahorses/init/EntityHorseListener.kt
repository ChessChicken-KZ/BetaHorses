package kz.chesschicken.betahorses.init

import kz.chesschicken.betahorses.entity.EntityHorse
import kz.chesschicken.betahorses.entity.RenderHorse
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent
import net.modificationstation.stationapi.api.common.event.EventListener
import net.modificationstation.stationapi.api.common.event.entity.EntityRegister
import net.modificationstation.stationapi.api.common.mod.entrypoint.Entrypoint
import net.modificationstation.stationapi.api.common.registry.ModID
import net.modificationstation.stationapi.api.common.util.Null

class EntityHorseListener {
    companion object {
        @Entrypoint.ModID var MOD_ID: ModID? = Null.get()
    }

    @EventListener
    fun registerEntity(event: EntityRegister) {
        event.register.accept(EntityHorse::class.java, "Horse", 123)
        
    }

    @EventListener
    fun registerEntityRenderer(event: EntityRendererRegisterEvent)
    {
        event.renderers[EntityHorse::class.java] = RenderHorse(0.8F)
    }





}