package net.horncatstudios.projectpancake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import net.horncatstudios.gameengine.BaseScene;
import net.horncatstudios.gameengine.Label;
import net.horncatstudios.gameengine.SceneManager;
import net.horncatstudios.toolkit.Point;

/**
 * Created by Angelina on 5/20/2014.
 */
public class EndGameScreen extends BaseScene {

  private Label theEnd;

  @Override
  public void createScene() {
    theEnd = new Label("THE END", new Point(300, 300));
  }

  @Override
  public SceneManager.SceneType getSceneType() {
    return null;
  }

  @Override
  public boolean keyDown(int keycode) {
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    return false;
  }

  @Override
  public boolean keyTyped(char character) {
    return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
    return false;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
    return false;
  }

  @Override
  public boolean scrolled(int amount) {
    return false;
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();

    mGame.batch.setProjectionMatrix(camera.combined);

    mGame.batch.begin();
    theEnd.draw(mGame.batch, resourcesManager.mFontTitleStyle.font);
    mGame.batch.end();
  }

  @Override
  public void resize(int width, int height) {

  }

  @Override
  public void show() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void dispose() {

  }
}
