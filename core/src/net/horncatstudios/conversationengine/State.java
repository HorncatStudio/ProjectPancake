package net.horncatstudios.conversationengine;

import java.util.ArrayList;
import java.util.List;

/**
 * \brief
 */
public class State {
  public String Text;
  public String Emotion;
  public List<Response> Responses;

  public State(String text) {
    Text = text;
    Responses = new ArrayList<Response>();
  }

}
