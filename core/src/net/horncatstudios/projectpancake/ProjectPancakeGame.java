package net.horncatstudios.projectpancake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.horncatstudios.gameengine.ResourceManager;
import net.horncatstudios.gameengine.SceneManager;

public class ProjectPancakeGame extends Game implements InputProcessor {
  SpriteBatch batch;
  BitmapFont font;

  ResourceManager resourceManager;

  @Override
  public void create() {
    batch = new SpriteBatch();
    font = new BitmapFont(Gdx.files.internal("font/dosgreen.txt"), false);

    resourceManager = resourceManager.getInstance();
    resourceManager.prepareManager(this);
    resourceManager.prepareSharedResources();

    SceneManager.getInstance().setScene(SceneManager.SceneType.SCENE_SPLASH);
    Gdx.input.setInputProcessor(this);
  }


  @Override
  public void dispose() {
    batch.dispose();
    font.dispose();
    ResourceManager.getInstance().disposeSharedresources();
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

  @Override
  public boolean keyDown(int keycode) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean keyTyped(char character) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean scrolled(int amount) {
    // TODO Auto-generated method stub
    return false;
  }
}
