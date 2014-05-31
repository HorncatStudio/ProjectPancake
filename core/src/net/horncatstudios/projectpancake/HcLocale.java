package net.horncatstudios.projectpancake;

/**
 * Created by Shinichi on 5/30/2014.
 */
public class HcLocale {

  public enum Locale {
    EN,
    JP
  }

  static private Locale currentLocale = Locale.EN;

  static public void setLocale(Locale locale)   {
    currentLocale = locale;
  }

  static public Locale getCurrentLocale()   {
    return currentLocale;
  }
}
