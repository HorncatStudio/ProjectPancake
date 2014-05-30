package net.horncatstudios.projectpancake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by Shinichi on 5/30/2014.
 */
public class FontResourceManager {
  public BitmapFont conversationFont;

  private BitmapFont englishTextFont = new BitmapFont(Gdx.files.internal("font/PW403.fnt"), Gdx.files.internal("font/PW403.png"), false);
  private BitmapFont japaneseTextFont = new BitmapFont(Gdx.files.internal("font/japanese.fnt"), Gdx.files.internal("font/japanese.png"), false);

  public void chageLocale(HcLocale.Locale locale)
  {
    if (locale == HcLocale.Locale.EN)
    {
      conversationFont = englishTextFont;
    }
    else if (locale == HcLocale.Locale.JP)
    {
      conversationFont = japaneseTextFont;
    }
  }

}
