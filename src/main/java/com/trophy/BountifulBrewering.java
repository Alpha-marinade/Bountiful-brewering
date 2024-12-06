package com.trophy;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.trophy.common.itemReg;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.include.com.google.gson.Gson;
import org.spongepowered.include.com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.trophy.data.foodData;

public class BountifulBrewering implements ModInitializer {
	public static final String MOD_ID = "bountiful_brewering";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static List<Item> allItems = new ArrayList<>();

	private static final String CONFIG = "bountiful_brewering.json";
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	public boolean foodExpiration = true;

	public void load(){
		File configFile = new File(FabricLoader.getInstance().getConfigDirectory(), CONFIG);
		if (configFile.exists()) {
			try (FileReader reader = new FileReader(configFile)) {
				BountifulBrewering config = GSON.fromJson(reader, BountifulBrewering.class);
				this.foodExpiration = config.foodExpiration;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			save();
		}
	}
	public void save() {
		File configFile = new File(FabricLoader.getInstance().getConfigDirectory(), CONFIG);
		try (FileWriter writer = new FileWriter(configFile)) {
			GSON.toJson(this, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onInitialize() {
		itemReg.init();
		load();
		Registries.ITEM.forEach(item -> allItems.add(item));
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new foodData());


	}
}


