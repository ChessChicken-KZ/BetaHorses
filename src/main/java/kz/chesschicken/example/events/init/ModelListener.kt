package kz.chesschicken.example.events.init

import net.modificationstation.stationapi.impl.client.model.CustomModel
import net.modificationstation.stationapi.api.client.event.model.ModelRegister
import net.modificationstation.stationapi.api.common.event.EventListener
import net.modificationstation.stationapi.api.common.factory.GeneralFactory
import net.modificationstation.stationapi.impl.client.model.CustomModelRenderer


class ModelListener {
    companion object {
        lateinit var CUSTOM_MODEL : CustomModel
    }

    @EventListener
    fun registerModels(event: ModelRegister?) {
        CUSTOM_MODEL = CustomModelRenderer( "/assets/examplemod/stationapi/models/model.json", "examplemod").getEntityModelBase()
    }
}