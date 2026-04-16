package edu.kis.powp.jobs2d.canvas;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public enum PaperFormat implements CanvasFormat {
    A4(210, 297),
    B3(353, 500);

    private final int width;
    private final int height;

    PaperFormat(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Shape getShape() {
        return new Rectangle2D.Double((double) -width / 2, (double) -height / 2, width, height);
    }

    @Override
    public String getName() {
        return "Paper Format Canvas (" + name() + " - " + width + "x" + height + ")";
    }
}
