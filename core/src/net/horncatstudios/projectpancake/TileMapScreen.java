package net.horncatstudios.projectpancake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import net.horncatstudios.gameengine.BaseScene;
import net.horncatstudios.gameengine.SceneManager;
import net.horncatstudios.map.PlayerSprite;

/**
 * Created by Angelina on 6/16/2014.
 */
public class TileMapScreen extends BaseScene {

  private OrthogonalTiledMapRenderer mRenderer;
  private PlayerSprite player;

  @Override
  public void createScene() {
    mRenderer = new OrthogonalTiledMapRenderer(resourcesManager.mSchoolMap);
    this.player = new PlayerSprite(new Sprite(new Texture("tileset/marajade_mod.png")),
        (TiledMapTileLayer) resourcesManager.mSchoolMap.getLayers().get(1));
    this.player.setPosition(100, 300);
    Gdx.input.setInputProcessor(this);
  }

  @Override
  public SceneManager.SceneType getSceneType() {
    return null;
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    mRenderer.setView(this.camera);
    mRenderer.render();

    mGame.batch.begin();
    this.player.draw(mGame.batch);
    mGame.batch.end();
  }

  @Override
  public void resize(int width, int height) {

  }

  @Override
  public boolean keyDown(int keycode) {
    switch (keycode) {
      case Input.Keys.UP:
        this.player.moveUp();
        break;
      case Input.Keys.DOWN:
        this.player.moveDown();
        break;
      case Input.Keys.RIGHT:
        this.player.moveRight();
        break;
      case Input.Keys.LEFT:
        this.player.moveLeft();
        break;
      case Input.Keys.ENTER:
      case Input.Keys.BUTTON_A:
        break;
    }

    return true;
  }

  @Override
  public boolean keyUp(int keycode) {
    this.player.stop();
    return true;
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
