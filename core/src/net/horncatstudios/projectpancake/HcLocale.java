package net.horncatstudios.projectpancake;

/**
 * Created by Shinichi on 5/30/2014.
 */
public class HcLocale {
  public enum Locale {EN, JP}

  private Locale currentLocale;

  public void setLocale(Locale locale)
  {
    currentLocale = locale;
  }

  public Locale getCurrentLocale()
  {
    return currentLocale;
  }
}
