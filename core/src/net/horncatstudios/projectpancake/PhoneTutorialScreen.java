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


  @Override
  public void createScene() {
    this.mConversationWidget = new ConversationWidget(this);
    this.mConversation = new Conversation();

    loadPhoneTutorialTalking(true);

//    this.mConversationWidget.setState(this.mConversation.ConversationStates.get(0));
    Gdx.input.setInputProcessor(this);

    mRectangleSprite = new RectangleSprite(50.0f, 0.0f, 300.0f, camera.viewportHeight, 2.0f);
    mRectangleSprite.setCenter(camera.viewportWidth/2f, camera.viewportHeight/2f);

    mPhoneStageViewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);
    mPhoneStage = new Stage(mPhoneStageViewport, this.mGame.batch);
    //mPhoneStage = new Stage(camera.viewportWidth, camera.viewportHeight, false);
    Table tableLayout = new Table();
    tableLayout.debug();
   // tableLayout.setPosition(50.0f, 0.0f);
    //tableLayout.setOrigin(50.0f, 0.0f);
   // tableLayout.setWidth(300.0f);
    //tableLayout.top();
     tableLayout.setFillParent(true);
    this.mPhoneStage.addActor(tableLayout);

    mContactsButton = new ImageTextButton("Contacts", resourcesManager.contactsStyle);

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

  public void loadPhoneTutorialTalking( boolean explain ) {
    State state1 = new State("First, you can do pretty much anything with your phone.");
    State state2 = new State("You can play games on your phone, text people, or see what’s going on in the world.");
    State state3 = new State("Everyone here uses [social media name]. As you get to know people, maybe they’ll add you?");

    state1.Responses.add(new Response("", state2));
    state2.Responses.add(new Response("", state3));

    State state4 = new State("I'll give you my number, since I'm nice.  You should read my blog if you're bored. It's pretty deep.");


    state3.Responses.add(new Response("", null));
    state4.Responses.add(new Response("", null));

    this.mConversation.ConversationStates.add(state1);
    this.mConversation.ConversationStates.add(state1);
    this.mConversation.ConversationStates.add(state3);
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
  public void onStateChange(final State state) {

  }

  @Override
  public void onStateChange(final State state, final String customEvent) {
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

  }

  @Override
  public void dispose() {

  }


}
