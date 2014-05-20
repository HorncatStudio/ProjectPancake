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

  public State(String text) {
    Text = text;
    Responses = new ArrayList<Response>();
  }

  public State(String text, Emotion emotion) {
    Text = text;
    Responses = new ArrayList<Response>();
    Emotion = emotion;
  }

}
