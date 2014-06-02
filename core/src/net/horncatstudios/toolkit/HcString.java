package net.horncatstudios.toolkit;

import net.horncatstudios.projectpancake.HcLocale;

import java.util.HashMap;

/**
 * Created by Shinichi on 5/30/2014.
 */
public class HcString {

  HashMap<HcLocale.Locale, String> mText;

  public HcString(final String text)   {
    mText = new HashMap<HcLocale.Locale, String>();
    mText.put(HcLocale.Locale.EN, text);
  }

  public HcString(final HcLocale.Locale hcLocale, final String text)   {
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
