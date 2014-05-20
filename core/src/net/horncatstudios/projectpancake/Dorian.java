package net.horncatstudios.projectpancake;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.horncatstudios.conversationengine.Relationship;

/**
 * Created by Angelina on 5/18/2014.
 */
public class Dorian extends ACharacter {

  private Relationship mRelationship;

  public Dorian(final TextureAtlas atlas) {
    super(atlas);
    mRelationship = new Relationship();
  }
}
