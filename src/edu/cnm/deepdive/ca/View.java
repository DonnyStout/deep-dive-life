package edu.cnm.deepdive.ca;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class View extends Canvas {

  public void draw(boolean[][] terrain) {
    GraphicsContext context = getGraphicsContext2D();
    double cellSize = Math.min(
        (double) getWidth() / terrain[0].length,
        (double) getHeight() / terrain.length
    );
    context.clearRect(0, 0, getWidth(), getHeight());
    context.setFill(Color.RED);
    for (int i = 0; i < terrain.length; i++) {
      for (int j = 0; j < terrain[i].length; j++) {
        if (terrain[i][j]) {
          context.fillOval(j * cellSize, i * cellSize, cellSize, cellSize);
        }
      }
    }
  }

}
