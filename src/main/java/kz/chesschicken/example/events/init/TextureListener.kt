package kz.chesschicken.example.events.init

import net.modificationstation.stationapi.api.client.event.texture.TextureRegister
import net.modificationstation.stationapi.api.client.texture.TextureFactory
import net.modificationstation.stationapi.api.common.event.EventListener
import net.modificationstation.stationapi.impl.client.texture.TextureRegistry


class TextureListener {

    @EventListener
    fun registerTextures(event: TextureRegister?) {
        ItemListener.coolItem.setTexturePosition(
            TextureFactory.INSTANCE.addTexture(TextureRegistry.getRegistry("GUI_ITEMS") as TextureRegistry?, "/assets/examplemod/textures/coolItem.png")
        )
    }

}