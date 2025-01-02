/*
 * -------------------------------------------------------------------
 * Redisruption
 * Copyright (c) 2025 SciRave
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * -------------------------------------------------------------------
 */

package net.scirave.disruption;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.event.GameEvent;

import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Disruption implements ModInitializer {
    public static final String MOD_ID = "disruption";

    public static final TagKey<Block> HANGS = getBlockTag("hangs");
    public static final TagKey<Block> PROTECTED = getBlockTag("protected");
    public static final TagKey<Block> BUOYANT = getBlockTag("buoyant");
	public static final TagKey<Block> FLOATS = getBlockTag("floats");
    public static final TagKey<Block> USE_DEFAULT_STATE = getBlockTag("default_state");

	public static final GameEvent FIRE_SPREAD = new GameEvent("fire_spread", 16);
	public static final GameEvent BLOCK_EXPLODED = new GameEvent("block_exploded", 16);

	public static final TagKey<GameEvent> DISRUPTION = getGameEventTag("disruption");
	public static final TagKey<GameEvent> NEIGHBOR_DISRUPTION = getGameEventTag("neighbor_disruption");
	public static final TagKey<GameEvent> ENTITY_DISRUPTION = getGameEventTag("entity_disruption");
	public static final TagKey<GameEvent> ENTITY_NEIGHBOR_DISRUPTION = getGameEventTag("entity_neighbor_disruption");

	public static Identifier getIdentifier(String name) {
		return new Identifier(MOD_ID, name);
	}

	public static TagKey<Block> getBlockTag(String id) {
		return TagKey.of(RegistryKeys.BLOCK, getIdentifier(id));
	}
	public static TagKey<GameEvent> getGameEventTag(String id) {
		return TagKey.of(RegistryKeys.GAME_EVENT, getIdentifier(id));
	}

    @Override
    public void onInitialize() {
		Registry.register(Registries.GAME_EVENT, Identifier.tryParse(FIRE_SPREAD.getId()), FIRE_SPREAD);
		Registry.register(Registries.GAME_EVENT, Identifier.tryParse(BLOCK_EXPLODED.getId()), BLOCK_EXPLODED);

        Logger.getLogger(MOD_ID).log(Level.INFO, "[{}] It's raining stone and.. barrels?", MOD_ID);
    }
}

