package net.horncatstudios.gameengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import net.horncatstudios.projectpancake.FontResourceManager;
import net.horncatstudios.projectpancake.HcLocale;
import net.horncatstudios.projectpancake.ProjectPancakeMenuActions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angelina on 5/10/2014.
 */
public class MainMenu implements InputProcessor {

  //! The stage responsible for managing the main menu table
  private Stage mStage;

  private ProjectPancakeMenuActions mMenuActions;

  Label mTitleLabel;
  Label mSubTitleLabel;

  List<TextButton> mMenuOptions;

  int mCurrentSelectedIndex;

  public MainMenu(final FontResourceManager fontManager, final ProjectPancakeMenuActions actions) {
    this.mMenuActions = actions;
    this.mMenuOptions = new ArrayList<TextButton>();

    this.mStage = new Stage();

    if (HcLocale.getCurrentLocale() == HcLocale.Locale.EN) {
      this.mTitleLabel = new com.badlogic.gdx.scenes.scene2d.ui.Label("Project", fontManager.MainMenuGameTitleStyle);
    } else {
      this.mTitleLabel = new com.badlogic.gdx.scenes.scene2d.ui.Label("ぷろじぇくと", fontManager.MainMenuGameTitleStyle);
    }
    this.mTitleLabel.setFontScale(1f);
    this.mTitleLabel.setAlignment(Align.center | Align.bottom);

    if (HcLocale.getCurrentLocale() == HcLocale.Locale.EN) {
      this.mSubTitleLabel = new com.badlogic.gdx.scenes.scene2d.ui.Label("PANCAKE", fontManager.MainMenuGameTitleStyleBig);
    } else {
      this.mSubTitleLabel = new com.badlogic.gdx.scenes.scene2d.ui.Label("パンケーキ", fontManager.MainMenuGameTitleStyleBig);
    }

    //! Note: Angelina -
    //! Aligning to the bottom actually makes it align to the top of the cell for the label
    this.mSubTitleLabel.setAlignment(Align.bottom);

    Table table = new Table();
    //table.debug(); uncomment for table debugging
    table.setFillParent(true);
    table.setSkin(fontManager.MainMenuButtonSkin);
    this.mStage.addActor(table);

    // Adding empty cells to improve spacing for the main menu
    table.add().expand();
    table.add().expand();
    table.row();

    table.add(mTitleLabel).expandX().size(0, 60);
    table.row();

    table.add(mSubTitleLabel).expandX()/*.size(0,60)*/;
    table.row();

    // Adding empty cells to improve spacing for the main menu
    table.add().expand();
    table.row();

    // register the button "start game"
    TextButton startGameButton = null;
    if (HcLocale.getCurrentLocale() == HcLocale.Locale.EN) {
      startGameButton = new TextButton("Start game", fontManager.MainMenuButtonSkin);
    } else {
      startGameButton = new TextButton("すたーと", fontManager.MainMenuButtonSkin);
    }
    startGameButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        mMenuActions.StartGame();
      }
    });

    table.add(startGameButton).uniform();
    this.mMenuOptions.add(startGameButton);
    table.row();

    // register the button "quit game" - leaving with clicked as an example of using both
    TextButton quitGameButton = null;
    if (HcLocale.getCurrentLocale() == HcLocale.Locale.EN) {
      quitGameButton = new TextButton("Quit game", fontManager.MainMenuButtonSkin);
    } else {
      quitGameButton = new TextButton("ヤメル", fontManager.MainMenuButtonSkin);
    }
    quitGameButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        mMenuActions.QuitGame();
      }
    });
    table.add(quitGameButton).uniform();
    this.mMenuOptions.add(quitGameButton);
    table.row();

    // Adding empty cells to improve spacing for the main menu
    table.add().expand();
    table.row();

    setSelectedIndex(0);
  }

  public Stage getStage() {
    return mStage;
  }

  public void draw() {
    // Table.drawDebug(mStage); uncomment for table debugging placement
    mStage.draw();
  }

  private void setSelectedIndex(final int selectedIndex) {
    for (int buttonIndex = 0; buttonIndex < this.mMenuOptions.size(); buttonIndex++) {
      if (selectedIndex == buttonIndex)
        this.mMenuOptions.get(buttonIndex).setChecked(true);
      else
        this.mMenuOptions.get(buttonIndex).setChecked(false);
    }
    this.mCurrentSelectedIndex = selectedIndex;
  }

  private void moveSelectionUp() {
    if (this.mCurrentSelectedIndex == 0)
      return;

    setSelectedIndex(this.mCurrentSelectedIndex - 1);
  }

  private void moveSelectionDown() {
    if (this.mCurrentSelectedIndex == this.mMenuOptions.size())
      return;

    setSelectedIndex(this.mCurrentSelectedIndex + 1);
  }

  public TextButton currentButtonSelected() {
    return this.mMenuOptions.get(this.mCurrentSelectedIndex);
  }

  private void triggerMenuSelection() {
    TextButton button = currentButtonSelected();
    if (button.getText().toString().contains("Start game") || button.getText().toString().contains("すたーと"))
      mMenuActions.StartGame();
    else
      mMenuActions.QuitGame();
  }


  @Override
  public boolean keyDown(int keycode) {
    switch (keycode) {
      case Input.Keys.UP:
        moveSelectionUp();
        break;
      case Input.Keys.DOWN:
        moveSelectionDown();
        break;
      case Input.Keys.ENTER:
      case Input.Keys.BUTTON_A:
        triggerMenuSelection();
        break;
    }

    Gdx.app.log("touch", "Event key: " + keycode);

    //! So it shall look for other events to process
    return false;
  }

  @Override
  public boolean keyUp(int keycode) {
    return false;
  }

  @Override
  public boolean keyTyped(char character) {
    return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
    return false;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
    return false;
  }

  @Override
  public boolean scrolled(int amount) {
    return false;
  }
}
