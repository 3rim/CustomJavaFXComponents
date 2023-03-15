package org.erim.components;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ToggleSwitch extends Region {

    private   double PREFERRED_WIDTH = 100;
    private   double PREFERRED_HEIGHT = 50;

    private final BooleanProperty switchedOn = new SimpleBooleanProperty(false);
    private final TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
    private final FillTransition fillAnimation = new FillTransition(Duration.seconds(0.25));
    private final ParallelTransition animation = new ParallelTransition(translateAnimation,fillAnimation);

    private double width;
    private double height;
    private Rectangle background;
    private Circle trigger;


    public ToggleSwitch(){

        width = PREFERRED_WIDTH;
        height = PREFERRED_HEIGHT;
        background = new Rectangle(width,height);
        background.setFill(Color.WHITE);
        background.setStroke(Color.LIGHTGRAY);
        background.setArcWidth(50);
        background.setArcHeight(50);


        trigger = new Circle(height/2);
        trigger.setCenterX(height/2);
        trigger.setCenterY(height/2);
        trigger.setFill(Color.WHITE);
        trigger.setStroke(Color.LIGHTGRAY);

        translateAnimation.setNode(trigger);
        fillAnimation.setShape(background);

        getChildren().addAll(background,trigger);

        switchedOn.addListener((observableValue, oldState, newState) -> {
            boolean isOn = newState;
            translateAnimation.setToX(isOn ? getWidth()-trigger.getRadius()*2 : 0);
            fillAnimation.setFromValue(isOn ? Color.WHITE : Color.LIGHTGREEN);
            fillAnimation.setToValue(isOn ? Color.LIGHTGREEN : Color.WHITE);
            animation.play();
        });

        setOnMouseClicked(event -> {
            switchedOn.set(!switchedOn.get());
        });

    }


    @Override
    protected void layoutChildren(){
        double width = getWidth();
        double height= getHeight();
        background.setWidth(width);
        background.setHeight(height);
        background.setArcWidth(height);
        background.setArcHeight(height);

        trigger.setRadius(height/2);
        trigger.setCenterX(height/2);
        trigger.setCenterY(height/2);
    }

    public BooleanProperty switchedOnProperty() {
        return switchedOn;
    }

}
