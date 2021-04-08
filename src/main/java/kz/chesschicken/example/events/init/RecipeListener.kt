package kz.chesschicken.example.events.init

import net.minecraft.block.BlockBase
import net.minecraft.item.ItemBase
import net.minecraft.item.ItemInstance
import net.modificationstation.stationapi.api.common.event.EventListener

import net.modificationstation.stationapi.api.common.event.recipe.RecipeRegister
import net.modificationstation.stationapi.api.common.recipe.CraftingRegistry
import net.modificationstation.stationapi.api.common.recipe.SmeltingRegistry
import net.modificationstation.stationapi.api.common.registry.Identifier


class RecipeListener {

    @EventListener
    fun registerRecipes(event: RecipeRegister) {
        val type: Identifier = event.recipeId
        if (type === RecipeRegister.Vanilla.CRAFTING_SHAPED.type()) {
            CraftingRegistry.INSTANCE.addShapedRecipe(
                ItemInstance(ItemListener.coolItem, 1),
                "XXX",
                "X X",
                "X X",
                'X',
                BlockBase.DIRT
            )
        }
        if (type === RecipeRegister.Vanilla.SMELTING.type()) {
            SmeltingRegistry.INSTANCE.addSmeltingRecipe(ItemInstance(ItemBase.apple, 1), ItemInstance(BlockBase.WOOL))
        }
        if (type === RecipeRegister.Vanilla.CRAFTING_SHAPELESS.type()) {
            CraftingRegistry.INSTANCE.addShapelessRecipe(
                ItemInstance(BlockListener.exampleBlock, 1),
                ItemInstance(BlockBase.DIRT)
            )
            CraftingRegistry.INSTANCE.addShapelessRecipe(
                ItemInstance(BlockListener.exampleBlock2, 1),
                ItemInstance(BlockBase.DIRT),
                ItemInstance(BlockBase.DIRT)
            )
        }
    }

}