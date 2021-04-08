package kz.chesschicken.example.wrappers

import kz.chesschicken.example.events.init.ModelListener
import net.minecraft.block.material.Material
import net.minecraft.level.Level
import net.modificationstation.stationapi.api.client.model.BlockModelProvider
import net.modificationstation.stationapi.api.client.model.CustomModel
import net.modificationstation.stationapi.api.common.registry.Identifier
import net.modificationstation.stationapi.template.common.block.BlockBase

class ExampleBlockWithModel(id: Identifier?, m: Material) : BlockBase(id, m) , BlockModelProvider {

    override fun isFullOpaque(): Boolean = false

    override fun getCustomWorldModel(p0: Level?, p1: Int, p2: Int, p3: Int, p4: Int): CustomModel = ModelListener.CUSTOM_MODEL

    override fun getCustomInventoryModel(p0: Int): CustomModel = ModelListener.CUSTOM_MODEL


}