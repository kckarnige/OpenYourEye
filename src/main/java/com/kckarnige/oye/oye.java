package com.kckarnige.oye;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kckarnige.oye.item.ModItems;

public class oye implements ModInitializer {
	public static final String MOD_ID = "oye";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
	}
}