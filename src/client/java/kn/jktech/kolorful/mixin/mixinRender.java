package kn.jktech.kolorful.mixin;

import kn.jktech.kolorful.blocks.blockDisco;
import net.minecraft.src.client.renderer.RenderBlocks;
import net.minecraft.src.client.renderer.Tessellator;
import net.minecraft.src.client.renderer.block.icon.Icon;
import net.minecraft.src.game.block.*;
import net.minecraft.src.game.item.ItemDye;
import net.minecraft.src.game.level.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderBlocks.class)
public abstract class mixinRender {
    @Shadow
    private boolean enableAO;
    @Shadow
    private boolean renderAllFaces;
    @Shadow
    private IBlockAccess blockAccess;
    @Shadow
    public abstract boolean renderStandardBlockWithColorMultiplier(Block block, int x, int y, int z, float r, float g, float b);

    @Inject(method = "renderBlockByRenderType",at=@At("HEAD"),cancellable = true)
    public void renderBlockByRenderType(Block block, int x, int y, int z, CallbackInfoReturnable ci){

        if (block instanceof blockDisco) {
            float[] rgb=ItemDye.ballColors[this.blockAccess.getBlockMetadata(x,y,z)];
            float r=rgb[0];
            float g=rgb[1];
            float b=rgb[2];
            Material mUp = this.blockAccess.getBlockMaterial(x, y + 1, z);
            boolean coveredInSnow = (mUp == Material.builtSnow || mUp == Material.snow)
                    && block.blockMaterial != Material.snow
                    && block.blockMaterial != Material.builtSnow;
            boolean coveredInAsh = mUp == Material.ash && block.blockMaterial != Material.ash;
            this.enableAO = false;
            Tessellator instance = Tessellator.instance;
            boolean var9 = false;
            float var10 = 0.5F;
            float var11 = 1.0F;
            float var12 = 0.8F;
            float var13 = 0.6F;
            float var14 = var11 * r;
            float var15 = var11 * g;
            float var16 = var11 * b;
            float var17 = var10;
            float var18 = var12;
            float var19 = var13;
            float var20 = var10;
            float var21 = var12;
            float var22 = var13;
            float var23 = var10;
            float var24 = var12;
            float var25 = var13;
            if (block != Block.grass) {
                var17 = var10 * r;
                var18 = var12 * r;
                var19 = var13 * r;
                var20 = var10 * g;
                var21 = var12 * g;
                var22 = var13 * g;
                var23 = var10 * b;
                var24 = var12 * b;
                var25 = var13 * b;
            }

            float var26 = block.getBlockBrightness(this.blockAccess, x, y, z);
            if (this.renderAllFaces || block.shouldSideBeRendered(this.blockAccess, x, y - 1, z, 0)) {

                instance.setColorOpaque_F(r, g, b);
                   ((RenderBlocks) (Object) this).renderBottomFace(
                            block, (double)x, (double)y, (double)z, block.getBlockTexture(this.blockAccess, x, y, z, 0)
                    );
                }
            

            if (this.renderAllFaces || block.shouldSideBeRendered(this.blockAccess, x, y + 1, z, 1)) {

                instance.setColorOpaque_F(r, g, b);
                   ((RenderBlocks) (Object) this).renderTopFace(
                            block, (double)x, (double)y, (double)z, block.getBlockTexture(this.blockAccess, x, y, z, 1)
                    );
            }

            if (this.renderAllFaces || block.shouldSideBeRendered(this.blockAccess, x, y, z - 1, 2)) {

                instance.setColorOpaque_F(r, g, b);
                Icon texture = block.getBlockTexture(this.blockAccess, x, y, z, 2);
                   ((RenderBlocks) (Object) this).renderEastFace(block, (double)x, (double)y, (double)z, texture);
                var9 = true;
            }

            if (this.renderAllFaces || block.shouldSideBeRendered(this.blockAccess, x, y, z + 1, 3)) {
                instance.setColorOpaque_F(r, g, b);
                Icon texture = block.getBlockTexture(this.blockAccess, x, y, z, 3);
                   ((RenderBlocks) (Object) this).renderWestFace(block, (double)x, (double)y, (double)z, texture);
            }

            if (this.renderAllFaces || block.shouldSideBeRendered(this.blockAccess, x - 1, y, z, 4)) {
                instance.setColorOpaque_F(r, g, b);
                Icon texture = block.getBlockTexture(this.blockAccess, x, y, z, 4);
                   ((RenderBlocks) (Object) this).renderNorthFace(block, (double)x, (double)y, (double)z, texture);
            }

            if (this.renderAllFaces || block.shouldSideBeRendered(this.blockAccess, x + 1, y, z, 5)) {
                instance.setColorOpaque_F(r, g, b);
                Icon texture = block.getBlockTexture(this.blockAccess, x, y, z, 5);
                   ((RenderBlocks) (Object) this).renderSouthFace(block, (double)x, (double)y, (double)z, texture);
            }
            ci.cancel();
        }
    }
}
