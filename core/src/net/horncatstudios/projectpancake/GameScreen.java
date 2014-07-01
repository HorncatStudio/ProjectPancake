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
    this.mConversationWidget = new ConversationWidget(this,
                          (int)(this.camera.viewportWidth),
                          (int)(this.camera.viewportHeight/7) );
    mDorianSprite.setPosition(0, 10);
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
    State state1 = new State("Hey, new girl. 'Sup? You're probably wondering why someone would even talk to you right?", Emotion.CASUAL);
    State state2 = new State("Is that like the opposite of a #humblebrag? Anyway", Emotion.EMBARRASSED);
    State state3 = new State("Well, the institution asked me to give you a grand tour of the grounds.  I'm honored that they realized how much I didn't want to be in class.", Emotion.CASUAL);
    State state4 = new State("Have you had a chance to check out our humble city? It?Ωfs pretty boring around here.", Emotion.EMBARRASSED);
    State state5 = new State("Sorry, what? I just had to hit the snooze button on my brain. Are you serious?");
    State state6 = new State("Eh, we?Ωfve got at least ONE place that sells fair trade coffee. I play the uke, steal Twinkies from the guy who lives with me. I?Ωfm just trying to find myself, y?Ωfknow?");
    State state7 = new State("Man, alright. Let?Ωfs just focus on the tour? This is already punishing enough without you making it worse.");
    State state8 = new State("Did you move from the middle of nowhere? New [guy/girl], you?Ωfre rather mysterious.");
    State state9 = new State("Very cool. Anyways, back to the tour.", Emotion.CASUAL);

    Response response = new Response("Yes", state3);
    state1.Responses.add(response);
    state1.Responses.add(new Response("Its' Obvious that I'm just too awesome to ignore", state2));

    state2.Responses.add(new Response("", state3));

    state3.Responses.add(new Response("", state4));

    state4.Responses.add(new Response("I think it is nice.", state5));
    state4.Responses.add(new Response("So what do you do all the time?", state6));

    state5.Responses.add(new Response("Yeah, I?Ωfm serious!: ", state7));
    state5.Responses.add(new Response("Uh, it's alright.", state8));

    state6.Responses.add(new Response("Cool.", state9));

    state7.Responses.add(new Response("", null));

    state8.Responses.add(new Response("", null));

    state9.Responses.add(new Response("", null));

    mCurrentConversation.ConversationStates.add(state1);
    mCurrentConversation.ConversationStates.add(state2);
    mCurrentConversation.ConversationStates.add(state3);
    mCurrentConversation.ConversationStates.add(state4);
  }

  public void GenerateJapaneseConversation() {
     // Charset.forName("SJIS");
    State state1 = new State( "?Ω?Ω?Ω?Ω?Ω?Ω?ΩﬂÇƒÇ≈ÇÔøΩ?ΩÂÇ§...", Emotion.CASUAL);
    State state2 = new State("...?Ω?Ω?Ω?Ω...", Emotion.EMBARRASSED);
    State state3 = new State("?Ω?Ω—ÇÔøΩ?Ω?Ω...", Emotion.SAD);
    State state4 = new State("?Ω‹ÇÔøΩ?ΩÊÇ§?Ω?Ω?Ω?Ω?Ω?Ω?ΩÃäw?Ω?Ω?Ω÷ÅB", Emotion.EMBARRASSED);

    Response response = new Response("?Ω?Ω?Ω?Ω?ΩI?Ω?Ω?Ω?Ω?Ωo?ΩZ?Ω?ΩI", state2);
    state1.Responses.add(response);
    state1.Responses.add(new Response("?Ω?Ω?Ω?Ω?ΩA?Ω?Ω?ΩƒïÔøΩ?Ω?Ω?Ω?Ω»ÇÔøΩ?ΩH", state2));
    state1.Responses.add(new Response("?Ω?Ω?Ω?Ω", state3));

    state2.Responses.add(new Response("?Ω?Ω?Ωv?ΩH", state3));
    state2.Responses.add(new Response("?Ω?Ω?Ω?Ω", state4));

    state3.Responses.add(new Response("?Ω?Ω?Ω?Ω?Ω?Ω[?Ω[", null));
    state3.Responses.add(new Response("4?Ω÷ÅI", state4));

    state4.Responses.add(new Response("?Ω?Ω?Ω?Ω?Ωs?Ωv?Ωc?Ω»ñÔøΩ?Ω?Ω?Ω?Ω?Ω?Ω?Ω?Ω", null));
    state4.Responses.add(new Response("?Ω?Ωb?ΩI?Ω?Ω", null));

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
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 0, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    mGame.batch.setProjectionMatrix(camera.combined);


    mGame.batch.begin();
    mGame.batch.draw(resourcesManager.schoolBackground, 0, 0, this.camera.viewportWidth, this.camera.viewportHeight);
    this.mDorianSprite.draw(mGame.batch);
    //mGame.batch.draw(resourcesManager.textBackground, 0, 0);


    mConversationWidget.draw(mGame.batch, resourcesManager.fontManager.conversationFont, delta);
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
