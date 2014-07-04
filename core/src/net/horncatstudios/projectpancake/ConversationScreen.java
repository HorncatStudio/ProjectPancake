package net.horncatstudios.projectpancake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import net.horncatstudios.conversationengine.Conversation;
import net.horncatstudios.conversationengine.Emotion;
import net.horncatstudios.conversationengine.Response;
import net.horncatstudios.conversationengine.State;
import net.horncatstudios.gameengine.BaseScreen;
import net.horncatstudios.gameengine.ConversationWidget;
import net.horncatstudios.gameengine.ScreenManager;
import net.horncatstudios.gameengine.StateChangeListener;

/**
 * Created by Angelina on 7/18/13.
 */
public class ConversationScreen extends BaseScreen implements StateChangeListener {

  private Dorian mDorianSprite;
  private Conversation mCurrentConversation;
  private ConversationWidget mConversationWidget;

  public ConversationScreen() {
    Gdx.app.log("touch", "Creating game mLevel scene Game");
    mDorianSprite = new Dorian(resourcesManager.dorianImages);

    mDorianSprite.setPosition(0, 10);
    this.mConversationWidget = new ConversationWidget(this);
    this.mCurrentConversation = new Conversation();

    if (HcLocale.getCurrentLocale() == HcLocale.Locale.EN) {
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
    State state4 = new State("Have you had a chance to check out our humble city? It??fs pretty boring around here.", Emotion.EMBARRASSED);
    State state5 = new State("Sorry, what? I just had to hit the snooze button on my brain. Are you serious?");
    State state6 = new State("Eh, we??fve got at least ONE place that sells fair trade coffee. I play the uke, steal Twinkies from the guy who lives with me. I??fm just trying to find myself, y??fknow?");
    State state7 = new State("Man, alright. Let??fs just focus on the tour? This is already punishing enough without you making it worse.");
    State state8 = new State("Did you move from the middle of nowhere? New [guy/girl], you??fre rather mysterious.");
    State state9 = new State("Very cool. Anyways, back to the tour.", Emotion.CASUAL);

    Response response = new Response("Yes", state3);
    state1.Responses.add(response);
    state1.Responses.add(new Response("Its' Obvious that I'm just too awesome to ignore", state2));

    state2.Responses.add(new Response("", state3));

    state3.Responses.add(new Response("", state4));

    state4.Responses.add(new Response("I think it is nice.", state5));
    state4.Responses.add(new Response("So what do you do all the time?", state6));

    state5.Responses.add(new Response("Yeah, I??fm serious!: ", state7));
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
    State state1 = new State("?????????????????????...", Emotion.CASUAL);
    State state2 = new State("...????????...", Emotion.EMBARRASSED);
    State state3 = new State("????т?????...", Emotion.SAD);
    State state4 = new State("??????????????????????w???????B", Emotion.EMBARRASSED);

    Response response = new Response("??????????I??????????o??Z????I", state2);
    state1.Responses.add(response);
    state1.Responses.add(new Response("??????????A????????????????????H", state2));
    state1.Responses.add(new Response("????????", state3));

    state2.Responses.add(new Response("??????v??H", state3));
    state2.Responses.add(new Response("????????", state4));

    state3.Responses.add(new Response("????????????[??[", null));
    state3.Responses.add(new Response("4???I", state4));

    state4.Responses.add(new Response("??????????s??v??c????????????????????", null));
    state4.Responses.add(new Response("????b??I????", null));

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
  public ScreenManager.ScreenType getSceneType() {
    return ScreenManager.ScreenType.CONVERSATION;
  }


  @Override
  public void onStateChange(final State state) {

    if (null == state) {
      ScreenManager.getInstance().loadWorldMapTutorialScreen();
      dispose();
      return;
    }

    // \todo update relationship stats
    mDorianSprite.setEmotion(state.Emotion);
    mConversationWidget.setState(state);
  }

  @Override
  public void onStateChange(final State state, final String customEvent) {
    onStateChange(state);
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
    resourcesManager.unloadConversationResources();
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
