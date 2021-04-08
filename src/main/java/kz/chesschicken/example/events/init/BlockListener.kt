package kz.chesschicken.example.events.init

import net.modificationstation.stationapi.api.common.util.Null

import net.modificationstation.stationapi.api.common.mod.entrypoint.Entrypoint
import net.modificationstation.stationapi.api.common.registry.ModID
import net.minecraft.block.material.Material

import kz.chesschicken.example.wrappers.ExampleBlockWithModel
import net.modificationstation.stationapi.api.common.event.EventListener

import net.modificationstation.stationapi.api.common.event.block.BlockRegister
import net.modificationstation.stationapi.api.common.registry.Identifier
import net.modificationstation.stationapi.template.common.block.BlockBase


class BlockListener {
    companion object {
        lateinit var exampleBlock : net.minecraft.block.BlockBase
        lateinit var exampleBlock2 : net.minecraft.block.BlockBase

        @Entrypoint.ModID val MOD_ID: ModID? = Null.get()
    }

    @EventListener
    fun registerBlocks(event: BlockRegister?) {
        println(MOD_ID)
        exampleBlock = BlockBase(MOD_ID?.let { Identifier.of(it, "test") }, Material.DIRT).setTranslationKey(MOD_ID, "test")
        exampleBlock2 = ExampleBlockWithModel(MOD_ID?.let { Identifier.of(it, "test2") }, Material.DIRT).setTranslationKey(MOD_ID, "test2")
    }



}