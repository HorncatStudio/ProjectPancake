package net.horncatstudios.projectpancake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import net.horncatstudios.conversationengine.Conversation;
import net.horncatstudios.conversationengine.Emotion;
import net.horncatstudios.conversationengine.Response;
import net.horncatstudios.conversationengine.State;
import net.horncatstudios.gameengine.BaseScene;
import net.horncatstudios.gameengine.ConversationWidget;
import net.horncatstudios.gameengine.SceneManager;
import net.horncatstudios.gameengine.StateChangeListener;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by Angelina on 7/18/13.
 */
public class GameScreen extends BaseScene implements StateChangeListener {

  private Dorian mDorianSprite;
  private Conversation mCurrentConversation;
  private ConversationWidget mConversationWidget;

  public GameScreen() {
    Gdx.app.log("touch", "Creating game mLevel scene Game");
    mDorianSprite = new Dorian(resourcesManager.dorianImages);
    this.mConversationWidget = new ConversationWidget(this);
    this.mCurrentConversation = new Conversation();

   // GenerateDemoConversation();
      GenerateJapaneseConversation();

    this.mConversationWidget.setState(mCurrentConversation.ConversationStates.get(0));
  }

  public void GenerateDemoConversation() {
    State state1 = new State("Must be new here...", Emotion.CASUAL);
    State state2 = new State("... O-o ...", Emotion.EMBARRASSED);
    State state3 = new State("Too ... Happy ...", Emotion.SAD);
    State state4 = new State("Well welcome here.", Emotion.EMBARRASSED);

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

  public void GenerateJapaneseConversation() {
      Charset.forName("UTF-8");
    State state1 = new State( "はい" );
    State state2 = new State("よ");
    State state3 = new State("おもしろい");
    State state4 = new State("ふうううう");

    Response response = new Response("ふも", state2);
    state1.Responses.add(response);
    state1.Responses.add(new Response("ふもふも", state2));
    state1.Responses.add(new Response("ふもふ", state3));

    state2.Responses.add(new Response("ぱんつ", state3));
    state2.Responses.add(new Response("みえてるよ", state4));

    state3.Responses.add(new Response("みて", null));
    state3.Responses.add(new Response("おろ", state4));

    state4.Responses.add(new Response("ぷ", null));
    state4.Responses.add(new Response("ふ～", null));

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
  public void onStateChange(final State state) {

    if (null == state) {
      SceneManager.getInstance().loadEndScreen();
      dispose();
      return;
    }

    // \todo update relationship stats
    mDorianSprite.setEmotion(state.Emotion);
    mConversationWidget.setState(state);
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
