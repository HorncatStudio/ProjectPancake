package net.horncatstudios.gameengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import net.horncatstudios.projectpancake.ProjectPancakeGame;

/**
 * Created by Angelina on 7/5/2014.
 */
public abstract class ChildScreen implements IHcScreen {

  private BaseScreen parentScreen;

  public OrthographicCamera camera;
  protected ResourceManager resourcesManager;
  protected ProjectPancakeGame mGame;
  protected ScreenViewport mScreenViewport;

  public ChildScreen(BaseScreen parent) {
    this.resourcesManager = ResourceManager.getInstance();
    this.mGame = this.resourcesManager.mGame;

    camera = new OrthographicCamera();
    camera.setToOrtho(false, 640, 480);
    mScreenViewport = new ScreenViewport(camera);

    createScene();

    this.parentScreen = parent;
  }

  @Override
  public void resize(int width, int height) {
    mScreenViewport.update(width, height);
  }

  public abstract void createScene();

  public void loadChildScreen() {
    ScreenManager.getInstance().setScene(this);
    Gdx.input.setInputProcessor(this);
  }

  public void finish() {
    ScreenManager.getInstance().setScene(parentScreen.getSceneType());
    Gdx.input.setInputProcessor(parentScreen);
  }
}
