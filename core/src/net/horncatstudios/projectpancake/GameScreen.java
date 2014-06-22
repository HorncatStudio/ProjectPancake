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

    if( HcLocale.getCurrentLocale() == HcLocale.Locale.EN ) {
      GenerateDemoConversation();
    } else {
      GenerateJapaneseConversation();
    }

    this.mConversationWidget.setState(mCurrentConversation.ConversationStates.get(0));
  }

  public void GenerateDemoConversation() {
    State state1 = new State("Hey, new girl. 'Sup? You're probably wondering why someone would event talk to you right?", Emotion.CASUAL);
    State state2 = new State("Is that like the opposite of a #humblebrag? Anyway", Emotion.EMBARRASSED);
    State state3 = new State("Well, the instituion asked me to give you a grand tour of the grounds.  I'm honored that they realized how much I didn't want to be in class.", Emotion.CASUAL);
    State state4 = new State("Well welcome here.", Emotion.EMBARRASSED);

    Response response = new Response("Yes", state3);
    state1.Responses.add(response);
    state1.Responses.add(new Response("Its' Obvious that I'm just too awesome to ignore", state2));

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
     // Charset.forName("SJIS");
    State state1 = new State( "ここ初めてでしょう...", Emotion.CASUAL);
    State state2 = new State("...そう...", Emotion.EMBARRASSED);
    State state3 = new State("喜びすぎ...", Emotion.SAD);
    State state4 = new State("まぁようこそこの学園へ。", Emotion.EMBARRASSED);

    Response response = new Response("そう！今日が初登校日！", state2);
    state1.Responses.add(response);
    state1.Responses.add(new Response("ああ、見て分かんない？", state2));
    state1.Responses.add(new Response("ああ", state3));

    state2.Responses.add(new Response("大丈夫？", state3));
    state2.Responses.add(new Response("いや", state4));

    state3.Responses.add(new Response("やったーー", null));
    state3.Responses.add(new Response("4へ！", state4));

    state4.Responses.add(new Response("何か不思議な娘だったな", null));
    state4.Responses.add(new Response("会話終了", null));

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
    mConversationWidget.draw(mGame.batch, resourcesManager.fontManager.conversationFont);
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
