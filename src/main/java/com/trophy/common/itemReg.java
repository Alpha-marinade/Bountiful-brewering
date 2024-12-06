package com.trophy.common;


import com.trophy.block.*;
import com.trophy.block.crops.hazelStem;
import com.trophy.block.crops.mustard;
import com.trophy.block.crops.turnip;
import com.trophy.block.crops.wildPlant;
import com.trophy.item.hzchoeto;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.trophy.BountifulBrewering.MOD_ID;

public class itemReg {
    public static Item ROT = new Item(new Item.Settings());
    public static Block STOVE = new stove(AbstractBlock.Settings.create()
            .pistonBehavior(PistonBehavior.IGNORE)
            .ticksRandomly()
            .luminance(stove::getLuminance), ParticleTypes.FLAME);
    public static Block DEEPSHROOMS=new Block(AbstractBlock.Settings.create()
            .pistonBehavior(PistonBehavior.DESTROY)
            .breakInstantly()
            .offset(AbstractBlock.OffsetType.XYZ)
            .noCollision()
            );
    public static Item TRUFFLE = new Item(new FabricItemSettings());
    public static Block BLACK_GRAPE_VINE= new BlackGrapeVine(AbstractBlock.Settings.create()
            .noCollision()
            .breakInstantly()
            .pistonBehavior(PistonBehavior.DESTROY)
            .burnable()
            .ticksRandomly()
            .allowsSpawning((state, world, pos, type) -> false));
    public static Item HZ_CHO_ETO = new hzchoeto(new Item.Settings());
    public static Item HAZELSTEM= new Item(new Item.Settings());
    public static Item GLASS_JAR= new Item(new Item.Settings());
    public static Item MUSTARD_JAR= new Item(new Item.Settings());
    public static Item TRUFFLE_JAR= new Item(new Item.Settings());
    public static Item MUSTARD_FLOWER= new Item(new Item.Settings());
    public static Item DEEP_STUFFED_POTATO= new Item(new Item.Settings()
            .food(new FoodComponent.Builder()
                    .hunger(7)
                    .saturationModifier(6)
                    .build()));
    public static Block SPOREFLOWERFRUCT = new sporeFlowerFruct(AbstractBlock.Settings.create()
      .ticksRandomly()
      .noCollision()
      .breakInstantly()
      .offset(AbstractBlock.OffsetType.XZ)
      .pistonBehavior(PistonBehavior.DESTROY)
      .luminance(state -> 15)
      .allowsSpawning((state, world, pos, type) -> false));
    public static Block SPOREFLOWERSTEM = new sporeFlowerStem(AbstractBlock.Settings.create()
      .noCollision()
      .ticksRandomly()
      .breakInstantly()
      .pistonBehavior(PistonBehavior.DESTROY)
      .offset(AbstractBlock.OffsetType.XZ));

    public static Block HAZELSTEM_CROP =new hazelStem(FabricBlockSettings.copyOf(Blocks.WHEAT));
   // public static Block
    public static Block TRUFFLE_CANDLE = new truffleCandle(AbstractBlock.Settings
            .create()
            .luminance(truffleCandle::getLuminance)
            .breakInstantly()
            .pistonBehavior(PistonBehavior.DESTROY), ParticleTypes.FLAME
    );

