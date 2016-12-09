package com.sudoplay.mc.kornoplace.module.noplace.handler;

import com.sudoplay.mc.kor.core.recipe.RecipeItemParser;
import com.sudoplay.mc.kor.spi.Kor;
import com.sudoplay.mc.kor.spi.event.KorForgeEventSubscriber;
import com.sudoplay.mc.kor.spi.registry.injection.KorInject;
import com.sudoplay.mc.kor.spi.registry.injection.KorJsonConfig;
import com.sudoplay.mc.kor.spi.registry.provider.KorInitStrategyProvider;
import com.sudoplay.mc.kor.spi.registry.strategy.KorInitStrategy;
import com.sudoplay.mc.kornoplace.module.noplace.config.Config;
import com.sudoplay.mc.kornoplace.module.noplace.config.ConfigEntry;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by codetaylor on 12/8/2016.
 */
public class EventHandlerNoPlace extends
    KorForgeEventSubscriber implements
    KorInitStrategyProvider {

  private final ConfigEntryAdapter configEntryAdapter;
  private Map<Block, ResolvedConfigEntry> resolvedConfigEntryMap;
  private Config config;

  @KorInject
  public EventHandlerNoPlace(
      Kor kor,
      @KorJsonConfig(file = "config.json") Config config
  ) {
    this.config = config;
    this.configEntryAdapter = new ConfigEntryAdapter(kor.getLoggerService(), new RecipeItemParser());
    this.resolvedConfigEntryMap = new HashMap<>();
  }

  @Override
  public KorInitStrategy getInitStrategy() {
    return kor -> resolveConfigEntries();
  }

  private void resolveConfigEntries() {
    this.resolvedConfigEntryMap.clear();
    Map<String, ConfigEntry> configEntryMap = this.config.getConfigEntryMap();

    for (Map.Entry<String, ConfigEntry> entry : configEntryMap.entrySet()) {

      String blockString = entry.getKey();
      ConfigEntry configEntry = entry.getValue();

      ResolvedConfigEntry resolvedConfigEntry = this.configEntryAdapter.adapt(blockString, configEntry);

      if (resolvedConfigEntry != null) {
        this.resolvedConfigEntryMap.put(resolvedConfigEntry.getBlock(), resolvedConfigEntry);
      }
    }
  }

  @SubscribeEvent
  public void onBlockPlaceEvent(BlockEvent.PlaceEvent event) {

    EntityPlayer player = event.getPlayer();
    World world = player.getEntityWorld();

    if (world.isRemote) {
      return;
    }

    ItemStack itemStack = event.getItemInHand();
    BlockSnapshot blockSnapshot = event.getBlockSnapshot();
    BlockPos pos = blockSnapshot.getPos();
    IBlockState blockState = blockSnapshot.getCurrentBlock();
    Block block = blockState.getBlock();
    int meta = block.getMetaFromState(blockState);

    ResolvedConfigEntry resolvedConfigEntry = this.resolvedConfigEntryMap.get(block);

    if (resolvedConfigEntry == null) {
      return;
    }

    if (resolvedConfigEntry.getMeta() != meta) {
      return;
    }

    List<Integer> whiteList = resolvedConfigEntry.getWhiteList();
    List<Integer> blackList = resolvedConfigEntry.getBlackList();
    int dimension = player.dimension;

    if (!whiteList.isEmpty()) {

      if (whiteList.contains(dimension)) {
        return;
      }
    }

    if (!blackList.isEmpty()) {

      if (!blackList.contains(dimension)) {
        return;
      }
    }

    if (itemStack != null && itemStack.stackSize > 0) {
      itemStack.stackSize -= 1;

      ItemStack returnedItemStack = resolvedConfigEntry.getReturnedItemStack();

      if (returnedItemStack != null) {
        InventoryHelper.spawnItemStack(
            world,
            pos.getX(),
            pos.getY(),
            pos.getZ(),
            returnedItemStack.copy()
        );

      } else {
        block.dropBlockAsItem(world, pos, blockState, 0);
      }

      String message = resolvedConfigEntry.getMessage();

      if (message != null && !message.isEmpty()) {
        player.addChatComponentMessage(new TextComponentString(message));
      }

      event.setCanceled(true);
    }
  }
}
