package kz.chesschicken.betahorses.item

import kz.chesschicken.betahorses.init.BetaHorsesListener
import net.minecraft.block.material.Material
import net.modificationstation.stationapi.api.registry.Identifier
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase

class BlockHay(ident: Identifier): TemplateBlockBase(ident, Material.ORGANIC) {
    init {
        setHardness(0.8F)
        setSounds(GRASS_SOUNDS)
    }

    override fun getTextureForSide(side: Int): Int {
        return if(side == 0 || side == 1) {
            BetaHorsesListener.textureHay_TOP
        } else
            BetaHorsesListener.textureHay_SIDE
    }

}