package kz.chesschicken.betahorses.gui

import kz.chesschicken.betahorses.init.BetaHorsesListener
import net.minecraft.client.gui.screen.ScreenBase
import net.minecraft.client.gui.screen.menu.MainMenu
import net.minecraft.client.gui.widgets.Button
import java.awt.Color
import java.awt.Desktop
import java.net.URI

class GuiUpdate : ScreenBase() {

    override fun init() {
        this.buttons.clear()
        val var4 = height / 4 + 48
        this.buttons.add(Button(0, this.width / 2 - 100, var4 + 48, "Go to webpage."))
        this.buttons.add(Button(1, this.width / 2 - 100, var4 + 72, "Continue playing."))
    }

    override fun buttonClicked(button: Button?) {
        if(button!!.id == 0)
        {
            Desktop.getDesktop().browse(URI(net.fabricmc.loader.api.FabricLoader.getInstance().getModContainer("betahorses").get().metadata.contact.get("sources").get()))
        }

        if(button.id == 1)
        {
            minecraft.openScreen(MainMenu())
        }
    }

    override fun render(mouseX: Int, mouseY: Int, delta: Float) {
        this.renderBackground()
        drawTextWithShadowCentred(textManager, "Update BetaHorses", width / 2, height / 4 - 40, 16777215)

        drawTextWithShadow(textManager, "A new update for the mod \'BetaHorse\' has been finally released!", this.width / 2 - 140, this.height / 4 + 18, Color.WHITE.rgb)
        drawTextWithShadow(textManager, "Your version is "+BetaHorsesListener.modID!!.version.friendlyString+" , but new is "+BetaHorsesListener.needToRemind, this.width / 2 - 140, this.height / 4 + 28, Color.WHITE.rgb)


        super.render(mouseX, mouseY, delta)
    }
}