    public static Block MUSTARD_CROP =new mustard(FabricBlockSettings.copyOf(Blocks.WHEAT));
    public static Block TURNIP_CROP =new turnip(FabricBlockSettings.copyOf(Blocks.WHEAT));
    public static Block WILD_HAZELSTEM = new wildPlant(FabricBlockSettings.copyOf(Blocks.GRASS));
    public static Block WILD_TURNIP = new wildPlant(FabricBlockSettings.copyOf(Blocks.GRASS));
    public static void init(){
        Registry.register(Registries.BLOCK,Identifier.of(MOD_ID,"stove"),STOVE);
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "stove"), new BlockItem(STOVE, new Item.Settings()));
        Registry.register(Registries.BLOCK,Identifier.of(MOD_ID,"black_grape_vine"),BLACK_GRAPE_VINE);
        Registry.register(Registries.BLOCK,Identifier.of(MOD_ID,"deepshrooms"),DEEPSHROOMS);
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "deepshrooms"), new BlockItem(DEEPSHROOMS, new Item.Settings()));
        Registry.register(Registries.BLOCK,Identifier.of(MOD_ID,"truffle_candle"),TRUFFLE_CANDLE);
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "truffle_candle"), new BlockItem(TRUFFLE_CANDLE, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "black_grape_vine_seeds"), new BlockItem(BLACK_GRAPE_VINE, new Item.Settings()));
        Registry.register(Registries.ITEM,Identifier.of(MOD_ID,"hz_cho_eto"),HZ_CHO_ETO);
        Registry.register(Registries.ITEM,Identifier.of(MOD_ID,"truffle"),TRUFFLE);
        Registry.register(Registries.ITEM,Identifier.of(MOD_ID,"rot"),ROT);
        Registry.register(Registries.ITEM,Identifier.of(MOD_ID,"glass_jar"),GLASS_JAR);
        Registry.register(Registries.ITEM,Identifier.of(MOD_ID,"deep_stuffed_potato"),DEEP_STUFFED_POTATO);
        Registry.register(Registries.ITEM,Identifier.of(MOD_ID,"truffle_jar"),TRUFFLE_JAR);
        Registry.register(Registries.ITEM,Identifier.of(MOD_ID,"mustard_jar"),MUSTARD_JAR);
        Registry.register(Registries.ITEM,Identifier.of(MOD_ID,"mustard_flower"),MUSTARD_FLOWER);
        Registry.register(Registries.BLOCK,Identifier.of(MOD_ID,"wild_hazelstem"),WILD_HAZELSTEM);
        Registry.register(Registries.BLOCK,Identifier.of(MOD_ID,"wild_turnip"),WILD_TURNIP);
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "wild_turnip"), new BlockItem(WILD_TURNIP, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "wild_hazelstem"), new BlockItem(WILD_HAZELSTEM, new Item.Settings()));
        Registry.register(Registries.ITEM,Identifier.of(MOD_ID,"hazelstem"),HAZELSTEM);
        Registry.register(Registries.BLOCK,Identifier.of(MOD_ID,"hazelstem_crop"),HAZELSTEM_CROP);
        Registry.register(Registries.BLOCK,Identifier.of(MOD_ID,"mustard_crop"),MUSTARD_CROP);
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "mustard_seeds"), new BlockItem(MUSTARD_CROP, new Item.Settings()));
        Registry.register(Registries.BLOCK,Identifier.of(MOD_ID,"turnip_crop"),TURNIP_CROP);
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "turnip"), new BlockItem(TURNIP_CROP, new Item.Settings()));
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "hazelstem_seeds"), new BlockItem(HAZELSTEM_CROP, new Item.Settings()));
        Registry.register(Registries.BLOCK, Identifier.of(MOD_ID,"crimson_fruit"), SPOREFLOWERFRUCT);
       Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "crimson_fruit"), new BlockItem(SPOREFLOWERFRUCT, new Item.Settings()));
        Registry.register(Registries.BLOCK, Identifier.of(MOD_ID,"crimson_stem"), SPOREFLOWERSTEM);
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "crimson_stem"), new BlockItem(SPOREFLOWERSTEM, new Item.Settings()));
        Registry.register(Registries.ITEM_GROUP, BBKEY, BBGROUP);

        ItemGroupEvents.modifyEntriesEvent(BBKEY).register(itemGroup -> {
            itemGroup.add(HZ_CHO_ETO);
            itemGroup.add(SPOREFLOWERFRUCT);
            itemGroup.add(SPOREFLOWERSTEM);
            itemGroup.add( HAZELSTEM);
            itemGroup.add(HAZELSTEM_CROP);
            itemGroup.add(WILD_HAZELSTEM);
            itemGroup.add(TURNIP_CROP);
            itemGroup.add(WILD_TURNIP);
            itemGroup.add(BLACK_GRAPE_VINE);
            itemGroup.add(GLASS_JAR);
            itemGroup.add(MUSTARD_JAR);
            itemGroup.add(MUSTARD_FLOWER);
            itemGroup.add(MUSTARD_CROP);
            itemGroup.add(DEEP_STUFFED_POTATO);
            itemGroup.add(TRUFFLE_JAR);
            itemGroup.add(TRUFFLE_CANDLE);
            itemGroup.add(TRUFFLE);
            itemGroup.add(DEEPSHROOMS);
            itemGroup.add(STOVE);



        });
    }
    public static final RegistryKey<ItemGroup> BBKEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(),
            Identifier.of(MOD_ID, "bountiful_brewering_group"));
    public static final ItemGroup BBGROUP = FabricItemGroup.builder()
        .icon(() -> new ItemStack(SPOREFLOWERFRUCT))
            .displayName(Text.translatable("itemGroup.bountiful_brewering"))
            .build();
}
