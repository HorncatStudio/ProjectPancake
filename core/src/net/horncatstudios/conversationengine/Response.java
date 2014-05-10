package net.horncatstudios.conversationengine;

/**
 * Created by Angelina on 5/9/2014.
 */
public class Response {

  public String Text;
  public State NextState;

  public Relationship RelationshipStats;

  public Response(String text, State nextState) {
    Text = text;
    NextState = nextState;
  }

  final boolean AtEnd() {
    return (null == NextState);
  }
}
