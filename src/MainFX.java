import Cards.Card;
import Cards.CardDeck;
import Cards.MyButton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
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
    HBox buttonGroup;

    @Override
    public void start(Stage primaryStage) throws Exception {


        MyButton hogerButton = new MyButton("Hoger");
        MyButton lagerButton = new MyButton("Lager");

        MyButton jaButton = new MyButton("Ja");
        MyButton neeButton = new MyButton("Nee");
        MyButton nextCardButton = new MyButton("Volgende Kaart");

        Text instruction = new Text("  Doe een gok of de volgende kaart hoger of lager is  ");
        instruction.setFill(Paint.valueOf("#ffffff"));
        instruction.setFont(Font.font(24));


        buttonGroup = new HBox(15, hogerButton, lagerButton);
        buttonGroup.setPadding(new Insets(0, 0, 30, 0));
        HBox cardGroup = new HBox(30, oldCardImage, newCardImage);

        HBox textHBox = new HBox(instruction);
        textHBox.setMinHeight(40);
        textHBox.maxWidthProperty().bind(instruction.wrappingWidthProperty().add(49));
        textHBox.setAlignment(Pos.CENTER);
        textHBox.setStyle("-fx-background-color: black; -fx-background-radius: 30; -fx-border-color: darkgrey; -fx-border-radius: 30 ");
        textHBox.setOpacity(0.8);

        VBox groups = new VBox(30, cardGroup, textHBox, buttonGroup);
        groups.setPadding(new Insets(30, 30, 0, 30));

        //oldCardImage.fitHeightProperty().bind(cardGroup.heightProperty());
        //oldCardImage.isPreserveRatio();

        Image backCard = new Image("Cards/AllWheel/background2.png");
        backCard.isPreserveRatio();
        //ImageView backgroundImage = new ImageView(backCard);
        //backgroundImage.setStyle(";-fx-background-repeat: repeat;");
        StackPane background = new StackPane(groups);
        background.setStyle("-fx-background-image: url('Cards/AllWheel/background.png');-fx-background-repeat: repeat;");
        //backgroundImage.fitWidthProperty().bind(primaryStage.widthProperty());
        // backgroundImage.fitHeightProperty().bind(primaryStage.heightProperty());

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
            buttonGroup.getChildren().clear();
            buttonGroup.getChildren().add(nextCardButton);

        });

        lagerButton.setOnAction(e -> {
            instruction.setText("  Je hebt LAGER Gekozen, neem volgende kaart  ");
            buttonGroup.getChildren().clear();
            buttonGroup.getChildren().add(nextCardButton);
        });

        nextCardButton.setOnAction(e -> {

            previousCard = newCard;
            oldCardImage.setImage(new Image(previousCard.cardImage));
            cards.remove(newCard);
            newCard = getNewCard();
            newCardImage.setImage(new Image(newCard.cardImage));
            String result = getResult();

            int size = cards.getSize();
            buttonGroup.getChildren().clear();
            if (size <= 1) {
                instruction.setText(result + "  De Kaarten zijn op nog een spelletje?  ");
                buttonGroup.getChildren().addAll(jaButton, neeButton);
            } else {

                buttonGroup.getChildren().addAll(hogerButton, lagerButton);
                instruction.setText(result + " Doe een gok of de volgende kaart hoger of lager is er zijn nog: " + size + " kaarten ");
            }
        });

        cardGroup.setAlignment(Pos.CENTER);
        buttonGroup.setAlignment(Pos.CENTER);
        groups.setAlignment(Pos.CENTER);

        primaryStage.setTitle("HogerLager");
        primaryStage.setScene(new Scene(background));
        primaryStage.show();
    }

    private String getResult() {
        if (previousCard.cardValue < newCard.cardValue) {
            return "  De nieuwe  kaart is hoger.";
        } else if (previousCard.cardValue == newCard.cardValue) {
            return "  De nieuwe  kaart is gelijk.";
        } else {
            return "  De nieuwe  kaart is lager.";
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

