package com.sudoplay.mc.kornoplace.module.noplace.config;

import com.sudoplay.mc.kor.spi.config.json.KorConfigObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codetaylor on 12/8/2016.
 */
public class ConfigEntry extends
    KorConfigObject {

  private List<Integer> dimensionIdWhiteList;
  private List<Integer> dimensionIdBlackList;
  private String returnedItem;
  private String message;

  public ConfigEntry() {
    this.dimensionIdWhiteList = new ArrayList<>();
    this.dimensionIdBlackList = new ArrayList<>();
  }

  public void addDimensionIdToWhiteList(int id) {
    this.dimensionIdWhiteList.add(id);
  }

  public void addDimensionIdToBlackList(int id) {
    this.dimensionIdBlackList.add(id);
  }

  public String getReturnedItem() {
    return returnedItem;
  }

  public void setReturnedItem(String returnedItem) {
    this.returnedItem = returnedItem;
  }

  public List<Integer> getDimensionIdBlackList() {
    return dimensionIdBlackList;
  }

  public List<Integer> getDimensionIdWhiteList() {
    return dimensionIdWhiteList;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
