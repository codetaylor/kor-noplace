package com.sudoplay.mc.kornoplace.module.noplace.config;

import com.google.gson.annotations.SerializedName;
import com.sudoplay.mc.kor.spi.config.json.KorConfigObject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by codetaylor on 12/8/2016.
 */
public class Config extends
    KorConfigObject {

  @SerializedName("blocks")
  private Map<String, ConfigEntry> configEntryMap;

  public Config() {
    this.configEntryMap = new LinkedHashMap<>();

    ConfigEntry entry = new ConfigEntry();
    entry.setReturnedItem("minecraft:stone:0");
    entry.addDimensionIdToBlackList(0);
    entry.setMessage("As an example, stone has been blacklisted for dimension 0 (Overworld).");
    this.configEntryMap.put("minecraft:stone:0", entry);
  }

  public Map<String, ConfigEntry> getConfigEntryMap() {
    return this.configEntryMap;
  }
}
