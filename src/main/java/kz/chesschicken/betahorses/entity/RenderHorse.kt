package kz.chesschicken.betahorses.entity

import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.entity.EntityBase
import net.minecraft.entity.Living
import org.lwjgl.opengl.GL11

class RenderHorse(f: Float) : LivingEntityRenderer(ModelHorse(), f) {

    override fun method_823(arg: Living, f: Float) {
        super.method_823(arg, f)
        val f1: Float = (arg as EntityHorse).size
        GL11.glScalef(f1, f1, f1)
    }

    private fun renderHorse(entityliving: Living, d: Double, d1: Double, d2: Double, f: Float, f1: Float) {
        super.render(entityliving, d, d1, d2, f, f1)
    }




    override fun render(entity: EntityBase, x: Double, y: Double, z: Double, f: Float, f1: Float) {
        renderHorse(entity as Living, x, y, z, f, f1)
    }

}