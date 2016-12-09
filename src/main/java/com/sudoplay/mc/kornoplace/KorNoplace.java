package com.sudoplay.mc.kornoplace;

import com.sudoplay.mc.kor.spi.Kor;
import com.sudoplay.mc.kor.spi.registry.ForgeEventListener;
import com.sudoplay.mc.kornoplace.module.noplace.ModuleNoPlace;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by codetaylor on 12/8/2016.
 */
@Mod(
    modid = KorNoPlace.MOD_ID,
    version = KorNoPlace.VERSION,
    name = KorNoPlace.NAME
    //@@DEPENDENCIES@@
)
@ForgeEventListener
public class KorNoPlace extends
    Kor {

  public static final String MOD_ID = "ctkornoplace";
  public static final String VERSION = "@@VERSION@@";
  public static final String NAME = "CTKor NoPlace";
  public static final double JSON_CONFIGS_VERSION = 1.0;

  @SuppressWarnings("unused")
  @Mod.Instance
  public static KorNoPlace INSTANCE;

  @Override
  public String getModId() {
    return MOD_ID;
  }

  @Override
  public String getModVersion() {
    return VERSION;
  }

  @Override
  public String getModName() {
    return NAME;
  }

  @Override
  public double getJsonConfigsVersion() {
    return JSON_CONFIGS_VERSION;
  }

  @Override
  @Mod.EventHandler
  protected void onPreInitialization(FMLPreInitializationEvent event) {

    this.registerModules(
        new ModuleNoPlace()
    );

    super.onPreInitialization(event);
  }

  @Override
  @Mod.EventHandler
  protected void onInitialization(FMLInitializationEvent event) {
    super.onInitialization(event);
  }

  @Override
  @Mod.EventHandler
  protected void onPostInitialization(FMLPostInitializationEvent event) {
    super.onPostInitialization(event);
  }
}
