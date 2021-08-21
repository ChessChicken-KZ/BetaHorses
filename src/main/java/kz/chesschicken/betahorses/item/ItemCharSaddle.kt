package kz.chesschicken.betahorses.item

import net.minecraft.entity.player.PlayerBase
import net.minecraft.item.ItemInstance
import net.minecraft.level.Level
import net.modificationstation.stationapi.api.registry.Identifier
import net.modificationstation.stationapi.api.template.item.TemplateItemBase

class ItemCharSaddle(identifier: Identifier) : TemplateItemBase(identifier) {
    init {
        setMaxStackSize(1)
    }

    override fun use(item: ItemInstance?, level: Level?, player: PlayerBase?): ItemInstance {
        return super.use(item, level, player)
    }
}