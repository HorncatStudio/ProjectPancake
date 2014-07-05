package net.horncatstudios.gameengine;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

/**
 * Created by Angelina on 7/5/2014.
 */
public interface IHcScreen extends Screen, InputProcessor {

  public ScreenManager.ScreenType getSceneType();

  public void setCustom(final String event);
}
