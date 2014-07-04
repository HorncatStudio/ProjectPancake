package net.horncatstudios.phoneengine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import net.horncatstudios.gameengine.RectangleSprite;

/**
 * Created by Angelina on 7/3/2014.
 */
public class Phone {
  private RectangleSprite mRectangleSprite;

  public Phone(OrthographicCamera camera) {
    mRectangleSprite = new RectangleSprite(50.0f, 0.0f, 300.0f, camera.viewportHeight, 2.0f);

  }

}
