package net.horncatstudios.projectpancake;

import java.util.HashMap;

/**
 * Created by Shinichi on 5/30/2014.
 */
public class HcText {

  HashMap<HcLocale.Locale, String> mText;

  public HcText(final String text)   {
    mText = new HashMap<HcLocale.Locale, String>();
    mText.put(HcLocale.Locale.EN, text);
  }

  public HcText(final HcLocale.Locale hcLocale, final String text)   {
    mText = new HashMap<HcLocale.Locale, String>();
    mText.put(hcLocale, text);
  }

  public String getText()  {
    return mText.get(HcLocale.getCurrentLocale());
  }

  public String getText(final HcLocale.Locale hcLocale) {
    return mText.get(hcLocale);
  }

  public void add(final HcLocale.Locale hcLocale, final String text) {
    mText.put(hcLocale, text);
  }
}
