package net.horncatstudios.projectpancake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.horncatstudios.gameengine.ResourceManager;
import net.horncatstudios.gameengine.SceneManager;

public class ProjectPancakeGame extends Game {
  SpriteBatch batch;

  ResourceManager resourceManager;

  @Override
  public void create() {
    batch = new SpriteBatch();

    HcLocale.setLocale(HcLocale.Locale.JP);

    resourceManager = resourceManager.getInstance();
    resourceManager.prepareManager(this);
    resourceManager.prepareSharedResources();

    SceneManager.getInstance().setScene(SceneManager.SceneType.SCENE_SPLASH);
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
