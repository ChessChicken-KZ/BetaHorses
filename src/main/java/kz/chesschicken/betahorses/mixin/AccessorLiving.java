package kz.chesschicken.betahorses.mixin;

import net.minecraft.entity.Living;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Living.class)
public interface AccessorLiving {
    @Accessor("field_1029")
    public float field_1029();
}
