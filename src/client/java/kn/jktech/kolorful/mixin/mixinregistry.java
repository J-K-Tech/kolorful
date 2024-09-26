package kn.jktech.kolorful.mixin;

import com.fox2code.foxloader.registry.GameRegistryClient;
import com.fox2code.foxloader.registry.ItemBuilder;
import com.fox2code.foxloader.registry.RegisteredItem;
import net.minecraft.src.game.block.Block;
import net.minecraft.src.game.block.BlockColored;
import net.minecraft.src.game.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.InvocationTargetException;

@Mixin(GameRegistryClient.class)
public class mixinregistry {
    public int kolor=960;

    @Inject(method = "registerNewItem0",at=@At("RETURN"),cancellable = true)

    private void registerNewItem0(String name, ItemBuilder itemBuilder,
                                  Block blockPrimary, Block blockSecondary,
                                  int itemId, boolean primary, CallbackInfoReturnable ci) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (blockPrimary instanceof BlockColored){
            Item i=(Item) ci.getReturnValue();
            ci.setReturnValue(
                    (Item)(i.getClass().getMethod("setSubtypesmix",boolean.class).invoke(i,true))
            );
        }
    }
    @Inject(method = "generateNewBlockId",at=@At("HEAD"),cancellable = true)
    public void generateNewBlockId(String name, int fallbackId,CallbackInfoReturnable ci) {
        if (name.split(":")[0].equals("kolor")){
            ci.setReturnValue(kolor++);
            ci.cancel();
        }
    }
}
