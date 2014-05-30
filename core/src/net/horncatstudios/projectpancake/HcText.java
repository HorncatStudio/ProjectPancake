package net.horncatstudios.projectpancake;

import java.util.HashMap;

/**
 * Created by Shinichi on 5/30/2014.
 */
public class HcText {

  HashMap<HcLocale.Locale, String> mText;

  public HcText(final String text)
  {
    mText.put(HcLocale.Locale.EN, text);
  }

  public HcText(final HcLocale hcLocale, final String text)
  {
    hcLocale.setLocale(HcLocale.Locale.JP);
    mText.put(hcLocale.getCurrentLocale(), text);
  }

  public String getText()
  {
    return mText.get(HcLocale.Locale.EN);
  }

  public String getText(final HcLocale hcLocale)
  {
    return mText.get(hcLocale.getCurrentLocale());
  }

  public void add(final HcLocale hcLocale, final String text)
  {
    mText.put(hcLocale.getCurrentLocale(), text);
  }
}
