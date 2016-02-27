package Cards;

import java.util.ArrayList;

/**
 * Created by udr013 on 23-2-2016.
 */
public class CardDeck {
    ArrayList<Card> cards = new ArrayList<>();

    public CardDeck() {
        //een ArrayList van kaarten en waardes word aangemaakt

        String[] suite = {"Harten", "Klaveren", "Schoppen", "Ruiten"};

        int y = 2;

        for (String x : suite) {
            cards.add(new Card(y, x + " 2", "Cards/AllWheel/" + x + "_2.png"));
            cards.add(new Card(++y, x + " 3", "Cards/AllWheel/" + x + "_3.png"));
            cards.add(new Card(++y, x + " 4", "Cards/AllWheel/" + x + "_4.png"));
            cards.add(new Card(++y, x + " 5", "Cards/AllWheel/" + x + "_5.png"));
            cards.add(new Card(++y, x + " 6", "Cards/AllWheel/" + x + "_6.png"));
            cards.add(new Card(++y, x + " 7", "Cards/AllWheel/" + x + "_7.png"));
            cards.add(new Card(++y, x + " 8", "Cards/AllWheel/" + x + "_8.png"));
            cards.add(new Card(++y, x + " 9", "Cards/AllWheel/" + x + "_9.png"));
            cards.add(new Card(++y, x + " 10", "Cards/AllWheel/" + x + "_10.png"));
            cards.add(new Card(++y, x + " Boer", "Cards/AllWheel/" + x + "_Boer.png"));
            cards.add(new Card(++y, x + " Vrouw", "Cards/AllWheel/" + x + "_Vrouw.png"));
            cards.add(new Card(++y, x + " Heer", "Cards/AllWheel/" + x + "_Heer.png"));
            cards.add(new Card(++y, x + " Aas", "Cards/AllWheel/" + x + "_Aas.png"));
            y = 2;
        }
    }

    public Card getCard() {
        int rand = (int) (Math.random() * cards.size());
        return cards.get(rand);
    }


    public int getSize() {
        return cards.size();
    }

    public void remove(Card removeCard) {
        cards.remove(removeCard);
    }
}
