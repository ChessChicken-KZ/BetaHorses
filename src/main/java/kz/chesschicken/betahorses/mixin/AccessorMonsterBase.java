package kz.chesschicken.betahorses.mixin;

import net.minecraft.entity.EntityBase;
import net.minecraft.entity.monster.MonsterBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MonsterBase.class)
public interface AccessorMonsterBase {
    @Invoker("getAttackTarget")
    EntityBase attackTarget();
}
