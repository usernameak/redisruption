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

package net.scirave.disruption.compat;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.valkyrienskies.core.apigame.world.ServerShipWorldCore;
import org.valkyrienskies.mod.common.IShipObjectWorldServerProvider;
import org.valkyrienskies.mod.common.VSGameUtilsKt;

public class ValkyrienSkiesCompat {
	private static Class<IShipObjectWorldServerProvider> shipObjectWorldServerProviderClass;

	static {
		try {
			//noinspection unchecked
			shipObjectWorldServerProviderClass = (Class<IShipObjectWorldServerProvider>) Class.forName(
				"org.valkyrienskies.mod.common.IShipObjectWorldServerProvider"
			);
		} catch (ClassNotFoundException e) {
			shipObjectWorldServerProviderClass = null;
		}
	}

	public static boolean checkBlockInVSShip(World world, BlockPos pos) {
		if (shipObjectWorldServerProviderClass == null) return false;
		return checkBlockInVSShipImpl(world, pos);
	}

	private static boolean checkBlockInVSShipImpl(World world, BlockPos pos) {
		IShipObjectWorldServerProvider provider = (IShipObjectWorldServerProvider) world.getServer();
		if (provider == null) return false;

		ServerShipWorldCore shipObjectWorld = provider.getShipObjectWorld();
		if (shipObjectWorld == null) return false;

		return shipObjectWorld.isBlockInShipyard(pos.getX(), pos.getY(), pos.getZ(), VSGameUtilsKt.getDimensionId(world));
	}
}
