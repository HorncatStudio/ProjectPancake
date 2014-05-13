package net.horncatstudios.gameengine;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import net.horncatstudios.conversationengine.Response;
import net.horncatstudios.conversationengine.State;
import net.horncatstudios.toolkit.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angelina on 5/10/2014.
 */
public class ConversationWidget {

  public Label mStateLabel;

  public Label mResponseALabel;
  public Label mResponseBLabel;
  public Label mResponseCLabel;

  private List<Label> mResponceWidgets;

  private State mCurrentState;

  private int mCurrentSelectedIndex;

  public ConversationWidget() {

    mStateLabel = new Label("STATE LABEL.", new Point(10, 112));

    mResponseALabel = new Label("RESPONCE A LABEL.", new Point(10, 70));
    mResponseBLabel = new Label("RESPONCE B LABEL.", new Point(285, 70));
    mResponseCLabel = new Label("RESPONCE C LABEL.", new Point(10, 30));

    mResponceWidgets = new ArrayList<Label>();
    mResponceWidgets.add(mResponseALabel);
    mResponceWidgets.add(mResponseBLabel);
    mResponceWidgets.add(mResponseCLabel);

    this.mCurrentSelectedIndex = 0;
  }

  public void setState(State state) {
    if (null == state)
      return;

    mCurrentState = state;
    mStateLabel.Text = "Dorian: " + state.Text;

    for (Label labels : mResponceWidgets) {
      labels.Text = "";
    }

    for (int index = 0; index < state.Responses.size(); index++) {
      mResponceWidgets.get(index).Text = state.Responses.get(index).Text;
    }

    setSelectedIndex(0);
  }

  public void draw(SpriteBatch batch, BitmapFont font) {
    this.mStateLabel.draw(batch, font);
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
    if ( selectedIndex < 0 || selectedIndex >= this.numberOfVisibleWidgets() )
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
        // \todo update relationship stats - refactor this and pull out of the widget
        setState(response.NextState);
      }
    }
  }

  final int numberOfVisibleWidgets() {
    int total = 0;

    for(Label button : this.mResponceWidgets ) {
      if( !button.Text.isEmpty() )
        total++;
    }

    return total;
  }
}
