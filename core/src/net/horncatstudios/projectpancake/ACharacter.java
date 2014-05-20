package net.horncatstudios.projectpancake;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.horncatstudios.conversationengine.Emotion;

/**
 * Created by Angelina on 5/18/2014.
 */
public class ACharacter {

  protected Sprite mCharacterSprite;
  protected TextureRegion normal;
  protected TextureRegion sad;
  protected TextureRegion blush;

  public ACharacter(final TextureAtlas atlas) {
    loadTextures(atlas);
    mCharacterSprite = new Sprite(normal);
  }

  public void loadTextures(final TextureAtlas atlas) {
    normal = atlas.findRegion("normal");
    sad = atlas.findRegion("sad");
    blush = atlas.findRegion("blush");
  }

  public void draw(SpriteBatch batch) {
    this.mCharacterSprite.draw(batch);
  }

  public void setBlushTexture() {
    mCharacterSprite.setRegion(blush);
  }

  public void setSadTexture() {
    mCharacterSprite.setRegion(sad);
  }

  public void setNormalTexture() {
    mCharacterSprite.setRegion(normal);
  }

  public void setEmotion(final Emotion emotion) {
    switch (emotion) {
      case CASUAL:
        setNormalTexture();
        break;
      case EMBARRASSED:
        setBlushTexture();
        break;
      case SAD:
        setSadTexture();
        break;
    }
  }

}
