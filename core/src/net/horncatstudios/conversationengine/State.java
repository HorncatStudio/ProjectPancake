package net.horncatstudios.conversationengine;

import java.util.ArrayList;
import java.util.List;

/**
 * \brief
 */
public class State {
  public String Text;
  public Emotion Emotion;
  public List<Response> Responses;

  //! An extra field to indicate a special action that can be customized for extra usgae
  public String CustomEvent;

  public State(String text) {
    Text = text;
    Responses = new ArrayList<Response>();
    CustomEvent = "";
  }

  public State(String text, Emotion emotion) {
    this(text);
    Emotion = emotion;
  }
}
