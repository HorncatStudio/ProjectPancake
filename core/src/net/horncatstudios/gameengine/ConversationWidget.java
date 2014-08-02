package net.horncatstudios.gameengine;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import net.horncatstudios.conversationengine.Response;
import net.horncatstudios.conversationengine.State;
import net.horncatstudios.projectpancake.HcLocale;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angelina on 5/10/2014.
 */
public class ConversationWidget {

  private State mCurrentState;

  private int mCurrentSelectedIndex;

  private StateChangeListener mStateListener;

  private int mWidth;
  private int mBackgroundHeight;
  private int mFontHeight;

  public Texture mCharacterNameBackround;
  public Texture mBackground;

  private com.badlogic.gdx.scenes.scene2d.ui.Label mCharacterNameLabel;

  private com.badlogic.gdx.scenes.scene2d.ui.Label mStateTextLabel;
  private TalkingLabelDecorator mTalkingLabelDecorator;

  List<TextButton> mCharacterResponses;

  private Stage mStage;
  private BitmapFont mFont;

  /**
   * Constructor that registers a StateChangeListener.  Enables actions to be selected
   *
   * @param listener
   */

  public ConversationWidget(StateChangeListener listener, BitmapFont font, OrthographicCamera camera, SpriteBatch batch) {
    this(listener, font, camera, batch, null);
  }

  public ConversationWidget(StateChangeListener listener, BitmapFont font, OrthographicCamera camera, SpriteBatch batch, Texture background) {

    mStateListener = listener;
    this.mCurrentSelectedIndex = 0;
    mFontHeight = 24;

    if (null == background) {
      Pixmap backgroundTexture = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
      backgroundTexture.setColor(Color.BLACK);
      backgroundTexture.fill();
      mBackground = new Texture(backgroundTexture);

      mCharacterNameBackround = new Texture(backgroundTexture);
    }

    this.mFont = font;
    this.mFontHeight = (int) this.mFont.getLineHeight();

    this.mStage = new Stage(new ScreenViewport(camera), batch);

    Table tableLayout = new Table();

    if (HcDefines.DebuggingEnabled) {
      tableLayout.debug();
    }

    TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
    style.checkedFontColor = Color.GRAY;
    style.fontColor = Color.WHITE;
    style.font = this.mFont;

    TextButton.TextButtonStyle difStyle = new TextButton.TextButtonStyle();
    difStyle.checkedFontColor = Color.PINK;
    difStyle.fontColor = Color.WHITE;
    difStyle.font = this.mFont;

    com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle labelStyle = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle(mFont, Color.WHITE);
    if (HcLocale.getCurrentLocale() == HcLocale.Locale.JP) {
      this.mCharacterNameLabel = new com.badlogic.gdx.scenes.scene2d.ui.Label("ドリアン", labelStyle);
    } else {
      this.mCharacterNameLabel = new com.badlogic.gdx.scenes.scene2d.ui.Label("Dorian", labelStyle);
    }

    //! Adding a smidge of padding so that the word wrapping wont overlap with the side of the screen
    this.mWidth = (int) camera.viewportWidth - 1;

    this.mCharacterNameLabel.setWidth(this.mWidth);
    this.mCharacterNameLabel.setWrap(true);
    this.mCharacterNameLabel.setWidth(this.mWidth);
    this.mCharacterNameLabel.setAlignment(Align.left | Align.top);

    this.mStateTextLabel = new com.badlogic.gdx.scenes.scene2d.ui.Label("Other Text", labelStyle);
    this.mStateTextLabel.setAlignment(Align.left | Align.top);
    this.mStateTextLabel.setWidth(this.mWidth);
    this.mStateTextLabel.setWrap(true);

    mTalkingLabelDecorator = new TalkingLabelDecorator(this.mStateTextLabel);

    this.mCharacterResponses = new ArrayList<TextButton>();
    this.mCharacterResponses.add(new TextButton("ResponseA", style));
    this.mCharacterResponses.add(new TextButton("ResponseB", style));
    this.mCharacterResponses.add(new TextButton("ResponseC", style));

    for (TextButton response : this.mCharacterResponses) {
      response.getLabel().setAlignment(Align.left | Align.top);
      response.getLabel().setWidth(this.mWidth);
      response.getLabel().setWrap(true);
    }

    tableLayout.add(this.mCharacterNameLabel).width(this.mWidth).height(this.mFontHeight).left();
    tableLayout.row().expand();
    tableLayout.row();
    tableLayout.add(this.mStateTextLabel).width(this.mWidth).height(this.mFontHeight * 2).left();

    for (TextButton response : this.mCharacterResponses) {
      tableLayout.row();
      tableLayout.add(response).width(this.mWidth).height(this.mFontHeight).left();
    }
    tableLayout.bottom().left();
    tableLayout.pad(5);

    this.mStage.addActor(tableLayout);

    //! note - must force the table to "validate" in order to determine sizing and positions for each of the labels
    //!        Once validated, can pull the physical position of the character label to determine the height of the
    //!        background for the widget.
    tableLayout.validate();
    this.mBackgroundHeight = (int) (mCharacterNameLabel.getY());
  }

