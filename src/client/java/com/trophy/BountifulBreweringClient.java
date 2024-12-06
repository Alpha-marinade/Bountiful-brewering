package com.trophy;

import com.trophy.common.itemReg;
import com.trophy.utils.foodToolTip;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class BountifulBreweringClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock (itemReg.SPOREFLOWERSTEM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock (itemReg.HAZELSTEM_CROP, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock (itemReg.WILD_HAZELSTEM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock (itemReg.TURNIP_CROP, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock (itemReg.WILD_TURNIP, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock (itemReg.BLACK_GRAPE_VINE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock (itemReg.MUSTARD_CROP, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock (itemReg.DEEPSHROOMS, RenderLayer.getCutout());
		foodToolTip.init();

	}
}