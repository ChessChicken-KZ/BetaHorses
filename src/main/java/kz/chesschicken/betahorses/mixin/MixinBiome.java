package kz.chesschicken.betahorses.mixin;

import kz.chesschicken.betahorses.entity.EntityHorse;
import net.minecraft.entity.EntityEntry;
import net.minecraft.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Biome.class)
public class MixinBiome {
    @Shadow protected List creatures;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addHorseSpawn(CallbackInfo ci)
    {
        this.creatures.add(new EntityEntry(EntityHorse.class, 4));
    }

}
