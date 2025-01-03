/*
 * -------------------------------------------------------------------
 * Redisruption
 * Copyright (c) 2025 SciRave, usernameak
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * -------------------------------------------------------------------
 */

package net.scirave.disruption.mixin;

import net.minecraft.block.FireBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.scirave.disruption.Disruption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireBlock.class)
public abstract class FireBlockMixin extends BlockMixin {

	@Inject(method = "trySpreadingFire", at = @At("RETURN"))
	protected void disruption$fireSpread(World world, BlockPos pos, int spreadFactor, Random random, int currentAge, CallbackInfo ci) {
		if (world.getBlockState(pos).isAir()) {
			GameEvent.Emitter context = GameEvent.Emitter.of(world.getBlockState(pos));
			world.emitGameEvent(Disruption.FIRE_SPREAD, Vec3d.of(pos), context);
		}
	}
}
