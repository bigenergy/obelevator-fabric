package com.bigenergy.obelevator.mixin;

import com.bigenergy.obelevator.block.ElevatorBlockFunctions;
import com.bigenergy.obelevator.util.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends Entity {
    public PlayerEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("HEAD"), method = "jump()V", cancellable = true)
    private void jump(CallbackInfo ci) {
        if (this.getWorld().isClient) return;
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        if (player.isInSneakingPose() || !player.isOnGround() || !PlayerUtils.isStandingOnElevator(player)) return;

        ElevatorBlockFunctions.goUp(player);
        ci.cancel();
    }
}
