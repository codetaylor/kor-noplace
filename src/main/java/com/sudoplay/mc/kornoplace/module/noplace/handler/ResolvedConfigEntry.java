package com.sudoplay.mc.kornoplace.module.noplace.handler;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by codetaylor on 12/8/2016.
 */
public class ResolvedConfigEntry {

  private Block block;
  private int meta;
  private ItemStack returnedItemStack;
  private List<Integer> whiteList;
  private List<Integer> blackList;
  private String message;

  public ResolvedConfigEntry(
      Block block,
      int meta,
      ItemStack returnedItemStack,
      List<Integer> whiteList,
      List<Integer> blackList,
      String message
  ) {
    this.block = block;
    this.meta = meta;
    this.returnedItemStack = returnedItemStack;
    this.whiteList = whiteList;
    this.blackList = blackList;
    this.message = message;
  }

  public Block getBlock() {
    return block;
  }

  public int getMeta() {
    return meta;
  }

  public ItemStack getReturnedItemStack() {
    return returnedItemStack;
  }

  public List<Integer> getWhiteList() {
    return whiteList;
  }

  public List<Integer> getBlackList() {
    return blackList;
  }

  public String getMessage() {
    return message;
  }
}
