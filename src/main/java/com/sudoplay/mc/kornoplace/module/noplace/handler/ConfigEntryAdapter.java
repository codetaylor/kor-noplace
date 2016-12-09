package com.sudoplay.mc.kornoplace.module.noplace.handler;

import com.sudoplay.mc.kor.core.log.LoggerService;
import com.sudoplay.mc.kor.core.recipe.ParseResult;
import com.sudoplay.mc.kor.core.recipe.RecipeItemParser;
import com.sudoplay.mc.kor.core.recipe.exception.MalformedRecipeItemException;
import com.sudoplay.mc.kornoplace.module.noplace.config.ConfigEntry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;

/**
 * Created by codetaylor on 12/8/2016.
 */
public class ConfigEntryAdapter {

  private final RecipeItemParser recipeItemParser;
  private LoggerService loggerService;

  public ConfigEntryAdapter(LoggerService loggerService, RecipeItemParser recipeItemParser) {
    this.loggerService = loggerService;
    this.recipeItemParser = recipeItemParser;
  }

  @Nullable
  public ResolvedConfigEntry adapt(String blockString, ConfigEntry entry) {

    try {
      ParseResult blockStringParseResult = this.recipeItemParser.parse(blockString);
      Block block = GameRegistry.findRegistry(Block.class).getValue(new ResourceLocation(blockStringParseResult.getDomain(), blockStringParseResult.getPath()));

      if (block == null) {
        this.loggerService.error("Can't find block for string [%s], skipping", blockString);
        return null;
      }

      String returnedItem = entry.getReturnedItem();
      ItemStack itemStack = null;

      if (returnedItem != null && !returnedItem.isEmpty()) {
        ParseResult itemStringParseResult = this.recipeItemParser.parse(returnedItem);
        Item item = GameRegistry.findRegistry(Item.class).getValue(new ResourceLocation(itemStringParseResult.getDomain(), itemStringParseResult.getPath()));

        if (item == null) {
          this.loggerService.error("Can't find item for string [%s], skipping", returnedItem);
          return null;
        }

        itemStack = new ItemStack(item, 1, itemStringParseResult.getMeta());
      }

      return new ResolvedConfigEntry(
          block,
          blockStringParseResult.getMeta(),
          itemStack,
          entry.getDimensionIdWhiteList(),
          entry.getDimensionIdBlackList(),
          entry.getMessage()
      );

    } catch (MalformedRecipeItemException e) {
      this.loggerService.error("", e);
    }

    return null;
  }

}
