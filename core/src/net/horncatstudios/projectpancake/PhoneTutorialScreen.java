package net.horncatstudios.projectpancake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import net.horncatstudios.conversationengine.Conversation;
import net.horncatstudios.conversationengine.Response;
import net.horncatstudios.conversationengine.State;
import net.horncatstudios.gameengine.*;

/**
 * Created by Angelina on 7/3/2014.
 */
public class PhoneTutorialScreen extends BaseScreen implements StateChangeListener {

  ConversationWidget mConversationWidget;
  Conversation mConversation;
  RectangleSprite mRectangleSprite;

  private FitViewport mPhoneStageViewport;
  private Stage mPhoneStage;

  private ImageTextButton mContactsButton;
  private ImageTextButton mPhoneButton;
  private ImageTextButton mSettingsButton;
  private ImageTextButton mMapButton;
  private ImageTextButton mFaceboxButton;

  private Image mStaminaImage;

  static final String DORIAN_ADD_CONTACT_INFO = "DorianAdd";
  static final String SHOW_CALENDAR = "ShowCalendar";

  PhoneCalendarScreen mCalendarScreen;

  @Override
  public void createScene() {

    mCalendarScreen = new PhoneCalendarScreen(this);

    this.mConversationWidget = new ConversationWidget(this);
    this.mConversation = new Conversation();

    loadPhoneTutorialTalking(true);

//    this.mConversationWidget.setState(this.mConversation.ConversationStates.get(0));
    Gdx.input.setInputProcessor(this);

    mRectangleSprite = new RectangleSprite(50.0f, 0.0f, 300.0f, camera.viewportHeight, 2.0f);
    mRectangleSprite.setCenter(camera.viewportWidth/2f, camera.viewportHeight/2f);

    mPhoneStageViewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);
    mPhoneStage = new Stage(mPhoneStageViewport, this.mGame.batch);
    Table tableLayout = new Table();
    tableLayout.debug();
     tableLayout.setFillParent(true);
    this.mPhoneStage.addActor(tableLayout);

    mContactsButton = new ImageTextButton("Contacts", resourcesManager.contactsStyle);
    mContactsButton.setChecked(true);

    mPhoneButton = new ImageTextButton("Phone", resourcesManager.phoneStyle);
    mSettingsButton = new ImageTextButton("Settings", resourcesManager.settingsStyle);
    mMapButton = new ImageTextButton("Map", resourcesManager.mapStyle);
    mFaceboxButton = new ImageTextButton("Facebox", resourcesManager.socialNetworkStyle);

    mStaminaImage = new Image(resourcesManager.staminaPlaceHolder);

    tableLayout.add(mContactsButton).width(70).height(70).pad(10.0f);
    tableLayout.add(mPhoneButton).width(70).height(70).pad(10.0f);
    tableLayout.add(mSettingsButton).width(70).height(70).pad(10.0f);
    tableLayout.row();
    tableLayout.add(mMapButton).width(70).height(70).pad(10.0f);
    tableLayout.add(mFaceboxButton).width(70).height(70).pad(10.0f);
    tableLayout.row();
    tableLayout.add(mStaminaImage).width(100).height(100).pad(10.0f);

    tableLayout.top();

    this.mConversationWidget.setState( mConversation.ConversationStates.get(0) );
  }

  @Override
  public void setCustom(String event) {
    if (event.equals("explain")) {
      loadPhoneTutorialTalking(true);
    } else {
      loadPhoneTutorialTalking(false);
    }
  }

  private void loadPhoneTutorialTalking(boolean explain) {

    State state4 = new State("I'll give you my number, since I'm nice.  You should read my blog if you're bored. It's pretty deep.");
    state4.CustomEvent = DORIAN_ADD_CONTACT_INFO;

    if (explain) {
      State state1 = new State("First, you can do pretty much anything with your phone.");
      State state2 = new State("You can play games on your phone, text people, or see what’s going on in the world.");
      State state3 = new State("Everyone here uses [social media name]. As you get to know people, maybe they’ll add you?");

      state1.Responses.add(new Response("", state2));
      state2.Responses.add(new Response("", state3));
      state3.Responses.add(new Response("", state4));

      this.mConversation.ConversationStates.add(state1);
      this.mConversation.ConversationStates.add(state1);
      this.mConversation.ConversationStates.add(state3);
    }


    State state5 = new State("You can see all my updates.  If you want to find me or your other friends, we usually check in wherever we are.");
    State state6 = new State("That way you can hang out with people without showing them that you're 'trying' y'know?");

    State stage7 = new State("If you ever want to send me a text ... ");
    stage7.CustomEvent = SHOW_CALENDAR;


    State state9 = new State("You can play games at most of the places you go, so if you get tired of socializing, you can alwaysw blow off some steam on the games.");
    State state10 = new State("They'll have [symbol] over them, so you'll see them immediately.");
    State state11 = new State("Tired yet? I guess it's a good time to talk about stamina");
    State state12 = new State("Every in-game day, you get a certain amount of stamina. You can see how much you have left based on the clock.");
    State state13 = new State("The later it is, the less you have.  Each time you hang out with someone, it'' take a certain amount of time.");
    State state14 = new State("At the end of the day, you can go home and sleep to start a new in-game day.");
    State state15 = new State("You can also be a rebel and stay up late, but you might sleep in and miss school, so try to be a little responsible Or don't.");
    State state16 = new State("Whatever. But seriously, if you stay up, you'll feel it the next day");

    state4.Responses.add(new Response("", state5));
    state5.Responses.add(new Response("", state6));
    state6.Responses.add(new Response("", stage7));

    stage7.Responses.add(new Response("", state9));
    state9.Responses.add(new Response("", state10));
    state10.Responses.add(new Response("", state11));
    state11.Responses.add(new Response("", state12));
    state12.Responses.add(new Response("", state13));
    state13.Responses.add(new Response("", state14));
    state14.Responses.add(new Response("", state15));
    state15.Responses.add(new Response("", state16));
    state16.Responses.add(new Response("", null));

    this.mConversation.ConversationStates.add(state4);
  }


  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 0, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    mGame.batch.setProjectionMatrix(camera.combined);


    mGame.batch.begin();
    this.mRectangleSprite.draw(mGame.batch);
    this.mConversationWidget.draw(mGame.batch, resourcesManager.fontManager.conversationFont, delta);
    mGame.batch.end();

    Table.drawDebug(mPhoneStage);
    mPhoneStage.draw();
  }

  @Override
  public ScreenManager.ScreenType getSceneType() {
    return ScreenManager.ScreenType.PHONE_TUTORIAL;
  }

  @Override
  public boolean keyDown(int keycode) {
    return this.mConversationWidget.keyDown(keycode);
  }

  @Override
  public void onStateChange(final State state, final String customEvent) {

    if (customEvent.equals(SHOW_CALENDAR)) {
      mCalendarScreen.loadChildScreen();
    }

    this.mConversationWidget.setState(state);
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
  public void resize(int width, int height) {

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
    Gdx.input.setInputProcessor(this);
  }

  @Override
  public void dispose() {

  }


}
