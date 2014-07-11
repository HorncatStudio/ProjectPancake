package net.horncatstudios.gameengine;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import net.horncatstudios.toolkit.Point;

import java.util.Random;

/**
 * \brief A Starfield class which draws a circles which increase and decrease in size randomly over a square area in
 * indicated length.
 */
public class StarField {

  class Star {
    public int X = 0;
    public int Y = 0;
    public int Size = 0;

    private int mMaxSize = 5;

    private Random mRandom;

    private int INITIAL_MAX_SIZE = 2;
    private int MIN_SIZE = 1;
    private boolean goingUp = true;
    private int range = INITIAL_MAX_SIZE - MIN_SIZE + 1;

    Star(Random random) {
      mRandom = random;
      mMaxSize = mRandom.nextInt(range) + MIN_SIZE;
    }

    void Update() {
      if (Size > mMaxSize)
        goingUp = false;
      else
        goingUp = true;

      if (goingUp)
        Size++;
      else
        Size--;
    }

    void RandomUpdateSize() {
      Size = mRandom.nextInt() % (INITIAL_MAX_SIZE - MIN_SIZE + 1);
    }
  }

  private Point mPosition = new Point(0, 0);
  private ShapeRenderer shapeRenderer = new ShapeRenderer();
  private Random random = new Random();

  private int numberOfStars;
  private final int maxStarSize = 2;
  private final int minStarSize = 0;
  private int starFieldLength = 610;
  private int starFieldHeight = 610;

  private final int frequency = 10;
  private int frequencyCounter = 1;

  Star[] stars = null;


  public StarField(OrthographicCamera camera) {
    shapeRenderer.setColor(Color.WHITE);
    starFieldLength = (int) camera.viewportWidth;
    starFieldHeight = (int) camera.viewportHeight;

    if (starFieldLength > 800) {
      numberOfStars = 1500;
    } else {
      numberOfStars = 500;
    }

    stars = new Star[numberOfStars];

    createStars();
  }

  public StarField() {
    shapeRenderer.setColor(Color.WHITE);
    stars = new Star[numberOfStars];
    createStars();
  }

  private void createStars() {
    int starSizeRange = maxStarSize - minStarSize;

    for (int index = 0; index < numberOfStars; index++) {
      stars[index] = new Star(random);

      stars[index].X = random.nextInt() % (starFieldLength + 1);
      stars[index].Y = random.nextInt() % (starFieldLength + 1);
      stars[index].Size = random.nextInt(starSizeRange) + (minStarSize);
    }
  }


  public void draw(Camera camera) {

    int randomFrequency = (random.nextInt(25) + 1);

    shapeRenderer.setProjectionMatrix(camera.combined);
    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

    for (int index = 0; index < numberOfStars; index++) {
      if (((index + 1) % randomFrequency) == 0)
        stars[index].Update();

      shapeRenderer.circle(stars[index].X, stars[index].Y, stars[index].Size);
    }

    shapeRenderer.end();

  }
}
