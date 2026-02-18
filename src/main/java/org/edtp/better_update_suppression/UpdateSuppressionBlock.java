package org.edtp.better_update_suppression;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;
import xyz.nucleoid.packettweaker.PacketContext;

public class UpdateSuppressionBlock extends Block implements PolymerTexturedBlock {
    public static final BooleanProperty ACTIVED = BooleanProperty.of("actived");

    private static final BlockState POLYMER_INACTIVE = PolymerBlockResourceUtils.requestBlock(
            BlockModelType.FULL_BLOCK,
            PolymerBlockModel.of(Identifier.of("better_update_suppression", "block/update_suppression_block"))
    );

    private static final BlockState POLYMER_ACTIVE = PolymerBlockResourceUtils.requestBlock(
            BlockModelType.FULL_BLOCK,
            PolymerBlockModel.of(Identifier.of("better_update_suppression", "block/update_suppression_block_active"))
    );

    public UpdateSuppressionBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(ACTIVED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVED);
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        return state.get(ACTIVED) ? POLYMER_ACTIVE : POLYMER_INACTIVE;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient()) {
            var active_state = state.get(ACTIVED);
            active_state = !active_state;
            player.playSound(SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, 1, 1);
            world.setBlockState(pos, state.with(ACTIVED, active_state));
            if (active_state) {
                player.sendMessage(Text.literal("update suppression on at: " + pos), true);
            } else {
                player.sendMessage(Text.literal("update suppression off at: " + pos), true);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, @Nullable WireOrientation wireOrientation, boolean notify) {
        if (!world.isClient()) {
            if (state.get(ACTIVED)) {
                throw new StackOverflowError("Intentionally suppressing block update chain at " + pos.toString());
            }
        }

        super.neighborUpdate(state, world, pos, sourceBlock, wireOrientation, notify);
    }
}
