package kz.chesschicken.betahorses.item

import kz.chesschicken.betahorses.entity.EntityHorse
import net.fabricmc.api.EnvType
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.entity.player.PlayerBase
import net.minecraft.item.ItemInstance
import net.minecraft.level.Level
import net.minecraft.util.hit.HitType
import net.modificationstation.stationapi.api.registry.Identifier
import net.modificationstation.stationapi.api.template.item.TemplateItemBase

class ItemHorseInfo(ident: Identifier) : TemplateItemBase(ident) {

    override fun use(item: ItemInstance, level: Level, player: PlayerBase): ItemInstance {
        if(FabricLoader.getInstance().environmentType == EnvType.CLIENT)
        {
            val oom = FabricLoader.getInstance().gameInstance as net.minecraft.client.Minecraft
            if(oom.hitResult != null)
            {
                if(oom.hitResult.type == HitType.ENTITY && oom.hitResult.field_1989 is EntityHorse)
                {
                    val horse = oom.hitResult.field_1989 as EntityHorse
                    oom.overlay.addChatMessage("Horse speed: "+horse.speedBase)
                    oom.overlay.addChatMessage("Horse owner: "+horse.owner)
                    oom.overlay.addChatMessage("Horse lvl: "+horse.lvl)
                }
            }
        }
        return item
    }
}