package com.trophy.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.trophy.utils.foodConfig;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import static com.mojang.text2speech.Narrator.LOGGER;
import static com.trophy.BountifulBrewering.MOD_ID;

public class foodData implements SimpleSynchronousResourceReloadListener {
    @Override
    public Identifier getFabricId() {
        return new Identifier(MOD_ID, "foods");
    }

    @Override
    public void reload(ResourceManager manager) {
        for(Identifier id : manager.findResources("foods", path -> path.getPath().endsWith(".json")).keySet()) {
            System.out.println(id.getPath().replace("foods/", "").replace(".json",""));
            System.out.println(read(manager,id,"hi"));

        }
    }
    public JsonElement read(ResourceManager manager, Identifier id, String get){

        try(InputStream stream = manager.getResource(id).get().getInputStream()) {
            try (Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                JsonElement jsonElement = JsonParser.parseReader(reader);
                if (jsonElement.isJsonObject()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    return jsonObject.get(get);
                }
            } catch(Exception e) {
                LOGGER.error("Error occurred while loading resource json" + id.toString(), e);
            }
        } catch(Exception e) {
            LOGGER.error("Error occurred while loading resource json" + id.toString(), e);
        }
        return null;
    }
}
