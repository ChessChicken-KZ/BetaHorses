package kz.chesschicken.betahorses.init

import kz.chesschicken.betahorses.entity.EntityHorse
import kz.chesschicken.betahorses.entity.RenderHorse
import kz.chesschicken.betahorses.item.BlockHay
import kz.chesschicken.betahorses.item.ItemCharSaddle
import kz.chesschicken.betahorses.item.ItemHorseAdmin
import kz.chesschicken.betahorses.item.ItemHorseInfo
import net.mine_diver.unsafeevents.listener.EventListener
import net.mine_diver.unsafeevents.listener.ListenerPriority
import net.minecraft.item.ItemBase
import net.minecraft.item.ItemInstance
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent
import net.modificationstation.stationapi.api.client.texture.atlas.ExpandableAtlas
import net.modificationstation.stationapi.api.event.entity.EntityRegister
import net.modificationstation.stationapi.api.event.mod.PostInitEvent
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint
import net.modificationstation.stationapi.api.recipe.CraftingRegistry
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry
import net.modificationstation.stationapi.api.registry.Identifier
import net.modificationstation.stationapi.api.registry.ModID
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase
import net.modificationstation.stationapi.api.template.item.TemplateItemBase
import net.modificationstation.stationapi.api.util.Null

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class BetaHorsesListener {


    companion object {
        @Entrypoint.ModID var modID: ModID? = Null.get()

        var needToRemind = ""

        lateinit var itemCharSaddle: TemplateItemBase
        lateinit var itemHorseInfo: TemplateItemBase
        lateinit var itemHorseAdmin: TemplateItemBase
        lateinit var blockHay: TemplateBlockBase

        var textureHay_SIDE = 0
        var textureHay_TOP = 0
    }

    @EventListener(priority = ListenerPriority.LOW)
    fun updateCheck(event: PostInitEvent)
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
    fun registerEntityRenderer(event: EntityRendererRegisterEvent)
    {
        event.renderers[EntityHorse::class.java] = RenderHorse(0.8F)
    }


    @EventListener
    fun registerItems(event: ItemRegistryEvent)
    {
        itemCharSaddle = ItemCharSaddle(Identifier.of(modID!!, "itemcharsaddle")).setTranslationKey(modID, "itemCharSaddle")
        itemHorseInfo = ItemHorseInfo(Identifier.of(modID!!, "itemhorseinfo")).setTranslationKey(modID, "itemHorseInfo")
        itemHorseAdmin = ItemHorseAdmin(Identifier.of(modID!!, "itemhorseadmin")).setTranslationKey(modID, "itemHorseAdmin")
    }

    @EventListener
    fun registerBlocks(event: BlockRegistryEvent)
    {
        blockHay = BlockHay(Identifier.of(modID!!, "blockhay")).setTranslationKey(modID, "blockHay")
    }

    @EventListener
    fun registerTextures(event: TextureRegisterEvent)
    {
        itemCharSaddle.setTexture("/assets/betahorses/textures/item/itemCharSaddle.png")
        itemHorseInfo.setTexture("/assets/betahorses/textures/item/itemHorseInfo.png")
        itemHorseAdmin.setTexture("/assets/betahorses/textures/item/itemHorseAdmin.png")

        textureHay_SIDE = ExpandableAtlas.STATION_TERRAIN.addTexture("/assets/betahorses/textures/block/blockHay_side.png").index
        textureHay_TOP = ExpandableAtlas.STATION_TERRAIN.addTexture("/assets/betahorses/textures/block/blockHay_top.png").index

    }

    @EventListener
    fun registerRecipe(event: RecipeRegisterEvent) {
        val type = event.recipeId
        if (type.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type()))
        {
            CraftingRegistry.addShapedRecipe(ItemInstance(blockHay),
            "XXX","XXX","XXX", Character.valueOf('X'), ItemBase.wheat)

            CraftingRegistry.addShapedRecipe(ItemInstance(itemHorseInfo),
            " X ", "XWX", " X ", Character.valueOf('X'), ItemBase.ironIngot, Character.valueOf('W'), blockHay)
        }
        if(type.equals(RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type()))
        {
            CraftingRegistry.addShapelessRecipe(ItemInstance(ItemBase.wheat, 9), ItemInstance(blockHay))
        }
        if(type.equals(RecipeRegisterEvent.Vanilla.SMELTING.type()))
        {
            SmeltingRegistry.addSmeltingRecipe(ItemInstance(ItemBase.saddle), ItemInstance(itemCharSaddle))
        }
    }








}
