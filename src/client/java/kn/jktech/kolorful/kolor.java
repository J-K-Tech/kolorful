package kn.jktech.kolorful;

import com.fox2code.foxloader.loader.ClientMod;
import com.fox2code.foxloader.loader.Mod;
import com.fox2code.foxloader.registry.*;
import kn.jktech.kolorful.blocks.blockDisco;
import net.minecraft.src.game.block.Block;
import net.minecraft.src.game.block.BlockColored;
import net.minecraft.src.game.block.Material;
import net.minecraft.src.game.item.Item;
import net.minecraft.src.game.item.ItemBlockColored;
import net.minecraft.src.game.item.ItemStack;
import net.minecraft.src.game.nbt.NBTTagCompound;
import net.minecraft.src.game.nbt.NBTTagInt;

import java.util.ArrayList;
import java.util.List;

public class kolor extends Mod  implements ClientMod {
    public static List<Integer> blockids=new ArrayList<>();
    public static List<Integer> itemids=new ArrayList<>();
    @Override
    public void onInit() {
        System.out.println("kolorful blocks");


            RegisteredBlock hardclay=registerNewBlock("kolor_hardened_clay",
                    new BlockBuilder().setBlockName("kolor_hardened_clay").setEffectiveTool(RegisteredToolType.PICKAXE)
                            .setBlockHardness(2.0F)
                            .setBlockResistance(10.0F)
                            .setBlockStepSounds(GameRegistry.BuiltInStepSounds.STONE)
                            .setGameBlockProvider(((id, blockBuilder, ext) -> new BlockColored(id,Material.rock,"_hardened_clay")))
            );
            blockids.add(hardclay.getRegisteredBlockId());
            itemids.add(hardclay.asRegisteredItem().getRegisteredItemId());
        for (int color = 0; color < 16; color++) {
        registerRecipe(new ItemStack(hardclay.asRegisteredItem().getRegisteredItemId(),8,color)
        ,
"BBB",
        "BDB",
        "BBB",
                'B', Block.hardenedClay,'D', new ItemStack(Item.dyePowder,1,color));}

            RegisteredBlock bricks=registerNewBlock("kolor_bricks",
                    new BlockBuilder().setBlockName("kolor_bricks").setEffectiveTool(RegisteredToolType.PICKAXE)
                            .setBlockHardness(2.0F)
                            .setBlockResistance(10.0F)
                            .setBlockStepSounds(GameRegistry.BuiltInStepSounds.STONE)
                            .setGameBlockProvider(((id, blockBuilder, ext) -> new BlockColored(id,Material.rock,"_bricks")))
            );


        for (int color = 0; color < 16; color++) {
            if (color!=0){
                NBTTagCompound nbt=new NBTTagCompound();
                nbt.setTag("color",new NBTTagInt(color));
                registerRecipe(new ItemStack(bricks.asRegisteredItem().getRegisteredItemId(),8,color,nbt)
                    ,
                    "BBB",
                    "BDB",
                    "BBB",
                    'B', Block.brick,'D', new ItemStack(Item.dyePowder,1,color));
    }}

        RegisteredBlock disco=registerNewBlock("disco",
                new BlockBuilder().setBlockName("disco").setEffectiveTool(RegisteredToolType.PICKAXE)
                        .setBlockHardness(2.0F)
                        .setBlockResistance(10.0F)
                        .setBlockStepSounds(GameRegistry.BuiltInStepSounds.GLASS)
                        .setGameBlockProvider((id,blockBuilder,ext)->new blockDisco(id,Material.glass,false).setLightValue(1))
        );
        blockids.add(disco.getRegisteredBlockId());
        itemids.add(disco.asRegisteredItem().getRegisteredItemId());

            RegisteredBlock discobase=registerNewBlock("discobase",
                new BlockBuilder().setBlockName("discobase").setEffectiveTool(RegisteredToolType.PICKAXE)
                        .setBlockHardness(2.0F)
                        .setBlockResistance(10.0F)
                        .setBlockStepSounds(GameRegistry.BuiltInStepSounds.GLASS)
                        .setGameBlockProvider((id,blockBuilder,ext)->new blockDisco(id,Material.glass,true).setLightValue(1))
        );
        blockids.add(discobase.getRegisteredBlockId());
        itemids.add(discobase.asRegisteredItem().getRegisteredItemId());
                registerRecipe(new ItemStack(disco.asRegisteredItem().getRegisteredItemId(),1)
                        ,
                        "BBB",
                        "BDB",
                        "BGB",
                        'B', Block.glass,'D', Item.dyePowder,'G',Item.glowstoneDust);
                registerRecipe(new ItemStack(discobase.asRegisteredItem().getRegisteredItemId(),1)
                ,
                "BWB",
                "BDB",
                "BGB",
                'B', Block.glass,'D', Item.dyePowder,'G',Item.glowstoneDust,'W',Block.gear);


                RegisteredBlock stucco=registerNewBlock("kolor_stucco_brick",
                        new BlockBuilder().setBlockName("kolor_stucco_brick").setEffectiveTool(RegisteredToolType.PICKAXE)
                                .setBlockHardness(2.0F)
                                .setBlockResistance(10.0F)
                                .setBlockStepSounds(GameRegistry.BuiltInStepSounds.STONE)
                                .setGameBlockProvider(((id, blockBuilder, ext) -> new BlockColored(id,Material.rock,"_stucco_brick")))
                );
        for (int color = 0; color < 16; color++) {
            NBTTagCompound nbt=new NBTTagCompound();
            nbt.setTag("color",new NBTTagInt(color));
            registerRecipe(new ItemStack(stucco.asRegisteredItem().getRegisteredItemId(),8,color,nbt)
                        ,
                        "BB",
                        "BB",
                        'B', new ItemStack(Block.stucco,1,color));
            }
    }
}
