package kz.chesschicken.betahorses.mixin;

import kz.chesschicken.betahorses.entity.EntityHorse;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.Saddle;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Saddle.class)
public abstract class MixinSaddle extends ItemBase {
    protected MixinSaddle(int id) { super(id); }

    @Override
    public ItemInstance use(ItemInstance item, Level level, PlayerBase player) {
        EntityHorse newHorse = new EntityHorse(level);
        newHorse.setPosition(player.x, player.y + 0.5, player.z);
        level.spawnEntity(newHorse);
        return item;
    }
}
