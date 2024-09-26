package kn.jktech.kolorful.mixin;

import net.minecraft.src.game.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.class)
public abstract class mixinItem {
    @Shadow
    protected boolean hasSubtypes;
    public Item setSubtypesmix (boolean a){

        this.hasSubtypes = a;
        return (Item) (Object)this;
    }
}
