package net.horncatstudios.gameengine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Angelina on 7/3/2014.
 */
public class RectangleSprite {

  // A larger rectangle that will be drawn that will represent the "border"
  Rectangle mBackRectangle;

  Rectangle mForegroundRectangle;

  Texture mDrawableBorder;

  Texture mDrawableForeground;

  public RectangleSprite(float x, float y, float width, float height, float borderWidth) {
    mBackRectangle = new Rectangle(x, y, width, height);
    mForegroundRectangle = new Rectangle(x + borderWidth, y + borderWidth, width - (borderWidth * 2) - 1, height - (borderWidth * 2) - 1);

    Pixmap backgroundPixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
    backgroundPixmap.setColor(Color.BLACK);
    backgroundPixmap.fill();

    mDrawableBorder = new Texture(backgroundPixmap);

    Pixmap foregroundPixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
    foregroundPixmap.setColor(Color.WHITE);
    foregroundPixmap.fill();

    mDrawableForeground = new Texture(foregroundPixmap);
  }

  public void draw(SpriteBatch batch) {
    batch.draw(mDrawableBorder,
        mBackRectangle.x, mBackRectangle.y,
        mBackRectangle.width, mBackRectangle.height);

    batch.draw(mDrawableForeground,
        mForegroundRectangle.x, mForegroundRectangle.y,
        mForegroundRectangle.width, mForegroundRectangle.height);
  }
}
