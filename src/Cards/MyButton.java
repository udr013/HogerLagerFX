package Cards;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;

/**
 * Created by udr013 on 6-2-2016.
 */
public  class MyButton extends Button{
    public MyButton(String a){
        setText(a);
        setTextFill( Color.WHITE );
        minWidth(300.0);
        setStyle( "-fx-font: 22 italic; -fx-base: #000000; " );
        //setBlendMode(BlendMode.LIGHTEN);

    }
    public MyButton(String a, Pos b){
        setAlignment(b);
        setText(a);
        setTextFill( Color.WHITE );
        minWidth(300.0);
        setStyle( "-fx-font: 22 italic; -fx-base: #000000; " );

    }


//    public void addEventHandler(Main.AfbrekenEventHandler afbrekenEventHandler) {
//    }
}