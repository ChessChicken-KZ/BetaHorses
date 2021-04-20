package kz.chesschicken.betahorses.init

import kz.chesschicken.betahorses.entity.EntityHorse
import kz.chesschicken.betahorses.entity.RenderHorse
import kz.chesschicken.betahorses.item.BlockHay
import kz.chesschicken.betahorses.item.ItemCharSaddle
import kz.chesschicken.betahorses.item.ItemHorseAdmin
import kz.chesschicken.betahorses.item.ItemHorseInfo
import net.minecraft.item.ItemBase
import net.minecraft.item.ItemInstance
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegister
import net.modificationstation.stationapi.api.client.event.texture.TextureRegister
import net.modificationstation.stationapi.api.client.texture.TextureFactory
import net.modificationstation.stationapi.api.client.texture.TextureRegistry
import net.modificationstation.stationapi.api.common.event.EventListener
import net.modificationstation.stationapi.api.common.event.ListenerPriority
import net.modificationstation.stationapi.api.common.event.block.BlockRegister
import net.modificationstation.stationapi.api.common.event.entity.EntityRegister
import net.modificationstation.stationapi.api.common.event.item.ItemRegister
import net.modificationstation.stationapi.api.common.event.mod.PostInit
import net.modificationstation.stationapi.api.common.event.recipe.RecipeRegister
import net.modificationstation.stationapi.api.common.mod.entrypoint.Entrypoint
import net.modificationstation.stationapi.api.common.recipe.CraftingRegistry
import net.modificationstation.stationapi.api.common.recipe.SmeltingRegistry
import net.modificationstation.stationapi.api.common.registry.Identifier
import net.modificationstation.stationapi.api.common.registry.ModID
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class BetaHorsesListener {


    companion object {
        @Entrypoint.ModID var modID: ModID? = null

        var needToRemind = ""

        var itemCharSaddle: net.minecraft.item.ItemBase? = null
        var itemHorseInfo: net.minecraft.item.ItemBase? = null
        var itemHorseAdmin: net.minecraft.item.ItemBase? = null
        var blockHay: net.minecraft.block.BlockBase? = null

        var textureHay_SIDE = 0
        var textureHay_TOP = 0
    }

    @EventListener(priority = ListenerPriority.LOW)
    fun updateCheck(event: PostInit)
    {
        Runnable {
            val url = URL("https://raw.githubusercontent.com/ChessChicken-KZ/BetaHorses/master/UPDATE.txt")
            val br = BufferedReader(InputStreamReader(url.openStream()))
            needToRemind = br.readLine()

            br.close()
        }.run()
    }


    @EventListener
    fun registerEntity(event: EntityRegister) {
        event.register.accept(EntityHorse::class.java, "Horse", 123)
    }

    @EventListener
    fun registerEntityRenderer(event: EntityRendererRegister)
    {
        event.renderers[EntityHorse::class.java] = RenderHorse(0.8F)
    }


    @EventListener
    fun registerItems(event: ItemRegister)
    {
        itemCharSaddle = ItemCharSaddle(Identifier.of(modID!!, "itemcharsaddle")).setTranslationKey(modID!!, "itemCharSaddle")
        itemHorseInfo = ItemHorseInfo(Identifier.of(modID!!, "itemhorseinfo")).setTranslationKey(modID!!, "itemHorseInfo")
        itemHorseAdmin = ItemHorseAdmin(Identifier.of(modID!!, "itemhorseadmin")).setTranslationKey(modID!!, "itemHorseAdmin")
    }

    @EventListener
    fun registerBlocks(event: BlockRegister)
    {
        blockHay = BlockHay(Identifier.of(modID!!, "blockhay")).setTranslationKey(modID!!, "blockHay")
    }

    @EventListener
    fun registerTextures(event: TextureRegister)
    {
        itemCharSaddle?.setTexturePosition(TextureFactory.INSTANCE.addTexture(TextureRegistry.getRegistry("GUI_ITEMS"), "/assets/betahorses/textures/item/itemCharSaddle.png"))
        itemHorseInfo?.setTexturePosition(TextureFactory.INSTANCE.addTexture(TextureRegistry.getRegistry("GUI_ITEMS"), "/assets/betahorses/textures/item/itemHorseInfo.png"))
        itemHorseAdmin?.setTexturePosition(TextureFactory.INSTANCE.addTexture(TextureRegistry.getRegistry("GUI_ITEMS"), "/assets/betahorses/textures/item/itemHorseAdmin.png"))

        textureHay_SIDE = TextureFactory.INSTANCE.addTexture(TextureRegistry.getRegistry("TERRAIN"), "/assets/betahorses/textures/block/blockHay_side.png")
        textureHay_TOP = TextureFactory.INSTANCE.addTexture(TextureRegistry.getRegistry("TERRAIN"), "/assets/betahorses/textures/block/blockHay_top.png")

    }

    @EventListener
    fun registerRecipe(event: RecipeRegister) {
        val type = event.recipeId
        if (type == RecipeRegister.Vanilla.CRAFTING_SHAPED.type())
        {
            CraftingRegistry.addShapedRecipe(ItemInstance(blockHay),
            "XXX","XXX","XXX", Character.valueOf('X'), ItemBase.wheat)

            CraftingRegistry.addShapedRecipe(ItemInstance(itemHorseInfo),
            " X ", "XWX", " X ", Character.valueOf('X'), ItemBase.ironIngot, Character.valueOf('W'), blockHay)
        }
        if(type == RecipeRegister.Vanilla.CRAFTING_SHAPELESS.type())
        {
            CraftingRegistry.addShapelessRecipe(ItemInstance(ItemBase.wheat, 9), ItemInstance(blockHay))
        }
        if(type == RecipeRegister.Vanilla.SMELTING.type())
        {
            SmeltingRegistry.addSmeltingRecipe(ItemInstance(ItemBase.saddle), ItemInstance(itemCharSaddle))
        }
    }








}