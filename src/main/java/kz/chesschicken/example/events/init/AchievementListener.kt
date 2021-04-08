package kz.chesschicken.example.events.init

import net.minecraft.achievement.Achievement
import net.modificationstation.stationapi.api.common.event.EventListener
import kz.chesschicken.example.custom.ExampleAchievementPage
import net.minecraft.item.ItemBase
import net.modificationstation.stationapi.api.client.gui.screen.menu.AchievementPage
import net.modificationstation.stationapi.api.common.event.achievement.AchievementRegister

class AchievementListener {
    companion object {
        lateinit var achievement : Achievement
        lateinit var achievement2 : Achievement
    }

    @EventListener
    fun registerAchievements(event: AchievementRegister) {
        val list = event.achievements
        val achievementPage: AchievementPage = ExampleAchievementPage("examplemod")
        achievement = Achievement(69696969, "examplemod.boned", -1, 0, ItemBase.bone, null)
        achievement2 = Achievement(69696970, "examplemod.apple", 0, 10, ItemBase.apple, achievement)
        achievement2.setUnusual()
        list.add(achievement)
        list.add(achievement2)
        achievementPage.addAchievements(achievement, achievement2)
    }
}