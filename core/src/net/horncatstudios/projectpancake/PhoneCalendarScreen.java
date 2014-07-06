package net.horncatstudios.projectpancake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import net.horncatstudios.conversationengine.Conversation;
import net.horncatstudios.conversationengine.Response;
import net.horncatstudios.conversationengine.State;
import net.horncatstudios.gameengine.*;

/**
 * Created by Angelina on 7/5/2014.
 */
public class PhoneCalendarScreen extends ChildScreen implements StateChangeListener {

  ConversationWidget mConversationWidget;
  Conversation mConversation;

  PhoneCalendarScreen(BaseScreen parent) {
    super(parent);

    this.mConversationWidget = new ConversationWidget(this, resourcesManager.fontManager.conversationFont,
        this.camera, this.mGame.batch);
    this.mConversation = new Conversation();

    State state = new State("People's birthdays and stuff pop up took, so you've got no excuse to miss a birthday.");
    State state2 = new State("Hint, hint");

    state.Responses.add(new Response("", state2));
    state2.Responses.add(new Response("", null));

    this.mConversation.ConversationStates.add(state);
    this.mConversation.ConversationStates.add(state2);

    this.mConversationWidget.setState(state);
  }

  @Override
  public void createScene() {

  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 0, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    mGame.batch.setProjectionMatrix(camera.combined);


    mGame.batch.begin();
    mGame.batch.draw(resourcesManager.sampleCalendar, 0, 0, camera.viewportWidth, camera.viewportHeight);
    this.mConversationWidget.draw(mGame.batch, delta);
    mGame.batch.end();
    this.mConversationWidget.draw();
  }


  @Override
  public void onStateChange(State state, String customEvent) {
    this.mConversationWidget.setState(state);

    if (null == state) {
      this.finish();
    }
  }

  @Override
  public ScreenManager.ScreenType getSceneType() {
    return ScreenManager.ScreenType.PHONE_TUTORIAL;
  }

  @Override
  public void setCustom(String event) {

  }

  @Override
  public boolean keyDown(int keycode) {
    return this.mConversationWidget.keyDown(keycode);
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
