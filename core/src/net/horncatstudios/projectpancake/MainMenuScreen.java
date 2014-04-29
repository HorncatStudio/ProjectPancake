package net.horncatstudios.projectpancake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.horncatstudios.gameengine.BaseScene;
import net.horncatstudios.gameengine.SceneManager;
import net.horncatstudios.gameengine.StarField;

/**
 * Created by Angelina on 7/18/13.
 */
public class MainMenuScreen extends BaseScene {

  StarField stars = new StarField();
  Label mTitleLabel;
  Label mMenuStartLabel;
  Label mMenuQuitLabel;

  public MainMenuScreen() {
  }

  @Override
  public void createScene() {
    mTitleLabel = new Label("Project Pancake", resourcesManager.mFontTitleStyle);
    mTitleLabel.setFontScale(10, 10);

    mMenuStartLabel = new Label("Start Game", resourcesManager.mFontMenuStyle);
    mMenuQuitLabel = new Label("Start Game", resourcesManager.mFontMenuStyle);
  }

  @Override
  public void render(float v) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();

    stars.draw(camera);

    mGame.batch.setProjectionMatrix(camera.combined);

    mGame.batch.begin();
    mTitleLabel.draw(mGame.batch, 0.0f);
    mMenuStartLabel.draw(mGame.batch, 0.0f);
    mMenuQuitLabel.draw(mGame.batch, 0.0f);

    mGame.font.draw(mGame.batch, "Project Pancake", 100, 150);
    mGame.font.draw(mGame.batch, "Start Game", 100, 100);
    mGame.font.draw(mGame.batch, "Quit", 100, 50);
    mGame.batch.end();

    if (Gdx.input.isTouched()) {
      SceneManager.getInstance().loadGameScene();
      dispose();
    }
  }

  @Override
  public void resize(int i, int i2) {

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

  @Override
  public SceneManager.SceneType getSceneType() {
    return SceneManager.SceneType.SCENE_MENU;
  }

  @Override
  public boolean keyDown(int i) {
    return false;
  }

  @Override
  public boolean keyUp(int i) {
    return false;
  }

  @Override
  public boolean keyTyped(char c) {
    return false;
  }

  @Override
  public boolean touchDown(int i, int i2, int i3, int i4) {
    return false;
  }

  @Override
  public boolean touchUp(int i, int i2, int i3, int i4) {
    return false;
  }

  @Override
  public boolean touchDragged(int i, int i2, int i3) {
    return false;
  }

  @Override
  public boolean mouseMoved(int i, int i2) {
    return false;
  }

  @Override
  public boolean scrolled(int i) {
    return false;
  }
}
