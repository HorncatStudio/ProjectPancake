package net.horncatstudios.gameengine;

import net.horncatstudios.projectpancake.HcLocale;

/**
 * Created by Angelina on 7/27/2014.
 */
public class HcDefines {

  public static boolean DebuggingEnabled = false;

  public static float mediumTalkSpeed() {
    if (HcLocale.getCurrentLocale() == HcLocale.Locale.JP) {
      return 0.2f;
    } else {
      return 0.03f;
    }
  }

  public static float fastTalkSpeed() {
    if (HcLocale.getCurrentLocale() == HcLocale.Locale.JP) {
      return 0.03f;
    } else {
      return 0.01f;
    }
  }
}
