package kz.chesschicken.betahorses.item

import net.minecraft.entity.player.PlayerBase
import net.minecraft.item.ItemInstance
import net.minecraft.level.Level
import net.modificationstation.stationapi.api.common.registry.Identifier
import net.modificationstation.stationapi.template.common.item.ItemBase

class ItemCharSaddle(identifier: Identifier) : ItemBase(identifier) {
    init {
        setMaxStackSize(1)
    }

    override fun use(item: ItemInstance?, level: Level?, player: PlayerBase?): ItemInstance {
        return super.use(item, level, player)
    }
}