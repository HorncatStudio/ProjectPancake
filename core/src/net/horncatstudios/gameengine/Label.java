package net.horncatstudios.gameengine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.horncatstudios.toolkit.Point;

/**
 * Created by Angelina on 8/3/13.
 */
public class Label {
  public String Text;
  private Point mPosition = new Point(0, 0);
  private boolean mChecked;
  public Label(String text, Point position) {
    Text = text;
    mPosition = position;
    mChecked = false;
  }

  public void setPosition(float x, float y) {
    mPosition = new Point((int) x, (int) y);
  }

  public void setChecked(final boolean checked) {
    this.mChecked = checked;
  }

  public void draw(SpriteBatch batch, BitmapFont font) {
    Color oldColor = font.getColor();
    if (this.mChecked) {
      font.setColor(Color.DARK_GRAY);
    }

    font.draw(batch, Text, mPosition.X, mPosition.Y);

    if (this.mChecked) {
      font.setColor(oldColor);
    }
  }
}
