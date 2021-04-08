package kz.chesschicken.example.events.init

import net.modificationstation.stationapi.api.common.event.EventListener
import net.modificationstation.stationapi.api.common.util.Null

import net.modificationstation.stationapi.api.common.mod.entrypoint.Entrypoint
import net.modificationstation.stationapi.api.common.registry.ModID
import net.modificationstation.stationapi.api.common.event.item.ItemRegister
import net.modificationstation.stationapi.api.common.registry.Identifier
import net.modificationstation.stationapi.template.common.item.ItemBase


class ItemListener {
    companion object {
        lateinit var coolItem : net.minecraft.item.ItemBase

        @Entrypoint.ModID val MOD_ID: ModID? = Null.get()
    }

    @EventListener
    fun registerItems(event: ItemRegister?) {
        coolItem = ItemBase(MOD_ID?.let { Identifier.of(it, "coolitem") }).setTranslationKey(MOD_ID, "coolitem")
    }

}