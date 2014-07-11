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
  private BitmapFont japaneseTextFont = null;

  //region Main Menu Resources32
  private BitmapFont mSharedFont;
  private BitmapFont mLargeSharedFont;
  public Label.LabelStyle MainMenuGameTitleStyle;
  public Label.LabelStyle MainMenuGameTitleStyleBig;
  public Skin MainMenuButtonSkin;
  //endregion

  public void loadMenuResources() {
    SmartFontGenerator fontGenerator = new SmartFontGenerator();

    FileHandle titleMenuFontFile = Gdx.files.internal("font/lovepop.ttf");
    mSharedFont = fontGenerator.createFont(titleMenuFontFile, "titlemenu", 36);
    mLargeSharedFont = fontGenerator.createFont(titleMenuFontFile, "titlemenularge", 64);

    MainMenuGameTitleStyle = new Label.LabelStyle(mSharedFont, Color.CYAN);
    MainMenuGameTitleStyleBig = new Label.LabelStyle(mLargeSharedFont, Color.CYAN);

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
    mLargeSharedFont.dispose();
    MainMenuButtonSkin.dispose();
  }

  public void loadSharedResources() {
    SmartFontGenerator fontGenerator = new SmartFontGenerator();
    FileHandle fontConversation = Gdx.files.local("font/AGENCYR.TTF");
    FileHandle japaneseFontConversation = Gdx.files.local("font/07YasashisaAntique.ttf");

    englishTextFont = fontGenerator.createFont(fontConversation, "conversation", 24);
    japaneseTextFont = fontGenerator.createFont(japaneseFontConversation, "japanese_conversation", 24);

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
