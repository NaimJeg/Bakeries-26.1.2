package com.renyigesai.bakeries.common.client.model;// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.renyigesai.bakeries.api.ResourceLocation;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;

public class GlassBreadRackDoorModel extends Model<Float> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("bakeries", "glass_bread_rack_door"), "main");
	private final ModelPart all;

	public GlassBreadRackDoorModel(ModelPart root) {
        super(root, RenderTypes::entityCutoutCull);
        this.all = root.getChild("all");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, 23.0F, -8.0F));

		PartDefinition cube_r1 = all.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, -17.0F, 0.9893F, 16.0F, 15.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 1.4651F, -1.6892F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r2 = all.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 15).addBox(-5.0F, -14.0F, -1.5F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 1.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Float state) {
		super.setupAnim(state);
		all.x = state;
	}

	public ModelPart getAll() {
		return all;
	}

}