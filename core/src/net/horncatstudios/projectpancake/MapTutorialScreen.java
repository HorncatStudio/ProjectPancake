package net.horncatstudios.projectpancake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import net.horncatstudios.conversationengine.Conversation;
import net.horncatstudios.conversationengine.Response;
import net.horncatstudios.conversationengine.State;
import net.horncatstudios.gameengine.BaseScreen;
import net.horncatstudios.gameengine.ConversationWidget;
import net.horncatstudios.gameengine.ScreenManager;
import net.horncatstudios.gameengine.StateChangeListener;

/**
 * Created by Angelina on 7/3/2014.
 */
public class MapTutorialScreen extends BaseScreen implements StateChangeListener {

  ConversationWidget mConversationWidget;
  Conversation mConversation;

  private static final String EXPLAIN_PHONE_TUTORIAL = "Explain";
  private static final String NO_EXPLAIN_PHONE_TUTORIAL = "NoExplain";

  @Override
  public void createScene() {
    this.mConversationWidget = new ConversationWidget(this);
    this.mConversation = new Conversation();

    loadMapTutorialTalking();

    this.mConversationWidget.setState(this.mConversation.ConversationStates.get(0));
    Gdx.input.setInputProcessor(this);
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
    state3.CustomEvent = this.EXPLAIN_PHONE_TUTORIAL;

    State state4 = new State("Dude, get a manual. But it's cool, I'll explain.");
    state4.CustomEvent = this.NO_EXPLAIN_PHONE_TUTORIAL;

    state2.Responses.add(new Response("Yes", state3));
    state2.Responses.add(new Response("No", state4));

    state3.Responses.add(new Response("", null));
    state4.Responses.add(new Response("", null));

    this.mConversation.ConversationStates.add(state1);
    this.mConversation.ConversationStates.add(state1);
    this.mConversation.ConversationStates.add(state3);
    this.mConversation.ConversationStates.add(state4);
  }

  @Override
  public void onStateChange(State state) {
    onStateChange(state, "");
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
    //  this.mDorianSprite.draw(mGame.batch);

    mConversationWidget.draw(mGame.batch, resourcesManager.fontManager.conversationFont, delta);
    mGame.batch.end();

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
