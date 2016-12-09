package com.sudoplay.mc.kornoplace.module.noplace;

import com.google.common.eventbus.Subscribe;
import com.sudoplay.mc.kor.spi.IKorModule;
import com.sudoplay.mc.kor.spi.event.internal.OnRegisterEventHandlersEvent;
import com.sudoplay.mc.kornoplace.module.noplace.handler.EventHandlerNoPlace;

/**
 * Created by codetaylor on 12/8/2016.
 */
public class ModuleNoPlace implements
    IKorModule {

  public static final String MODULE_ID = "noplace";

  @Override
  public String getKorModuleId() {
    return ModuleNoPlace.MODULE_ID;
  }

  @Subscribe
  public void onRegisterEventHandlersEvent(OnRegisterEventHandlersEvent event) {
    event.getRegistryService().register(

        EventHandlerNoPlace.class
    );
  }
}
