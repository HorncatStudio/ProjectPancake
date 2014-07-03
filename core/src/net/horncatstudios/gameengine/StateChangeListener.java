package net.horncatstudios.gameengine;

import net.horncatstudios.conversationengine.State;

/**
 * Created by Angelina on 5/20/2014.
 */
public interface StateChangeListener {

  public void onStateChange(final State state);

  public void onStateChange(final State state, final String customEvent);
}
