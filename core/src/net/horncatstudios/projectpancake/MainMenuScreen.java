package net.horncatstudios.projectpancake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import net.horncatstudios.gameengine.BaseScene;
import net.horncatstudios.gameengine.SceneManager;
import net.horncatstudios.gameengine.StarField;

/**
 * Created by Angelina on 7/18/13.
 */
public class MainMenuScreen extends BaseScene {

  StarField stars = new StarField();
  Label mTitleLabel;
  Label mSubTitleLabel;

  Label mMenuStartLabel;
  Label mMenuQuitLabel;

  private Stage mStage;

  public MainMenuScreen() {
  }

  @Override
  public void createScene() {

    mStage = new Stage();

    mTitleLabel = new Label("Project", resourcesManager.mFontTitleStyle);
    mTitleLabel.setFontScale(1f);
    mTitleLabel.setAlignment(Align.center | Align.bottom);

    mSubTitleLabel = new Label("PANCAKE", resourcesManager.mFontTitleStyle);
    mSubTitleLabel.setFontScale(2f);
    //! Note: Angelina -
    //! Aligning to the bottom actually makes it align to the top of the cell for the label
    mSubTitleLabel.setAlignment(Align.bottom);

    mMenuStartLabel = new Label("Start Game", resourcesManager.mFontMenuStyle);
    mMenuQuitLabel = new Label("Start Game", resourcesManager.mFontMenuStyle);

    Table table = new Table();
    //table.debug(); uncomment for table debugging
    table.setFillParent(true);
    table.setSkin(resourcesManager.mMenuButtonSkin);
    mStage.addActor(table);

    // Adding empty cells to improve spacing for the main menu
    table.add().expand();
    table.row();

    table.add(mTitleLabel).expandX().size(0, 60);
    table.row();

    table.add(mSubTitleLabel).expandX()/*.size(0,60)*/;
    table.row();

    // Adding empty cells to improve spacing for the main menu
    table.add().expand();
    table.row();

    // register the button "start game"
    TextButton startGameButton = new TextButton("Start game", resourcesManager.mMenuButtonSkin);
    startGameButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        SceneManager.getInstance().loadGameScene();
        dispose();
      }
    });
    table.add(startGameButton)/*.size(100, 60)*/.uniform();
    table.row();

    // register the button "quit game"
    TextButton quitGame = new TextButton("Quit game", resourcesManager.mMenuButtonSkin);
    quitGame.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
      }
    });
    table.add(quitGame)/*.size(375, 60)*/.uniform();
    table.row();

    // Adding empty cells to improve spacing for the main menu
    table.add().expand();
    table.row();

    Gdx.input.setInputProcessor(mStage);

  }

  @Override
  public void render(float v) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();

    stars.draw(camera);

    mGame.batch.setProjectionMatrix(camera.combined);

    mGame.batch.begin();
//    mTitleLabel.draw(mGame.batch, 0.0f);
//    mMenuStartLabel.draw(mGame.batch, 0.0f);
//    mMenuQuitLabel.draw(mGame.batch, 0.0f);
//
//    mGame.font.draw(mGame.batch, "Project Pancake", 100, 150);
//    mGame.font.draw(mGame.batch, "Start Game", 100, 100);
//    mGame.font.draw(mGame.batch, "Quit", 100, 50);
    mGame.batch.end();

    // Table.drawDebug(mStage); uncomment for table debuging placement
    mStage.draw();
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
