package kz.chesschicken.example.events.ingame

import net.minecraft.client.Minecraft

import kz.chesschicken.example.events.init.KeyBindingListener

import kz.chesschicken.example.events.init.AchievementListener

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.loader.api.FabricLoader
import org.lwjgl.input.Keyboard


class KeyPressedListener {

    @Environment(EnvType.CLIENT)
    fun keyPressed() {
        if (Keyboard.getEventKey() === KeyBindingListener.keyBinding.key) {
            (FabricLoader.getInstance().getGameInstance() as Minecraft).player.increaseStat(
                AchievementListener.achievement,
                1
            )
        }
        if (Keyboard.getEventKey() === KeyBindingListener.keyBinding2.key) {
            (FabricLoader.getInstance().getGameInstance() as Minecraft).player.increaseStat(
                AchievementListener.achievement2,
                1
            )
        }
        if (Keyboard.getEventKey() === KeyBindingListener.keyBinding3.key) {
            (FabricLoader.getInstance().getGameInstance() as Minecraft).player.damage(null, 1000000)
        }
    }

}