package net.horncatstudios.gameengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import net.horncatstudios.projectpancake.FontResourceManager;
import net.horncatstudios.projectpancake.ProjectPancakeGame;

public class ResourceManager {

  public static final ResourceManager INSTANCE = new ResourceManager();
  public static FontResourceManager fontManager = new FontResourceManager();

  public ProjectPancakeGame mGame;

  public static ResourceManager getInstance() {
    return INSTANCE;
  }

  public static void prepareManager(ProjectPancakeGame game) {
    getInstance().mGame = game;
  }

  public void prepareSharedResources() {
    loadSharedResouces();
  }

  void setScreen(BaseScreen scene) {
    mGame.setScreen(scene);
  }

  //region SharedGameResources
  // public Music conversationMusic;
  // public Sound supriseSound;
  public void loadSharedResouces() {
    // load the drop sound effect and the rain background "music"
    //conversationMusic = Gdx.audio.newMusic(Gdx.files.internal("conversationMusic.mp3"));
    //supriseSound = Gdx.audio.newSound(Gdx.files.internal("supriseSound.wav"));
    fontManager.loadSharedResources();
  }
  public void disposeSharedResources() {
    //supriseSound.dispose();
    //conversationMusic.dispose();
    fontManager.disposeSharedResources();
  }

  //endregion

  //regionSplash Resources
  public Texture ursaRageLogoimage = null;

  public void loadSplashScreen() {
    ursaRageLogoimage = new Texture(Gdx.files.internal("logo.png"));
  }

  public void unloadSplashScreen() {
    ursaRageLogoimage.dispose();
  }
  //endregion

  //region Main Menu Resources
  public void loadMenuResources() {
    fontManager.loadMenuResources();
  }
  public void unloadMenuResources() {
    fontManager.unloadMenuResources();
  }
  //endregion

  //region Tile Map resources
  public TiledMap mSchoolMap = null;

  public void loadSchoolMapResources() {
    TmxMapLoader loader = new TmxMapLoader();
    mSchoolMap = loader.load("basic_map/classRoomBeta.tmx");
  }
  public void unloadSchoolMapResources() {
    mSchoolMap.dispose();
  }
  //endregion

  //region Conversation Level Resources
  public Texture schoolBackground;
  public TextureAtlas dorianImages;

  public void loadConversationResources() {
    Gdx.app.log("touch", "Loading game resources");
    schoolBackground = new Texture(Gdx.files.internal("classroom.png"));
    dorianImages = new TextureAtlas(Gdx.files.internal("dorian/dorian.atlas"));
  }

  public void unloadConversationResources() {
    schoolBackground.dispose();
    dorianImages.dispose();
  }
  //endregion

  //region Map Tutorial Resources
  public Texture worldMap;

  public void loadMapTutorialResources() {
    worldMap = new Texture(Gdx.files.internal("world-map-pokemon.png"));
  }
  public void unloadMapTutorialResources() {
    worldMap.dispose();
  }

  //endregion

  //region Phone Tutorial Resources
  public TextureRegionDrawable contactsIcon;
  public TextureRegionDrawable phoneIcon;
  public TextureRegionDrawable settingsIcon;
  public TextureRegionDrawable mapIcon;
  public TextureRegionDrawable socialNetworkIcon;
  public TextureRegionDrawable messageIcon;

  public ImageTextButton.ImageTextButtonStyle contactsStyle;
  public ImageTextButton.ImageTextButtonStyle phoneStyle;
  public ImageTextButton.ImageTextButtonStyle settingsStyle;
  public ImageTextButton.ImageTextButtonStyle mapStyle;
  public ImageTextButton.ImageTextButtonStyle socialNetworkStyle;
  public ImageTextButton.ImageTextButtonStyle messageStyle;

  public void loadPhoneResources() {
    contactsIcon = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("phone_assets/BOOK.png"))));
    phoneIcon = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("phone_assets/PHONE.png"))));
    settingsIcon = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("phone_assets/SETTINGS.png"))));
    mapIcon = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("phone_assets/WORLD.png"))));
    socialNetworkIcon = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("phone_assets/TWEET.png"))));
    messageIcon = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("phone_assets/MAIL.png"))));

    contactsStyle = new ImageTextButton.ImageTextButtonStyle(contactsIcon, contactsIcon, contactsIcon, fontManager.conversationFont);
    phoneStyle = new ImageTextButton.ImageTextButtonStyle(phoneIcon, phoneIcon, phoneIcon, fontManager.conversationFont);
    settingsStyle = new ImageTextButton.ImageTextButtonStyle(settingsIcon, settingsIcon, settingsIcon, fontManager.conversationFont);
    mapStyle = new ImageTextButton.ImageTextButtonStyle(mapIcon, mapIcon, mapIcon, fontManager.conversationFont);
    socialNetworkStyle = new ImageTextButton.ImageTextButtonStyle(socialNetworkIcon, socialNetworkIcon, socialNetworkIcon, fontManager.conversationFont);
    messageStyle = new ImageTextButton.ImageTextButtonStyle(messageIcon, messageIcon, messageIcon, fontManager.conversationFont);
  }

  public void unloadPhoneResources() {

  }
  //endregion


}
