package net.horncatstudios.gameengine;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.horncatstudios.conversationengine.Response;
import net.horncatstudios.conversationengine.State;
import net.horncatstudios.toolkit.HcString;
import net.horncatstudios.toolkit.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angelina on 5/10/2014.
 */
public class ConversationWidget {


  public Texture mCharacterNameBackround;
  public Label mCharacterName;

  public Texture mBackground;

  public TalkingLabel mStateLabel;

  public Label mResponseALabel;
  public Label mResponseBLabel;
  public Label mResponseCLabel;

  private List<Label> mResponceWidgets;

  private State mCurrentState;

  private int mCurrentSelectedIndex;

  private StateChangeListener mStateListener;
  private int mTopOfCharacterName;
  private int mWidth;
  private int mHeight;
  private int mFontHeight;

  public ConversationWidget( StateChangeListener listener ) {
    this(listener, 800, 120, null);
  }

  public ConversationWidget( StateChangeListener listener, int width, int height ) {
    this(listener, width, height, null);
  }


  public ConversationWidget(StateChangeListener listener, int width, int height, Texture background ) {
    mStateListener = listener;
    mFontHeight = 24;
    this.mTopOfCharacterName = height + mFontHeight;
    mCharacterName = new Label("Dorian", new Point(10, this.mTopOfCharacterName));
    mStateLabel = new TalkingLabel(new HcString("STATE LABEL."), new Point(10, height - 2));

    mResponseALabel = new Label("RESPONCE A LABEL.", new Point(10, 70));
    mResponseBLabel = new Label("RESPONCE B LABEL.", new Point(285, 70));
    mResponseCLabel = new Label("RESPONCE C LABEL.", new Point(10, 30));

    mResponceWidgets = new ArrayList<Label>();
    mResponceWidgets.add(mResponseALabel);
    mResponceWidgets.add(mResponseBLabel);
    mResponceWidgets.add(mResponseCLabel);

    this.mCurrentSelectedIndex = 0;
    this.mWidth = width;
    this.mHeight = height;

    if( null == background )
    {
      Pixmap backgroundTexture = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
      backgroundTexture.setColor(Color.RED);
      backgroundTexture.fill();
      mBackground = new Texture(backgroundTexture);

      mCharacterNameBackround = new Texture(backgroundTexture);
    }
  }

  public void setState(State state) {
    if (null == state)
      return;

    mCurrentState = state;
    mStateLabel.setText( state.Text );

    for (Label labels : mResponceWidgets) {
      labels.Text = "";
    }

    for (int index = 0; index < state.Responses.size(); index++) {
      mResponceWidgets.get(index).Text = state.Responses.get(index).Text;
    }

    setSelectedIndex(0);
  }

  public void draw(SpriteBatch batch, BitmapFont font, float timeDelta ) {

    Color oldColor = batch.getColor();

    batch.setColor( oldColor.r, oldColor.g, oldColor.b, .3f);
    batch.draw(this.mBackground, 0, 0, this.mWidth, this.mHeight);
    batch.draw(this.mCharacterNameBackround, 0, this.mHeight  , this.mWidth/10, this.mFontHeight + 3 );
    batch.setColor(oldColor);

    this.mCharacterName.draw(batch, font);
    this.mStateLabel.draw(batch, font, timeDelta);
    this.mResponseALabel.draw(batch, font);
    this.mResponseBLabel.draw(batch, font);
    this.mResponseCLabel.draw(batch, font);
  }

  public boolean keyDown(int keycode) {
    switch (keycode) {
      case Input.Keys.UP:
        moveSelectionUp();
        break;
      case Input.Keys.DOWN:
        moveSelectionDown();
        break;
      case Input.Keys.ENTER:
      case Input.Keys.BUTTON_A:
        triggerResponseSelection();
        break;
    }
    return true;
  }

  private void moveSelectionUp() {
    if (this.mCurrentSelectedIndex == 0)
      return;

    setSelectedIndex(this.mCurrentSelectedIndex - 1);
  }

  private void moveSelectionDown() {
    if (this.mCurrentSelectedIndex == this.mResponceWidgets.size())
      return;

    setSelectedIndex(this.mCurrentSelectedIndex + 1);
  }

  private void setSelectedIndex(final int selectedIndex) {
    if (selectedIndex < 0 || selectedIndex >= this.numberOfVisibleWidgets())
      return;

    for (int buttonIndex = 0; buttonIndex < this.mResponceWidgets.size(); buttonIndex++) {


      if (selectedIndex == buttonIndex)
        this.mResponceWidgets.get(buttonIndex).setChecked(true);
      else
        this.mResponceWidgets.get(buttonIndex).setChecked(false);
    }
    this.mCurrentSelectedIndex = selectedIndex;
  }

  public Label currentButtonSelected() {
    return this.mResponceWidgets.get(this.mCurrentSelectedIndex);
  }

  private void triggerResponseSelection() {
    Label selectedButton = currentButtonSelected();

    for (Response response : mCurrentState.Responses) {
      if (response.Text.equals(selectedButton.Text)) {
        mStateListener.onStateChange(response.NextState);
      }
    }
  }

  final int numberOfVisibleWidgets() {
    int total = 0;

    for (Label button : this.mResponceWidgets) {
      if (!button.Text.isEmpty())
        total++;
    }

    return total;
  }
}
