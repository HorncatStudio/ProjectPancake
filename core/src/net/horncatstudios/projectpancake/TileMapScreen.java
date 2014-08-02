package net.horncatstudios.projectpancake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import net.horncatstudios.gameengine.BaseScreen;
import net.horncatstudios.gameengine.ScreenManager;
import net.horncatstudios.map.PlayerSprite;

/**
 * Created by Angelina on 6/16/2014.
 */
public class TileMapScreen extends BaseScreen {

  private OrthogonalTiledMapRenderer mRenderer;
  private PlayerSprite player;
  private ShapeRenderer shapeRendererForDebugging;

  int mTileMapWidth;
  int mTileMapHeight;
  int mMapWidth;
  int mMapHeight;

  boolean debugingEnabled = false;

  @Override
  public void createScene() {
    mRenderer = new OrthogonalTiledMapRenderer(resourcesManager.mSchoolMap);
    this.player = new PlayerSprite(new Sprite(new Texture("tileset/marajade_mod.png")),
        (TiledMapTileLayer) resourcesManager.mSchoolMap.getLayers().get(1));
    shapeRendererForDebugging = new ShapeRenderer();
    this.player.setPosition(132, 300);
    Gdx.input.setInputProcessor(this);

    mTileMapHeight = resourcesManager.mSchoolMap.getProperties().get("tileheight", Integer.class);
    mTileMapWidth = resourcesManager.mSchoolMap.getProperties().get("tilewidth", Integer.class);
    mMapWidth = resourcesManager.mSchoolMap.getProperties().get("width", Integer.class) * mTileMapWidth;
    mMapHeight = resourcesManager.mSchoolMap.getProperties().get("height", Integer.class) * mTileMapHeight;

  }

  @Override
  public ScreenManager.ScreenType getSceneType() {
    return null;
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    mRenderer.setView(this.camera);
    mRenderer.render();

    if (this.debugingEnabled) {
      shapeRendererForDebugging.setProjectionMatrix(this.camera.combined);
      shapeRendererForDebugging.begin(ShapeRenderer.ShapeType.Line);
      for (int x = 0; x < mMapWidth; x += mTileMapWidth)
        shapeRendererForDebugging.line(x, 0, x, mMapHeight);

      for (int y = 0; y < mMapHeight; y += mTileMapHeight)
        shapeRendererForDebugging.line(0, y, mMapWidth, y);
      shapeRendererForDebugging.end();
    }

    mGame.batch.begin();
    this.player.draw(mGame.batch);
    mGame.batch.end();
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
    switch (keycode) {
      case Input.Keys.UP:
      case Input.Keys.DOWN:
        this.player.stopUpDown();
        break;
      case Input.Keys.RIGHT:
      case Input.Keys.LEFT:
        this.player.stopLeftRight();
        break;
      case Input.Keys.ENTER:
      case Input.Keys.BUTTON_A:
        break;
    }
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
