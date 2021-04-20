package kz.chesschicken.betahorses.mixin;

import kz.chesschicken.betahorses.gui.GuiUpdate;
import kz.chesschicken.betahorses.init.BetaHorsesListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ScreenBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {
    @Shadow public abstract void openScreen(ScreenBase screen);

    @Inject(method = "init", at = @At("RETURN"))
    private void checkUpdate(CallbackInfo ci)
    {
        if(!BetaHorsesListener.Companion.getNeedToRemind().equals(BetaHorsesListener.Companion.getModID().getVersion().getFriendlyString()))
        {
            this.openScreen(new GuiUpdate());
        }
    }
}
