package net.horncatstudios.gameengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Angelina on 7/6/2014.
 */
public class TalkingLabelDecorator {
  Label mLabel;
  private int textIndexCounter = 0;
  private float timeDeltaCounter = 0.0f;
  private String mDisplayText;

  static final float MEDIUM_SPEED = 0.02f;
  private float mTalkSpeed = MEDIUM_SPEED;

  TalkingLabelDecorator(Label label) {
    this.mLabel = label;
    this.setText(this.mLabel.getText().toString());
  }

  public void setText(String text) {
    this.mLabel.setText("");
    this.mDisplayText = text;
    timeDeltaCounter = 0.0f;
    textIndexCounter = 0;
  }

  public void draw(float timeDelta) {
    if (this.mDisplayText.isEmpty())
      return;

    if (textIndexCounter != this.mDisplayText.length()) {
      timeDeltaCounter += timeDelta;
      Gdx.app.log("touch", "Updated timeDeltaCounter: " + timeDeltaCounter);
      if (timeDeltaCounter > mTalkSpeed) {
        textIndexCounter++;
        this.mLabel.setText(this.mDisplayText.substring(0, textIndexCounter));
        Gdx.app.log("touch", "Displaying the following text for state: " + this.mLabel.getText() + " For index: " + textIndexCounter);
        timeDeltaCounter = 0.0f;
      }
    }
  }

}
