package kn.jktech.kolorful.blocks;

import net.minecraft.src.game.block.Block;
import net.minecraft.src.game.block.BlockColored;
import net.minecraft.src.game.block.Material;
import net.minecraft.src.game.level.World;

import java.util.Random;

import static kn.jktech.kolorful.kolor.blockids;

public class blockDisco extends Block {
    public final boolean base;
    public blockDisco(int id, Material material, boolean base) {
        super(id, material);
        this.base = base;if (base){
        setRequiresSelfNotify();
        this.setTickOnLoad(true);}
    }

    @Override
    public int tickRate() {
        return 7;
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if (this.base){
        world.setBlockMetadataWithNotify(x,y,z, random.nextInt()%15);
        int [][]pos={
                {-1,-1},{-1,0},{-1,1},

                {0,-1},        {0,1},

                {1,-1}, {1,0}, {1,1}
        };
        for (int i = 0; i < 8; i++) {

            if(world.getBlockId(x+pos[i][0],y,z+pos[i][1])==blockids.get(31))world.setBlockMetadataWithNotify(x+pos[i][0],y,z+pos[i][1], random.nextInt()%15);
        }}
    }
}
