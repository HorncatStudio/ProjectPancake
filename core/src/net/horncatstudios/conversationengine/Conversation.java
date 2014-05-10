package net.horncatstudios.conversationengine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angelina on 5/9/2014.
 */
public class Conversation {
  public List<State> ConversationStates;

  public Conversation() {
    ConversationStates = new ArrayList<State>();
  }
}
