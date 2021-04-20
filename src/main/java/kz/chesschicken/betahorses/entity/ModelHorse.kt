package kz.chesschicken.betahorses.entity

import net.minecraft.client.model.Cuboid
import net.minecraft.client.render.entity.model.EntityModelBase
import net.minecraft.util.maths.MathHelper

class ModelHorse : EntityModelBase() {
    var body: Cuboid = Cuboid(26, 0)
    var tail: Cuboid = Cuboid(24, 36)
    var neck: Cuboid = Cuboid(0, 32)
    var head: Cuboid = Cuboid(0, 0)
    var leg2: Cuboid = Cuboid(0, 15)
    var leg4: Cuboid = Cuboid(0, 15)
    var leg3: Cuboid = Cuboid(0, 15)
    var leg1: Cuboid = Cuboid(0, 15)
    var hair: Cuboid = Cuboid(16, 16)
    var ear1: Cuboid = Cuboid(22, 14)
    var ear2: Cuboid = Cuboid(22, 14)
    var saddle: Cuboid = Cuboid(35,35)

    init {
        body.method_1817(-5F, -10F, -7F, 8, 19, 9)
        body.setRotationPoint(1.0F, 6F, 2.0F)


        body.mirror = true
        setRotation(body, 1.570796F, 0.0F, 0.0F)

        tail.method_1817(0.0F, 0.0F, 1.0F, 2, 12, 2)
        tail.setRotationPoint(-1F, 5F, 8F)
        tail.mirror = true
        setRotation(tail, 0.3717861F, 0.0F, 0.0F)

        neck.method_1817(-3F, -8F, 0.0F, 4, 12, 6)
        neck.setRotationPoint(1.0F, 6F, -10F)
        neck.mirror = true
        setRotation(neck, 0.5576792F, 0.0F, 0.0F)

        head.method_1817(-2F, -11F, -6F, 4, 5, 9)
        head.setRotationPoint(0.0F, 6F, -10F)
        head.mirror = true
        setRotation(head, 0.2230717F, 0.0F, 0.0F)

        leg2.method_1817(-2F, -1F, 1.0F, 3, 13, 3)
        leg2.setRotationPoint(3F, 12F, 6F)
        leg2.mirror = true
        setRotation(leg2, 0.0F, 0.0F, 0.0F)

        leg4.method_1817(-2F, -1F, -2F, 3, 13, 3)
        leg4.setRotationPoint(3F, 12F, -5F)
        leg4.mirror = true
        setRotation(leg4, 0.0F, 0.0F, 0.0F)

        leg3.method_1817(-1F, -1F, -2F, 3, 13, 3)
        leg3.setRotationPoint(-3F, 12F, -5F)
        leg3.mirror = true
        setRotation(leg3, 0.0F, 0.0F, 0.0F)

        leg1.method_1817(-1F, -1F, 1.0F, 3, 13, 3)
        leg1.setRotationPoint(-3F, 12F, 6F)
        leg1.mirror = true
        setRotation(leg1, 0.0F, 0.0F, 0.0F)

        hair.method_1817(0.0F, -8F, 1.0F, 0, 13, 3)
        hair.setRotationPoint(0.0F, 3F, -7F)
        hair.mirror = true
        setRotation(hair, 0.5576851F, 0.0F, 0.0F)

        ear1.method_1817(1.0F, -13F, -2F, 0, 3, 1)
        ear1.setRotationPoint(1.0F, 6F, -9F)
        ear1.mirror = true
        setRotation(ear1, 0.0F, 0.0F, -0.0371786F)

        ear2.method_1817(-2F, -13F, -2F, 0, 3, 1)
        ear2.setRotationPoint(0.0F, 6F, -9F)
        ear2.mirror = true
        setRotation(ear2, 0.0F, 0.0F, 0.0F)

        saddle.method_1817(0.0F, 0.0F, 0.0F, 8, 7, 9)
        saddle.setRotationPoint(-4F, 3.9F, -2F)
        saddle.mirror = true
        setRotation(saddle, 0.0F, 0.0F, 0.0F)
    }

    private fun setRotation(model: Cuboid, f: Float, f1: Float, f2: Float) {
        model.pitch = f
        model.yaw = f1
        model.roll = f2
    }

    override fun render(f: Float, f1: Float, f2: Float, f3: Float, f4: Float, f5: Float) {
        super.render(f, f1, f2, f3, f4, f5)
        setAngles(f, f1, f2, f3, f4, f5)
        body.method_1815(f5)
        tail.method_1815(f5)
        neck.method_1815(f5)
        head.method_1815(f5)
        leg2.method_1815(f5)
        leg4.method_1815(f5)
        leg3.method_1815(f5)
        leg1.method_1815(f5)
        hair.method_1815(f5)
        ear1.method_1815(f5)
        ear2.method_1815(f5)
        saddle.method_1815(f5)
    }

    override fun setAngles(f: Float, f1: Float, f2: Float, f3: Float, f4: Float, f5: Float) {
        super.setAngles(f, f1, f2, f3, f4, f5)
        tail.pitch = MathHelper.cos(f / 1.919108F) * 0.2268928F * f1 + 0.3717861F
        neck.pitch = MathHelper.cos(f / 1.919108F) * -0.2617994F * f1 + 0.5576792F
        head.pitch = MathHelper.cos(f / 1.919108F) * -0.2617994F * f1 + 0.2230717F
        hair.pitch = MathHelper.cos(f / 1.919108F) * -0.2617994F * f1 + 0.5576851F
        ear1.pitch = MathHelper.cos(f / 1.919108F) * -0.2617994F * f1 + 0.0F
        ear2.pitch = MathHelper.cos(f / 1.919108F) * -0.2617994F * f1 + 0.0F
        leg1.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1
        leg2.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1
        leg3.pitch = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1
        leg4.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * f1
        leg1.pitch = MathHelper.cos(f / 1.919108F) * -0.6108652F * f1
        leg2.pitch = MathHelper.cos(f / 1.919108F) * -0.6108652F * f1
        leg4.pitch = MathHelper.cos(f / 1.919108F) * 0.5235988F * f1
        leg3.pitch = MathHelper.cos(f / 1.919108F) * 0.4363323F * f1
    }

}