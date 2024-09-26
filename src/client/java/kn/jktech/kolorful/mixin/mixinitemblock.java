package kn.jktech.kolorful.mixin;

import net.minecraft.src.game.block.Block;
import net.minecraft.src.game.block.BlockColored;
import net.minecraft.src.game.entity.player.EntityPlayer;
import net.minecraft.src.game.item.ItemBlock;
import net.minecraft.src.game.item.ItemStack;
import net.minecraft.src.game.level.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.lang.model.util.SimpleElementVisitor7;

@Mixin(ItemBlock.class)
public class mixinitemblock {
    @Shadow
    public int blockID;
    @Inject(method = "onItemUse",at=@At("HEAD"),cancellable = true)
    public void onItemUse(
            ItemStack itemstack,
            EntityPlayer player,
            World world,
            int x,
            int y,
            int z,
            int facing,
            float xVec,
            float yVec,
            float zVec, CallbackInfoReturnable ci
            ) {
        Block b=Block.blocksList[this.blockID];
        if (b instanceof BlockColored){
            switch (facing) {
                case 0:
                    y--;
                    break;
                case 1:
                    y++;
                    break;
                case 2:
                    z--;
                    break;
                case 3:
                    z++;
                    break;
                case 4:
                    x--;
                    break;
                case 5:
                    x++;
            }
            if (itemstack.stackSize == 0) {
                System.out.println("itemstack is 0?");
                ci.setReturnValue(false);
                ci.cancel();
            } else if (world.canBlockBePlacedAt(this.blockID, x, y, z, false, facing)) {
                System.out.println("can place block");
                world.setBlockAndMetadataWithNotify(x,y,z,this.blockID, itemstack.getItemDamage());
                ci.setReturnValue(true);
                ci.cancel();
            } else {
                System.out.println("cant place block");
                ci.setReturnValue(false);
                ci.cancel();
            }
        }
    }
}
