import Cards.Card;
import Cards.CardDeck;
import Cards.MyButton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by udr013 on 23-2-2016.
 */
public class MainFX extends Application {

    CardDeck cards = new CardDeck();

    Card previousCard;
    private String previousCardImage = "Cards/AllWheel/_Back.png";
    Card newCard = cards.getCard();
    Image card1 = new Image(previousCardImage);
    Image card2 = new Image(newCard.cardImage);
    ImageView oldCardImage = new ImageView(card1);
    ImageView newCardImage = new ImageView(card2);
    ImageView counterField = new ImageView("Cards/AllWheel/counterBackground.png");
    ImageView scoreField = new ImageView("Cards/AllWheel/counterBackground.png");
    MyButton jaButton = new MyButton("Ja");
    MyButton neeButton = new MyButton("Nee");
    MyButton hogerButton = new MyButton("Hoger");
    MyButton lagerButton = new MyButton("Lager");
    HBox buttonGroup;
    HBox remainText;
    HBox scoreText;
    Text instruction;
    StackPane test;
    StackPane test2;
    int score=0;
    boolean guess;

    @Override
    public void start(Stage primaryStage) throws Exception {


        instruction = new Text("  Doe een gok of de volgende kaart hoger of lager is  ");
        instruction.setFill(Paint.valueOf("#ffffff"));
        instruction.setFont(Font.font(24));

        remainText  = getDigits(cards.getSize(),Color.BLUE);
        scoreText  = getDigits(score, Color.DARKGREEN);

        test = new StackPane(counterField,remainText);
        test2 = new StackPane(scoreField,scoreText);

        VBox testField= new VBox(test,test2);
        testField.setPadding(new Insets(30,30,30,30));

        buttonGroup = new HBox(15, hogerButton, lagerButton);
        buttonGroup.setPadding(new Insets(0, 0, 30, 0));
        HBox cardGroup = new HBox(30, oldCardImage, newCardImage,testField);

        HBox textHBox = new HBox(instruction);
        textHBox.setMinHeight(40);
        textHBox.maxWidthProperty().bind(instruction.wrappingWidthProperty().add(49));
        textHBox.setAlignment(Pos.CENTER);
        textHBox.setStyle("-fx-background-color: black; -fx-background-radius: 30; -fx-border-color: darkgrey; -fx-border-radius: 30 ");
        textHBox.setOpacity(0.8);

        VBox groups = new VBox(30, cardGroup, textHBox, buttonGroup);
        groups.setAlignment(Pos.CENTER);

        groups.setPadding(new Insets(30, 30, 0, 30));

        StackPane background = new StackPane(groups);
        background.setStyle("-fx-background-image: url('Cards/AllWheel/background.png');-fx-background-repeat: repeat;");


        jaButton.setOnAction(e -> {
            try {
                start(primaryStage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            cards = new CardDeck();
        });

        neeButton.setOnAction(e -> System.exit(0));


        hogerButton.setOnAction(e -> {
            instruction.setText("  Je hebt HOGER Gekozen, neem volgende kaart  ");
            guess = true;
            getUpdatedScreen();

        });

        lagerButton.setOnAction(e -> {
            instruction.setText("  Je hebt LAGER Gekozen, neem volgende kaart  ");
            guess= false;
            getUpdatedScreen();
        });


        cardGroup.setAlignment(Pos.CENTER);
        buttonGroup.setAlignment(Pos.CENTER);
        groups.setAlignment(Pos.CENTER);

        primaryStage.setTitle("HogerLager");
        primaryStage.setScene(new Scene(background));
        primaryStage.show();
    }

    private HBox getDigits(int size, Color color) {
        HBox digitBox = new HBox();
        String digits = ""+(size);
        if (digits.length()==1){
            digits ="0"+digits;
        }else {
            digits = ""+(size);
        }

        if(Integer.parseInt(digits)<=0){
            color =Color.CRIMSON;
        }
        String firstnr =  digits.substring(0,1);
        ImageView firstDigit = new ImageView("Cards/Digits/clock_stopwatch_digit_"+ firstnr+".png");
        firstDigit.setEffect(getLighting(color));

        String secondnr = digits.substring(1,2);
        ImageView secondDigit = new ImageView("Cards/Digits/clock_stopwatch_digit_"+ secondnr+".png");
        secondDigit.setEffect(getLighting(color));

        digitBox.getChildren().addAll(firstDigit,secondDigit);
        digitBox.setAlignment(Pos.CENTER);
        return digitBox;

    }
    public void getUpdatedScreen(){

        previousCard = newCard;
        oldCardImage.setImage(new Image(previousCard.cardImage));
        cards.remove(newCard);
        newCard = getNewCard();
        newCardImage.setImage(new Image(newCard.cardImage));
        getResult();

        int size = cards.getSize();

        remainText= getDigits(cards.getSize(), Color.BLUE);
        scoreText= getDigits(score, Color.DARKGREEN);

        test.getChildren().clear();
        test.getChildren().addAll(counterField,remainText);

        test2.getChildren().clear();
        test2.getChildren().addAll(scoreField,scoreText);


        if (size <= 1) {
            instruction.setText("  De Kaarten zijn op nog een spelletje?  ");
            buttonGroup.getChildren().clear();
            buttonGroup.getChildren().addAll(jaButton, neeButton);
        } else {
            instruction.setText(" Doe een gok of de volgende kaart hoger of lager is er zijn nog: " + size + " kaarten ");
        }
    }


    private Effect getLighting(Color mycolor) {
        Light.Distant light = new Light.Distant();
        light.setColor(mycolor);
        light.setAzimuth(-135.0);
        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);
        return lighting;
    }

    private int getResult() {
        if (((newCard.cardValue > previousCard.cardValue)&&guess)||((newCard.cardValue < previousCard.cardValue)&&!guess)){
            return score++;
        } else if (previousCard.cardValue == newCard.cardValue) {
            return score;
        } else {
            return score--;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    public Card getNewCard() {
        newCard = cards.getCard();
        return newCard;
    }
}

