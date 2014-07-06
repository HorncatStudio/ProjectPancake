package net.horncatstudios.projectpancake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import org.jrenner.smartfont.SmartFontGenerator;

/**
 * Created by Shinichi on 5/30/2014.
 */
public class FontResourceManager {
  public BitmapFont conversationFont = null;

  private BitmapFont englishTextFont = null;
  private BitmapFont japaneseTextFont = new BitmapFont(Gdx.files.internal("font/moreJapanese.fnt"));

  //region Main Menu Resources
  private BitmapFont mSharedFont;
  public Label.LabelStyle MainMenuGameTitleStyle;
  public Skin MainMenuButtonSkin;
  //endregion

  public void loadMenuResources() {
//    mSharedFont = new BitmapFont(Gdx.files.internal("font/PW403.fnt"), false);
    mSharedFont = new BitmapFont(Gdx.files.internal("font/lovepop.fnt"));
    MainMenuGameTitleStyle = new Label.LabelStyle(mSharedFont, Color.CYAN);

    // Sets the background of the Text Button - must be set
    Pixmap backgroundTexture = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
    backgroundTexture.setColor(Color.RED);
    backgroundTexture.fill();

    MainMenuButtonSkin = new Skin();
    MainMenuButtonSkin.add("white", new Texture(backgroundTexture));

    TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
    style.checkedFontColor = Color.PINK;
    style.fontColor = Color.WHITE;
    style.font = mSharedFont;
    MainMenuButtonSkin.add("default", style);
  }

  public void unloadMenuResources() {
    mSharedFont.dispose();
    MainMenuButtonSkin.dispose();
  }

  public void loadSharedResources() {
    // SmartFontGenerator fontGenerator = new SmartFontGenerator();
    // FileHandle fontConversation = Gdx.files.local("font/AGENCYR.TTF");

//    englishTextFont = fontGenerator.createFont(fontConversation, "conversation", 24);
    englishTextFont = new BitmapFont(Gdx.files.local("font/Agency24.fnt"));
    japaneseTextFont = new BitmapFont(Gdx.files.internal("font/moreJapanese.fnt"));

    if (HcLocale.getCurrentLocale() == HcLocale.Locale.EN) {
      conversationFont = englishTextFont;
    } else {
      conversationFont = japaneseTextFont;
    }
  }

  public void disposeSharedResources() {
    englishTextFont.dispose();
    japaneseTextFont.dispose();
  }

  public void updateLocale(final HcLocale.Locale locale) {
    if (locale == HcLocale.Locale.EN) {
      conversationFont = englishTextFont;
    } else if (locale == HcLocale.Locale.JP) {
      conversationFont = japaneseTextFont;
    }
  }

}
