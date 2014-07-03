package net.horncatstudios.projectpancake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import net.horncatstudios.conversationengine.Conversation;
import net.horncatstudios.conversationengine.State;
import net.horncatstudios.gameengine.*;

/**
 * Created by Angelina on 7/3/2014.
 */
public class PhoneTutorialScreen extends BaseScreen implements StateChangeListener {

  ConversationWidget mConversationWidget;
  Conversation mConversation;
  RectangleSprite mRectangleSprite;

  @Override
  public void createScene() {
    this.mConversationWidget = new ConversationWidget(this);
    this.mConversation = new Conversation();

    loadPhoneTutorialTalking();

//    this.mConversationWidget.setState(this.mConversation.ConversationStates.get(0));
    Gdx.input.setInputProcessor(this);

    mRectangleSprite = new RectangleSprite(50.0f, 0.0f, 300.0f, camera.viewportHeight, 2.0f);
  }

  public void loadPhoneTutorialTalking() {
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
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 0, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    mGame.batch.setProjectionMatrix(camera.combined);


    mGame.batch.begin();
    this.mRectangleSprite.draw(mGame.batch);
    mGame.batch.end();
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
