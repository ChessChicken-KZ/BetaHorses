package kz.chesschicken.example.custom

import net.minecraft.block.BlockBase
import net.modificationstation.stationapi.api.client.gui.screen.menu.AchievementPage
import java.util.*

class ExampleAchievementPage(name: String) : AchievementPage(name) {

    override fun getBackgroundTexture(random: Random, column: Int, row: Int, randomizedRow: Int, currentTexture: Int): Int {
        var k: Int = BlockBase.SAND.texture
        val l: Int = random.nextInt(1 + row) + row / 2
        if (l <= 37 && row != 35) {
            if (l == 22) {
                k = if (random.nextInt(2) !== 0) BlockBase.REDSTONE_ORE.texture else BlockBase.DIAMOND_ORE.texture
            } else if (l == 10) {
                k = BlockBase.WOOD.texture
            } else if (l == 8) {
                k = BlockBase.REDSTONE_ORE.texture
            } else if (l > 4) {
                k = BlockBase.STONE.texture
            } else if (l > 0) {
                k = BlockBase.DIRT.texture
            }
        } else {
            k = BlockBase.BEDROCK.texture
        }
        return k
    }

}