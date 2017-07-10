package client.driving;

import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;
import eu.hansolo.medusa.TickMarkType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class AngleGauge extends Region {

    private static final double PREFERRED_WIDTH = 320;
    private static final double PREFERRED_HEIGHT = 320;
    private static final double MINIMUM_WIDTH = 5;
    private static final double MINIMUM_HEIGHT = 5;
    private static final double MAXIMUM_WIDTH = 1024;
    private static final double MAXIMUM_HEIGHT = 1024;
    private double size;
    private Gauge angleGauge;
    private Pane pane;

    public AngleGauge() {
        init();
        initGraphics();
        registerListeners();
    }

    private void init() {
        if (Double.compare(getPrefWidth(), 0.0) <= 0 || Double.compare(getPrefHeight(), 0.0) <= 0
                || Double.compare(getWidth(), 0.0) <= 0 || Double.compare(getHeight(), 0.0) <= 0) {
            if (getPrefWidth() > 0 && getPrefHeight() > 0) {
                setPrefSize(getPrefWidth(), getPrefHeight());
            } else {
                setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
            }
        }

        if (Double.compare(getMinWidth(), 0.0) <= 0 || Double.compare(getMinHeight(), 0.0) <= 0) {
            setMinSize(MINIMUM_WIDTH, MINIMUM_HEIGHT);
        }

        if (Double.compare(getMaxWidth(), 0.0) <= 0 || Double.compare(getMaxHeight(), 0.0) <= 0) {
            setMaxSize(MAXIMUM_WIDTH, MAXIMUM_HEIGHT);
        }

    }

    private void initGraphics() {
        angleGauge = GaugeBuilder.create()
                .borderPaint(Color.WHITE)
                .foregroundBaseColor(Color.WHITE)
                .startAngle(290)
                .angleRange(220)
                .minValue(60)
                .maxValue(120)
                .valueVisible(false)
                .minorTickMarksVisible(false)
                .majorTickMarkType(TickMarkType.BOX)
                .mediumTickMarkType(TickMarkType.BOX)
                .title("Angle(°)")
                .interactive(true)
                .needleShape(Gauge.NeedleShape.ROUND)
                .needleSize(Gauge.NeedleSize.THICK)
                .needleColor(Color.rgb(234, 67, 38))
                .knobColor(Gauge.DARK_COLOR)
                .customTickLabelsEnabled(true)
                .customTickLabelFontSize(40)
                .customTickLabels("60", "", "", "", "", "", "", "120", "")
                .animated(false)
                .build();
        angleGauge.setSkin(new InteractiveGaugeSkin(angleGauge));

        pane = new Pane(angleGauge);

        getChildren().setAll(pane);
    }
     private void registerListeners() {
        widthProperty().addListener(observable -> resize());
        heightProperty().addListener(observable -> resize());
    }

    public Gauge getAngleGauge() {
        return angleGauge;
    }
    
        private void resize() {
        double width = getWidth() - getInsets().getLeft() - getInsets().getRight();
        double height = getHeight() - getInsets().getTop() - getInsets().getBottom();
        size = width < height ? width : height;

        if (size > 0) {
            pane.setMaxSize(size, size);
            pane.relocate((getWidth() - size) * 0.5, (getHeight() - size) * 0.5);

            angleGauge.setPrefSize(size, size);
        }
    }
}