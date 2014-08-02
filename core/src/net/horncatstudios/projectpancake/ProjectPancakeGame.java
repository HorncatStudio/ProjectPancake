package net.horncatstudios.projectpancake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.horncatstudios.gameengine.ResourceManager;
import net.horncatstudios.gameengine.ScreenManager;

public class ProjectPancakeGame extends Game {
  SpriteBatch batch;

  ResourceManager resourceManager;

  @Override
  public void create() {
    batch = new SpriteBatch();

    Gdx.app.setLogLevel(Gdx.app.LOG_DEBUG);
    HcLocale.setLocale(HcLocale.Locale.EN);

    resourceManager = resourceManager.getInstance();
    resourceManager.prepareManager(this);

    if (Gdx.graphics.getWidth() > 800) {
      resourceManager.fontManager.setOuyaSizeForFonts();
    }

    resourceManager.prepareSharedResources();

    ScreenManager.getInstance().prepareSceneManager(this);
    ScreenManager.getInstance().setScene(ScreenManager.ScreenType.SPLASH);
  }

  @Override
  public void dispose() {
    batch.dispose();
    ResourceManager.getInstance().disposeSharedResources();
  }

  @Override
  public void render() {
    super.render();
  }

  @Override
  public void resize(int width, int height) {
  }

  @Override
  public void pause() {
  }

  @Override
  public void resume() {
  }
}
