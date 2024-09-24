package kn.jktech.kolorful;

import com.fox2code.foxloader.loader.ClientMod;
import com.fox2code.foxloader.loader.Mod;
import com.fox2code.foxloader.registry.*;
import net.minecraft.src.game.block.Block;
import net.minecraft.src.game.block.BlockColored;
import net.minecraft.src.game.block.BlockPlanks;
import net.minecraft.src.game.block.Material;
import net.minecraft.src.game.item.Item;
import net.minecraft.src.game.item.ItemBlockColored;
import net.minecraft.src.game.item.ItemDye;
import net.minecraft.src.game.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class kolor extends Mod  implements ClientMod {
    public static List<Integer> blockids=new ArrayList<>();
    public static List<Integer> itemids=new ArrayList<>();
    @Override
    public void onInit() {
        System.out.println("kolorful blocks");
        for (int color = 0; color < 16; color++) {
            RegisteredBlock hardclay=registerNewBlock(ItemDye.textureColors[color]+"_hardened_clay",
                    new BlockBuilder().setBlockName(ItemDye.textureColors[color]+"_hardened_clay").setEffectiveTool(RegisteredToolType.PICKAXE)
                            .setBlockHardness(2.0F)
                            .setBlockResistance(10.0F)
                            .setBlockStepSounds(GameRegistry.BuiltInStepSounds.STONE)
            );
            blockids.add(hardclay.getRegisteredBlockId());
            itemids.add(hardclay.asRegisteredItem().getRegisteredItemId());
        registerRecipe(new ItemStack(hardclay.asRegisteredItem().getRegisteredItemId(),8)
        ,
"BBB",
        "BDB",
        "BBB",
                'B', Block.hardenedClay,'D', new ItemStack(Item.dyePowder,1,color));}
            for (int color = 0; color < 16; color++) {
        if (color!=0){
            RegisteredBlock bricks=registerNewBlock(ItemDye.textureColors[color]+"_bricks",
                    new BlockBuilder().setBlockName(ItemDye.textureColors[color]+"_bricks").setEffectiveTool(RegisteredToolType.PICKAXE)
                            .setBlockHardness(2.0F)
                            .setBlockResistance(10.0F)
                            .setBlockStepSounds(GameRegistry.BuiltInStepSounds.STONE)
            );
            blockids.add(bricks.getRegisteredBlockId());
            itemids.add(bricks.asRegisteredItem().getRegisteredItemId());
            registerRecipe(new ItemStack(bricks.asRegisteredItem().getRegisteredItemId(),8)
                    ,
                    "BBB",
                    "BDB",
                    "BBB",
                    'B', Block.brick,'D', new ItemStack(Item.dyePowder,1,color));
    }}
    }
}
