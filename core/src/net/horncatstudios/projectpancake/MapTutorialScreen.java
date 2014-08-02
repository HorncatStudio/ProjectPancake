package net.horncatstudios.projectpancake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import net.horncatstudios.conversationengine.Conversation;
import net.horncatstudios.conversationengine.Response;
import net.horncatstudios.conversationengine.State;
import net.horncatstudios.gameengine.*;
import net.horncatstudios.toolkit.HcString;
import net.horncatstudios.toolkit.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angelina on 7/3/2014.
 */
public class MapTutorialScreen extends BaseScreen implements StateChangeListener {

  ConversationWidget mConversationWidget;
  Conversation mConversation;

  private Stage mStage;

  private static final String EXPLAIN_PHONE_TUTORIAL = "Explain";
  private static final String NO_EXPLAIN_PHONE_TUTORIAL = "NoExplain";

  TextButton mHomeTextButton;
  TextButton mSchoolTextButton;
  TextButton mMallTextButton;
  TextButton mParkMenuButton;
  TextButton mCoffeeShopButton;
  List<TextButton> mapMenuButtons;

  Cell mBottomCell;

  int mCurrentMapIndex;

  Sprite mapPointerSprite;

  Point mHomePosition;
  Point mSchoolPosition;
  Point mParkPosition;

  @Override
  public void createScene() {

    mCurrentMapIndex = 1;
    this.mConversationWidget = new ConversationWidget(this, resourcesManager.fontManager.conversationFont,
        this.camera, this.mGame.batch);
    this.mConversation = new Conversation();

    HcString homeMenuItem = new HcString("Home");
    HcString schoolMenuItem = new HcString("School");
    HcString mallMenuItem = new HcString("Mall");
    HcString parkMenuItem = new HcString("Park");
    HcString coffeeShopItem = new HcString("Coffee Shop ");

    mHomePosition = new Point(100, 250);
    mSchoolPosition = new Point(200, 350);
    mParkPosition = new Point(400, 400);

    mapPointerSprite = new Sprite(resourcesManager.mapPointer);
    mapPointerSprite.setSize(75, 75);
    mapPointerSprite.setPosition(mSchoolPosition.X, mSchoolPosition.Y);

    mapMenuButtons = new ArrayList<TextButton>();

    mHomeTextButton = new TextButton(homeMenuItem.getText(), ResourceManager.fontManager.LocationMenuStyle);
    mHomeTextButton.getLabel().setAlignment(Align.left | Align.top);
    mapMenuButtons.add(mHomeTextButton);

    mSchoolTextButton = new TextButton(schoolMenuItem.getText(), ResourceManager.fontManager.LocationMenuStyle);
    mSchoolTextButton.setChecked(true);
    mSchoolTextButton.getLabel().setAlignment(Align.left | Align.top);
    mapMenuButtons.add(mSchoolTextButton);

    mMallTextButton = new TextButton(mallMenuItem.getText(), ResourceManager.fontManager.LocationMenuStyle);
    mMallTextButton.setDisabled(true);
    mMallTextButton.getLabel().setAlignment(Align.left | Align.top);
    mapMenuButtons.add(mMallTextButton);


    mParkMenuButton = new TextButton(parkMenuItem.getText(), ResourceManager.fontManager.LocationMenuStyle);
    //  mParkMenuButton.setDisabled(true);
    mParkMenuButton.getLabel().setAlignment(Align.left | Align.top);
    mapMenuButtons.add(mParkMenuButton);

    mCoffeeShopButton = new TextButton(coffeeShopItem.getText(), ResourceManager.fontManager.LocationMenuStyle);
    mCoffeeShopButton.setDisabled(true);
    mCoffeeShopButton.getLabel().setAlignment(Align.left | Align.top);
    mapMenuButtons.add(mCoffeeShopButton);

    int cellMenuWidth = (int) (this.camera.viewportWidth / 5);

    this.mStage = new Stage(mScreenViewport);
    Table mapMenu = new Table();

    if (HcDefines.DebuggingEnabled) {
      mapMenu.debug();
    }

    mapMenu.add().height(50);
    mapMenu.row();
    mapMenu.add(mHomeTextButton).width(cellMenuWidth).left();
    mapMenu.add().width(50);
    mapMenu.row();
    mapMenu.add(mSchoolTextButton).width(cellMenuWidth).left();
    mapMenu.row();
    mapMenu.add(mMallTextButton).width(cellMenuWidth).left();
    mapMenu.row();
    mapMenu.add(mParkMenuButton).width(cellMenuWidth).left();
    mapMenu.row();
    mBottomCell = mapMenu.add(mCoffeeShopButton).width(cellMenuWidth).left();
    mapMenu.top().right();

    this.mStage.addActor(mapMenu);
    mapMenu.validate();

    // mapMenu.setBackground(resourcesManager.menuBackground);

    if (HcLocale.getCurrentLocale() == HcLocale.Locale.EN) {
      loadMapTutorialTalking();
    } else {
      loadJapaneseMapTutorialTalking();
    }

    loadMapTutorialTalking();

    this.mConversationWidget.setState(this.mConversation.ConversationStates.get(0));
    Gdx.input.setInputProcessor(this);

    mapMenu.setFillParent(true);
  }

  @Override
  public ScreenManager.ScreenType getSceneType() {
    return ScreenManager.ScreenType.MAP_TUTORIAL;
  }

