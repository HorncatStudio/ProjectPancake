package net.horncatstudios.projectpancake;

/**
 * Created by Angelina on 5/23/2014.
 */
public class SampleOtherObject implements ProjectPancakeMenuActions {
  @Override
  public void StartGame() {
    System.out.println("START");
  }

  @Override
  public void QuitGame() {
    System.out.println("QUIT!");
  }
}
