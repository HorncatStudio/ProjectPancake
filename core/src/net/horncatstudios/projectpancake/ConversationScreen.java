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
    this.mConversationWidget = new ConversationWidget(this, resourcesManager.fontManager.conversationFont,
        this.camera, this.mGame.batch);
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
    State state4 = new State("Have you had a chance to check out our humble city? It's pretty boring around here.", Emotion.EMBARRASSED);
    State state5 = new State("Sorry, what? I just had to hit the snooze button on my brain. Are you serious?");
    State state6 = new State("Eh, we have got at least ONE place that sells fair trade coffee. I play the uke, steal Twinkies from the guy who lives with me. I'm just trying to find myself, y'know?");
    State state7 = new State("Man, alright. Let's just focus on the tour? This is already punishing enough without you making it worse.");
    State state8 = new State("Did you move from the middle of nowhere? New [guy/girl], you're rather mysterious.");
    State state9 = new State("Very cool. Anyways, back to the tour.", Emotion.CASUAL);

    Response response = new Response("Yes", state3);
    state1.Responses.add(response);
    state1.Responses.add(new Response("Its' Obvious that I'm just too awesome to ignore", state2));

    state2.Responses.add(new Response("", state3));

    state3.Responses.add(new Response("", state4));

    state4.Responses.add(new Response("I think it is nice.", state5));
    state4.Responses.add(new Response("So what do you do all the time?", state6));

    state5.Responses.add(new Response("Yeah, I'm serious!", state7));
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
    State state1 = new State("よっ、そこの新人。元気？何でこいつは俺に話しかけてるんだろうとか思ってるだろ？", Emotion.CASUAL);
    State state2 = new State("謙虚って言葉知ってるか？とにかく、", Emotion.EMBARRASSED);
    State state3 = new State("まぁ学校からお前を案内するよう言われてんだよ。授業を堂々とさぼれて非常に光栄なんだけどな。", Emotion.CASUAL);
    State state4 = new State("この地味な町はもう見て回ったか？ここは本当退屈だ。", Emotion.EMBARRASSED);
    State state5 = new State("ごめん、何て？頭の中の目覚まし止めるので必死だったわ。本気で言ってる？");
    State state6 = new State("そうだな、いいコーヒーを売る店があるからそこによく行くな。あとウクレレ弾いたり同居人のトゥウィンキー盗んだり。まぁそんな感じだ。");
    State state7 = new State("はいはい。もう案内始めるぞ？これ以上の馬鹿な発言には付き合いきれん。");
    State state8 = new State("お前の住んでたとこ何も無かったのか？何か訳アリっぽいな、新人。");
    State state9 = new State("だろ？まぁとにかく案内に戻るとするか。", Emotion.CASUAL);

    Response response = new Response("うん。", state3);
    state1.Responses.add(response);
    state1.Responses.add(new Response("滲み出るオーラはやっぱ隠し切れないか。", state2));

    state2.Responses.add(new Response("", state3));

    state3.Responses.add(new Response("", state4));

    state4.Responses.add(new Response("いい町だと思うけど。", state5));
    state4.Responses.add(new Response("それじゃいつも何してるの？", state6));

    state5.Responses.add(new Response("本気と書いてマジ！ ", state7));
    state5.Responses.add(new Response("あー、いや何でもないよ。", state8));

    state6.Responses.add(new Response("へー、いいね。", state9));

    state7.Responses.add(new Response("", null));

    state8.Responses.add(new Response("", null));

    state9.Responses.add(new Response("", null));

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
  public void onStateChange(final State state, final String customEvent) {
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
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 0, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    mGame.batch.setProjectionMatrix(camera.combined);


    mGame.batch.begin();
    mGame.batch.draw(resourcesManager.schoolBackground, 0, 0, this.camera.viewportWidth, this.camera.viewportHeight);
    this.mDorianSprite.draw(mGame.batch);
    this.mConversationWidget.draw(mGame.batch, delta);
    mGame.batch.end();

    this.mConversationWidget.draw();
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
