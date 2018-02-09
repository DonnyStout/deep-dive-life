package edu.cnm.deepdive.ca;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;

public class Controller {

  private static final int UPDATE_INTERVAL = 10;
  private Model model;
  private boolean running = false;
  private Runner runner = null;
  private boolean updatePending = false;

  @FXML
  private View terrainView;

  @FXML
  private Slider densitySlider;

  @FXML
  private Button resetButton;

  @FXML
  private ToggleButton runForeverButton;

  @FXML
  private Button runOnceButton;

  @FXML
  private void reset() {
    model.populate(densitySlider.getValue() / 100);
    updateView();

  }

  @FXML
  private void runOnce() {
    model.advance();
    updateView();
  }

  @FXML
  private void runForever() {
    if (runForeverButton.isSelected()) {
      resetButton.setDisable(true);
      runOnceButton.setDisable(true);
      running = true;
      runner = new Runner();
      runner.start();
    } else {
      running = false;
      runner = null;
      resetButton.setDisable(false);
      runOnceButton.setDisable(false);
    }
  }

  public Model getModel() {
    return model;
  }

  public void setModel(Model model) {
    this.model = model;
  }

  private void updateView() {
    boolean[][] terrain;
    synchronized (model) {
      terrain = model.getTerrain();

    }
    terrainView.draw(terrain);
    updatePending = false;
  }

  private class Runner extends Thread {

    @Override
    public void run() {
      int currentGeneration;
      while (running) {
        synchronized (model) {
          model.advance();
          currentGeneration = model.getGeneration();
        }
        if (!updatePending && currentGeneration % UPDATE_INTERVAL == 0) {
          updatePending = true;
          Platform.runLater(new Runnable() {
            @Override
            public void run() {
              updateView();
            }
          });
        }
      }
      updatePending = true;
      Platform.runLater(new Runnable() {
        @Override
        public void run() {
          updateView();
        }
      });
    }

  }

}
