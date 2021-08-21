package kz.chesschicken.betahorses.item

import kz.chesschicken.betahorses.entity.EntityHorse
import net.minecraft.entity.player.PlayerBase
import net.minecraft.item.ItemInstance
import net.minecraft.level.Level
import net.modificationstation.stationapi.api.registry.Identifier
import net.modificationstation.stationapi.api.template.item.TemplateItemBase

class ItemHorseAdmin(ident: Identifier) : TemplateItemBase(ident) {
    override fun use(item: ItemInstance, level: Level, player: PlayerBase): ItemInstance {
        val horse = EntityHorse(level)
        horse.setPosition(player.x, player.y + 0.2, player.z)
        level.spawnEntity(horse)
        System.out.println("Yay! Spawned horse ("+horse.entityId+") at: "+player.x+" "+(player.y+0.2)+" "+player.z)
        return item
    }
}