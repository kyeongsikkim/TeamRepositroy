package client.driving;

import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.Gauge.NeedleShape;
import eu.hansolo.medusa.Gauge.NeedleSize;
import eu.hansolo.medusa.Gauge.SkinType;
import eu.hansolo.medusa.GaugeBuilder;
import eu.hansolo.medusa.TickMarkType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class SpeedGauge extends Region {

    private static final double PREFERRED_WIDTH = 320;
    private static final double PREFERRED_HEIGHT = 320;
    private static final double MINIMUM_WIDTH = 5;
    private static final double MINIMUM_HEIGHT = 5;
    private static final double MAXIMUM_WIDTH = 1024;
    private static final double MAXIMUM_HEIGHT = 1024;
    private double size;
    private Gauge speedGauge;
    private Gauge motorGauge;
    private Gauge oilGauge;
    private Pane pane;

    //-------------------------------------------------------------------------------------------
    public SpeedGauge() {
        init();
        initGraphics();
        registerListeners();
    }
    // ******************** Initialization ************************************

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
        speedGauge = GaugeBuilder.create()
                .borderPaint(Color.WHITE)
                .foregroundBaseColor(Color.WHITE)
                .startAngle(290)
                .angleRange(220)
                .minValue(0)
                .maxValue(4000)
                .valueVisible(false)
                .minorTickMarksVisible(false)
                .majorTickMarkType(TickMarkType.BOX)
                .mediumTickMarkType(TickMarkType.BOX)
                .title("Speed")
                .interactive(true)
                .needleShape(NeedleShape.ROUND)
                .needleSize(NeedleSize.THICK)
                .needleColor(Color.rgb(234, 67, 38))
                .knobColor(Gauge.DARK_COLOR)
                .customTickLabelsEnabled(true)
                .customTickLabelFontSize(40)
                .customTickLabels("0", "", "10", "", "20", "", "30", "", "40")
                .animated(false)
                .build();
        speedGauge.setSkin(new InteractiveGaugeSkin(speedGauge));

        motorGauge = GaugeBuilder.create()
                .skinType(SkinType.HORIZONTAL)
                .prefSize(170, 170)
                .autoScale(false)
                .foregroundBaseColor(Color.WHITE)
                .valueVisible(false)
                .angleRange(90)
                .minValue(0)
                .maxValue(180)
                .needleShape(NeedleShape.ROUND)
                .needleSize(NeedleSize.THICK)
                .needleColor(Color.rgb(234, 67, 38))
                .minorTickMarksVisible(false)
                .mediumTickMarksVisible(false)
                .majorTickMarkType(TickMarkType.BOX)
                .knobColor(Gauge.DARK_COLOR)
                .customTickLabelsEnabled(true)
                .customTickLabelFontSize(36)
                .customTickLabels("", "", "", "", "", "")
                .animated(true)
                .build();

        oilGauge = GaugeBuilder.create()
                .skinType(SkinType.HORIZONTAL)
                .prefSize(400, 400)
                .foregroundBaseColor(Color.WHITE)
                .title("Oil")
                .valueVisible(false)
                .angleRange(90)
                .needleShape(NeedleShape.ROUND)
                .needleSize(NeedleSize.THICK)
                .needleColor(Color.rgb(234, 67, 38))
                .minorTickMarksVisible(false)
                .mediumTickMarksVisible(false)
                .majorTickMarkType(TickMarkType.BOX)
                .knobColor(Gauge.DARK_COLOR)
                .customTickLabelsEnabled(true)
                .customTickLabelFontSize(36)
                .customTickLabels("F", "", "", "", "", "", "", "", "", "", "E")
                .animated(true)
                .build();

        pane = new Pane(motorGauge, oilGauge, speedGauge);

        getChildren().setAll(pane);
    }

    private void registerListeners() {
        widthProperty().addListener(observable -> resize());
        heightProperty().addListener(observable -> resize());
    }

    // ******************** Methods *******************************************
    public Gauge getSpeedGauge() {
        return speedGauge;
    }

    public Gauge getMotorGauge() {
        return motorGauge;
    }

    public Gauge getOilGauge() {
        return oilGauge;
    }

    // ******************** Resizing ******************************************
    private void resize() {
        double width = getWidth() - getInsets().getLeft() - getInsets().getRight();
        double height = getHeight() - getInsets().getTop() - getInsets().getBottom();
        size = width < height ? width : height;

        if (size > 0) {
            pane.setMaxSize(size, size);
            pane.relocate((getWidth() - size) * 0.5, (getHeight() - size) * 0.5);

            speedGauge.setPrefSize(size, size);

            motorGauge.setPrefSize(size * 0.425, size * 0.425);
            motorGauge.relocate(size * 0.1, size * 0.5625);

            oilGauge.setPrefSize(size * 0.425, size * 0.425);
            oilGauge.relocate(size * 0.475, size * 0.5625);
        }
    }
}
