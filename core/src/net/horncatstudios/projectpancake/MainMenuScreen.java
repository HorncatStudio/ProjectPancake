package net.horncatstudios.projectpancake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import net.horncatstudios.gameengine.BaseScreen;
import net.horncatstudios.gameengine.MainMenu;
import net.horncatstudios.gameengine.ScreenManager;
import net.horncatstudios.gameengine.StarField;

/**
 * Created by Angelina on 7/18/13.
 */
public class MainMenuScreen extends BaseScreen implements ProjectPancakeMenuActions {

  StarField stars = new StarField();
  MainMenu mMenu;

  public MainMenuScreen() {
  }

  @Override
  public void createScene() {
    mMenu = new MainMenu(resourcesManager.fontManager, this);
    Gdx.input.setInputProcessor(mMenu);
  }

  @Override
  public void render(float v) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    camera.update();
    stars.draw(camera);

    mGame.batch.setProjectionMatrix(camera.combined);

    mGame.batch.begin();
    mGame.batch.end();

    mMenu.draw();
  }


  @Override
  public void StartGame() {
    ScreenManager.getInstance().loadConversationScene();
    this.dispose();
  }

  @Override
  public void QuitGame() {
    this.dispose();
  }

  @Override
  public void resize(int i, int i2) {
    camera.viewportHeight = i;
    camera.viewportWidth = i2;
    camera.update();
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
    this.resourcesManager.unloadMenuResources();
  }

  @Override
  public ScreenManager.ScreenType getSceneType() {
    return ScreenManager.ScreenType.TITLE_MENU;
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
