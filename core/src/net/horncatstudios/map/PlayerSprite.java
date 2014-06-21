package net.horncatstudios.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

/**
 * \todo write class documentation
 */
public class PlayerSprite extends Sprite {

  private float speed = 20;
  private Vector2 velocity = new Vector2(0, 0);

  private TiledMapTileLayer mCollisionLayer;

  public PlayerSprite(Sprite sprite, TiledMapTileLayer collisionLayer) {
    super(sprite);
    this.mCollisionLayer = collisionLayer;
  }

  public void update(final float delta) {
    if (velocity.y > speed)
      velocity.y = speed;
    else if (velocity.y < -speed)
      velocity.y = -speed;

    // save old position
    float oldX = getX(), oldY = getY();
    boolean collisionX = false, collisionY = false;

    // move on x
    setX(getX() + velocity.x * delta);

    if (velocity.x < 0) // going left
      collisionX = collidesLeft();
    else if (velocity.x > 0) // going right
      collisionX = collidesRight();

    // react to x collision
    if (collisionX) {
      setX(oldX);
      velocity.x = 0;
    }

    // move on y
    setY(getY() + velocity.y * delta * 5f);

    if (velocity.y < 0) // going down
      collisionY = collidesBottom();
    else if (velocity.y > 0) // going up
      collisionY = collidesTop();

    // react to y collision
    if (collisionY) {
      setY(oldY);
      velocity.y = 0;
    }
  }

  public void draw(SpriteBatch spriteBatch) {
    update(Gdx.graphics.getDeltaTime());
    super.draw(spriteBatch);
  }

  public boolean collidesRight() {
    for (float step = 0; step < getHeight(); step += this.mCollisionLayer.getTileHeight() / 2)
      if (isCellBlocked(getX() + getWidth(), getY() + step))
        return true;
    return false;
  }

  public boolean collidesLeft() {
    for (float step = 0; step < getHeight(); step += this.mCollisionLayer.getTileHeight() / 2)
      if (isCellBlocked(getX(), getY() + step))
        return true;
    return false;
  }

  public boolean collidesTop() {
    for (float step = 0; step < getWidth(); step += this.mCollisionLayer.getTileWidth() / 2)
      if (isCellBlocked(getX() + step, getY() + getHeight()))
        return true;
    return false;

  }

  public boolean collidesBottom() {
    for (float step = 0; step < getWidth(); step += this.mCollisionLayer.getTileWidth() / 2)
      if (isCellBlocked(getX() + step, getY()))
        return true;
    return false;
  }

  public void moveRight() {
    velocity.x = speed * 2;
  }

  public void moveLeft() {
    velocity.x = -speed * 2;
  }

  public void moveUp() {
    velocity.y = speed;
  }

  public void moveDown() {
    velocity.y = -speed;
  }

  public void stopLeftRight() {
    velocity.x = 0;
  }

  public void stopUpDown() {
    velocity.y = 0;
  }

  private boolean isCellBlocked(final float x, final float y) {
    Cell cell = this.mCollisionLayer.getCell((int) (x / this.mCollisionLayer.getTileWidth()), (int) (y / this.mCollisionLayer.getTileHeight()));
    return ((null != cell) && (cell.getTile() != null) && cell.getTile().getProperties().containsKey("blocked"));
  }
}

