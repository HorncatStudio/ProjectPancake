package net.horncatstudios.projectpancake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import net.horncatstudios.conversationengine.Conversation;
import net.horncatstudios.conversationengine.Response;
import net.horncatstudios.conversationengine.State;
import net.horncatstudios.gameengine.BaseScene;
import net.horncatstudios.gameengine.ConversationWidget;
import net.horncatstudios.gameengine.Label;
import net.horncatstudios.gameengine.SceneManager;
import net.horncatstudios.toolkit.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angelina on 7/18/13.
 */
public class GameScreen extends BaseScene {

  private Sprite mDorianSprite;
  private Conversation mCurrentConversation;
  private ConversationWidget mConversationWidget;

  public GameScreen() {
    Gdx.app.log("touch", "Creating game mLevel scene Game");
    mDorianSprite = new Sprite(resourcesManager.dorianBaseImage, 640, 520);
    this.mConversationWidget = new ConversationWidget();
    this.mCurrentConversation = new Conversation();

    GenerateDemoConversation();

    this.mConversationWidget.setState(mCurrentConversation.ConversationStates.get(0));
  }

  public void GenerateDemoConversation() {
    State state1 = new State("Must be new here...");
    State state2 = new State("... O-o ...");
    State state3 = new State("Too ... Happy ...");
    State state4 = new State("Well welcome here.");

    Response response = new Response("Oh yes!  First day here!", state2);
    state1.Responses.add(response);
    state1.Responses.add(new Response("Um yeah.  Obvious isn't it?", state2));
    state1.Responses.add(new Response("Yes.", state3));

    state2.Responses.add(new Response("Is everything alright?", state3));
    state2.Responses.add(new Response("No.", state4));

    state3.Responses.add(new Response("Weeeeeee", null));
    state3.Responses.add(new Response("To 4!", state4));

    state4.Responses.add(new Response("Well that was awkward.", null));
    state4.Responses.add(new Response("Conversation ending now", null));

    mCurrentConversation.ConversationStates.add(state1);
    mCurrentConversation.ConversationStates.add(state2);
    mCurrentConversation.ConversationStates.add(state3);
    mCurrentConversation.ConversationStates.add(state4);
  }

  @Override
  public void createScene() {
    Gdx.input.setInputProcessor(this);
  }

  @Override
  public SceneManager.SceneType getSceneType() {
    return SceneManager.SceneType.SCENE_GAME;
  }

  @Override
  public void render(float v) {
    Gdx.gl.glClearColor(1, 0, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    mGame.batch.setProjectionMatrix(camera.combined);

    mGame.batch.begin();
    mGame.batch.draw(resourcesManager.schoolBackground, 0, 0);
    this.mDorianSprite.draw(mGame.batch);
    mGame.batch.draw(resourcesManager.textBackground, 0, 0);
    mConversationWidget.draw(mGame.batch, resourcesManager.mConversationFont);
    mGame.batch.end();
  }

  @Override
  public void resize(int i, int i2) {

  }

  @Override
  public void show() {
    // resourcesManager.conversationMusic.play();
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
    resourcesManager.unloadGameResources();
  }

  @Override
  public boolean keyDown(int i) {
    return this.mConversationWidget.keyDown(i);
  }

  @Override
  public boolean keyUp(int i) {
    return false;
  }

  @Override
  public boolean keyTyped(char c) {
    return false;
  }

  @Override
  public boolean touchDown(int i, int i2, int i3, int i4) {
    return false;
  }

  @Override
  public boolean touchUp(int i, int i2, int i3, int i4) {
    return false;
  }

  @Override
  public boolean touchDragged(int i, int i2, int i3) {
    return false;
  }

  @Override
  public boolean mouseMoved(int i, int i2) {
    return false;
  }

  @Override
  public boolean scrolled(int i) {
    return false;
  }
}
