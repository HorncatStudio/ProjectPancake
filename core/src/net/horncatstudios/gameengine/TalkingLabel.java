package net.horncatstudios.gameengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import net.horncatstudios.gameengine.Label;
import net.horncatstudios.toolkit.HcString;
import net.horncatstudios.toolkit.Point;

/**
 * Created by SeriousHornCat on 7/1/2014.
 */
public class TalkingLabel extends Label {
  private float talkSpeed = 0.02f;
  private int textIndexCounter = 0;
  private float timeDeltaCounter = 0.0f;
  private String displayText;

  TalkingLabel( HcString text, Point point ) {
    super("", point);
    displayText = text.getText();
  }

  public void setText( String text ) {
    displayText = text;
    textIndexCounter = 0;
  }

  public void draw( SpriteBatch batch, BitmapFont font, float timeDelta ) {
    if( textIndexCounter != displayText.length() ) {
      timeDeltaCounter += timeDelta;
      Gdx.app.log("touch", "Updated timeDeltaCounter: " + timeDeltaCounter );
      if( timeDeltaCounter > talkSpeed )
      {
        textIndexCounter++;
        Text = displayText.substring(0, textIndexCounter);
        Gdx.app.log("touch", "Displaying the following text for state: " + Text + " For index: " + textIndexCounter);
        timeDeltaCounter = 0.0f;
      }
    }

    super.draw(batch, font);
  }
}