  public void setState(State state) {
    if (null == state)
      return;

    this.mCurrentState = state;
    this.mTalkingLabelDecorator.setText(state.Text);

    for (TextButton labels : this.mCharacterResponses) {
      labels.setText("");
    }

    for (int index = 0; index < state.Responses.size(); index++) {
      this.mCharacterResponses.get(index).setText(state.Responses.get(index).Text);
    }

    setSelectedIndex(0);
  }

  public void draw(SpriteBatch batch, float timeDelta) {
    Color oldColor = batch.getColor();

    batch.setColor(oldColor.r, oldColor.g, oldColor.b, .6f);
    batch.draw(this.mBackground, 0, 0, this.mWidth, this.mBackgroundHeight);
    batch.draw(this.mCharacterNameBackround, 0, this.mBackgroundHeight, (float) (this.mWidth / 7.8), this.mFontHeight + 3);
    batch.setColor(oldColor);

    this.mTalkingLabelDecorator.draw(timeDelta);
  }

  public void draw() {
    if (HcDefines.DebuggingEnabled) {
      Table.drawDebug(this.mStage);
    }
    this.mStage.draw();
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
    if (this.mCurrentSelectedIndex == this.mCharacterResponses.size())
      return;

    setSelectedIndex(this.mCurrentSelectedIndex + 1);
  }

  private void setSelectedIndex(final int selectedIndex) {
    if (selectedIndex < 0 || selectedIndex >= this.numberOfVisibleWidgets())
      return;

    for (int buttonIndex = 0; buttonIndex < this.mCharacterResponses.size(); buttonIndex++) {
      if (selectedIndex == buttonIndex)
        this.mCharacterResponses.get(buttonIndex).setChecked(true);
      else
        this.mCharacterResponses.get(buttonIndex).setChecked(false);
    }
    this.mCurrentSelectedIndex = selectedIndex;
  }

  public TextButton currentButtonSelected() {
    return this.mCharacterResponses.get(this.mCurrentSelectedIndex);
  }

  /**
   * Programatically selects the currently selected item within the widget.  The equivalent to a button click.
   */
  private void triggerResponseSelection() {
    TextButton selectedButton = currentButtonSelected();

    for (Response response : mCurrentState.Responses) {
      if (response.Text.equals(selectedButton.getText().toString())) {
        mStateListener.onStateChange(response.NextState, mCurrentState.CustomEvent);
      }
    }
  }

  /**
   * Returns how many items are being displayed in the list.
   */
  final int numberOfVisibleWidgets() {
    int total = 0;

//    for (Label button : this.mResponceWidgets) {
//      if (!button.Text.isEmpty())
//        total++;
//    }

    for (TextButton button : this.mCharacterResponses) {
      if (!button.getText().toString().isEmpty())
        total++;
    }

    return total;
  }
}