  private void loadMapTutorialTalking() {
    State state1 = new State("So here's map of the city.  You can access it from your phone any time you want. " +
        "Any time you want to go anywhere, just select the place on the map.");
    State state2 = new State("Do you already know how to use your phone?");

    state1.Responses.add(new Response("", state2));

    State state3 = new State("Okay, then that saves us a lot of time");
    state3.CustomEvent = this.NO_EXPLAIN_PHONE_TUTORIAL;

    State state4 = new State("Dude, get a manual. But it's cool, I'll explain.");
    state4.CustomEvent = this.EXPLAIN_PHONE_TUTORIAL;

    state2.Responses.add(new Response("Yes", state3));
    state2.Responses.add(new Response("No", state4));

    state3.Responses.add(new Response("", null));
    state4.Responses.add(new Response("", null));

    this.mConversation.ConversationStates.add(state1);
    this.mConversation.ConversationStates.add(state1);
    this.mConversation.ConversationStates.add(state3);
    this.mConversation.ConversationStates.add(state4);
  }

  private void loadJapaneseMapTutorialTalking() {
    State state1 = new State("これが町の全体図だ。いつでも携帯メニューからアクセスできる。行きたい場所をセレクトすればそこに行ける。");
    State state2 = new State("携帯の使い方は知ってるよな？");

    state1.Responses.add(new Response("", state2));

    State state3 = new State("よし、手間が省けたな。");
    state3.CustomEvent = this.EXPLAIN_PHONE_TUTORIAL;

    State state4 = new State("おいおい、マニュアル読めよ。まぁいい、説明してやるからよく聞けよ？");
    state4.CustomEvent = this.NO_EXPLAIN_PHONE_TUTORIAL;

    state2.Responses.add(new Response("はい", state3));
    state2.Responses.add(new Response("いいえ", state4));

    state3.Responses.add(new Response("", null));
    state4.Responses.add(new Response("", null));

    this.mConversation.ConversationStates.add(state1);
    this.mConversation.ConversationStates.add(state1);
    this.mConversation.ConversationStates.add(state3);
    this.mConversation.ConversationStates.add(state4);
  }

  @Override
  public void onStateChange(State state, String customEvent) {

    if (null == state) {
      if (customEvent == this.EXPLAIN_PHONE_TUTORIAL) {
        ScreenManager.getInstance().loadPhoneTutorialWithExplanation();
      } else if (customEvent == this.NO_EXPLAIN_PHONE_TUTORIAL) {
        ScreenManager.getInstance().loadPhoneTutorialWithoutExplanation();
      }
      this.dispose();
      return;
    }

    this.mConversationWidget.setState(state);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 0, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    mGame.batch.setProjectionMatrix(camera.combined);

    mGame.batch.begin();
    mGame.batch.draw(resourcesManager.worldMap, 0, 0, this.camera.viewportWidth, this.camera.viewportHeight);
    mGame.batch.draw(resourcesManager.menuBackgroundTexture, mBottomCell.getActorX() - 10, mBottomCell.getActorY() - 10, mBottomCell.getActorWidth() + 20, mBottomCell.getActorHeight() * 5 + 20);
    this.mConversationWidget.draw(mGame.batch, delta);
    this.mapPointerSprite.draw(mGame.batch);

    mGame.batch.end();

    this.mConversationWidget.draw();

    if (HcDefines.DebuggingEnabled) {
      Table.drawDebug(this.mStage);
    }

    this.mStage.draw();
  }


  @Override
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
        //triggerMapSelection();
        break;
    }

    return this.mConversationWidget.keyDown(keycode);
  }


  private void moveSelectionUp() {
    if (this.mCurrentMapIndex == 0)
      return;

    int newIndex = nextNotDisabledButton(this.mCurrentMapIndex, false);
    setSelectedIndex(newIndex);
  }

  private void moveSelectionDown() {
    if (this.mCurrentMapIndex == this.mapMenuButtons.size())
      return;

    int newIndex = nextNotDisabledButton(this.mCurrentMapIndex, true);
    setSelectedIndex(newIndex);
  }

  private void setSelectedIndex(final int selectedIndex) {
    if (selectedIndex < 0 || selectedIndex >= this.mapMenuButtons.size())
      return;

    for (int buttonIndex = 0; buttonIndex < this.mapMenuButtons.size(); buttonIndex++) {
      if (selectedIndex == buttonIndex) {
        this.mapMenuButtons.get(buttonIndex).setChecked(true);
        updateMapHighlightLocation(this.mapMenuButtons.get(buttonIndex));
      } else
        this.mapMenuButtons.get(buttonIndex).setChecked(false);
    }
    this.mCurrentMapIndex = selectedIndex;
  }

  int nextNotDisabledButton(final int index, boolean goingDown) {

    if (goingDown) {
      for (int buttonIndex = index + 1; buttonIndex < this.mapMenuButtons.size(); buttonIndex++) {
        if (!this.mapMenuButtons.get(buttonIndex).isDisabled())
          return buttonIndex;
      }
    } else {
      for (int buttonIndex = index - 1; buttonIndex >= 0; buttonIndex--) {
        if (!this.mapMenuButtons.get(buttonIndex).isDisabled())
          return buttonIndex;
      }
    }

    return index;
  }

  void updateMapHighlightLocation(TextButton button) {
    if (button == mSchoolTextButton) {
      this.mapPointerSprite.setPosition(this.mSchoolPosition.X, this.mSchoolPosition.Y);
    } else if (button == mHomeTextButton) {
      this.mapPointerSprite.setPosition(this.mHomePosition.X, this.mHomePosition.Y);
    } else if (button == mParkMenuButton) {
      this.mapPointerSprite.setPosition(this.mParkPosition.X, this.mParkPosition.Y);
    }
  }


  @Override
  public boolean keyUp(int keycode) {
    return false;
  }

  @Override
  public boolean keyTyped(char character) {
    return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
    return false;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
    return false;
  }

  @Override
  public boolean scrolled(int amount) {
    return false;
  }

  @Override
  public void show() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void dispose() {

  }

}
