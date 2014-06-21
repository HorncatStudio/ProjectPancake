package net.horncatstudios.map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

/**
 * \todo write class documentation
 */
public class PlayerSprite extends Sprite {

  //! movement velocity
  private Vector2 veloctiy = new Vector2(0, 0);

  // private float speed = 60 * 2;
  // private float gravity = 60 * 1.8f;

  private TiledMapTileLayer mCollisionLayer;

  public PlayerSprite(Sprite sprite, TiledMapTileLayer collisionLayer) {
    super(sprite);
    this.mCollisionLayer = collisionLayer;
  }

  public void draw(SpriteBatch spriteBatch) {
    update(0);
    super.draw(spriteBatch);
  }

  public void moveRight() {
    veloctiy.x = 1;
  }

  public void moveLeft() {
    veloctiy.x = -1;
  }

  public void moveUp() {
    veloctiy.y = 1;
  }

  public void moveDown() {
    veloctiy.y = -1;
  }

  public void stop() {
    veloctiy.x = 0;
    veloctiy.y = 0;
  }

  public void update(float delta) {
    // Apply the gravity
//
//    if (veloctiy.y > speed)
//      veloctiy.y = speed;
//    else if (veloctiy.y < speed)
//      veloctiy.y = -speed;

    // save old position
    float oldX = getX();
    float oldY = getY();

    boolean collidedX = false;
    boolean collidedY = false;

    // move on x
    setX(getX() + veloctiy.x);

    // the left and right direction
    if (veloctiy.x < 0) {
      collidedX = this.isTopLeftTileBlocked(getX(), getY());

      if (!collidedX)
        collidedX = this.isLeftTileBlocked(getX(), getY());

      if (!collidedX)
        collidedX = this.isBottomLeftTileBlocked(getX(), getY());

    } else if (veloctiy.x > 0) {
      collidedX = this.isTopRightTileBlocked(getX(), getY());

      if (!collidedX)
        collidedY = this.isRightTileBlocked(getX(), getY());

      if (!collidedX)
        collidedY = this.isBottomRightTileBlocked(getX(), getY());
    }

    if (collidedX)
      setX(oldX);


    // move on y
    setY(getY() + veloctiy.y);

    // up/down direction
    if (veloctiy.y < 0) {
      collidedY = this.isTopLeftTileBlocked(getX(), getY());

      if (!collidedY)
        collidedY = this.isTopTileBlocked(getX(), getY());

      if (!collidedY)
        collidedY = this.isTopRightTileBlocked(getX(), getY());

    } else if (veloctiy.y > 0) {
      collidedY = this.isBottomLeftTileBlocked(getX(), getY());

      if (!collidedY)
        collidedY = this.isBottomTileBlocked(getX(), getY());

      if (!collidedY)
        collidedY = this.isBottomRightTileBlocked(getX(), getY());
    }

    if (collidedY)
      setY(oldY);
  }

  private boolean isTopLeftTileBlocked(final float x, final float y) {
    return isCellBlocked(x, y + getHeight());
  }

  private boolean isLeftTileBlocked(final float x, final float y) {
    return isCellBlocked(x, (y + getHeight() / 2));
  }

  private boolean isBottomLeftTileBlocked(final float x, final float y) {
    return isCellBlocked(x, y);
  }

  private boolean isTopRightTileBlocked(final float x, final float y) {
    return isCellBlocked(x + getWidth(), y + getHeight());
  }

  private boolean isRightTileBlocked(final float x, final float y) {
    return isCellBlocked(x + getWidth(), y + getHeight() / 2);
  }

  private boolean isBottomRightTileBlocked(final float x, final float y) {
    return isCellBlocked(x + getWidth(), y);
  }

  private boolean isTopTileBlocked(final float x, final float y) {
    return isCellBlocked(x + getWidth() / 2, y + getHeight());
  }

  private boolean isBottomTileBlocked(final float x, final float y) {
    return isCellBlocked(x + getWidth() / 2, y);
  }

  private boolean isCellBlocked(final float x, final float y) {
    Cell cell = this.mCollisionLayer.getCell((int) (x / this.mCollisionLayer.getTileWidth()), (int) (y / this.mCollisionLayer.getTileHeight()));
    return ((null != cell) && (cell.getTile() != null) && cell.getTile().getProperties().containsKey("blocked"));
  }
}

