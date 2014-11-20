public class Deck {

    public final static int CARDS_IN_DECK = 52;

    private Card[] deck;
    private int cardsUsed;

    /**
     * creates deck with 52 cards
     */
    public Deck() {
        deck = new Card[CARDS_IN_DECK];

        int cardCreated = 0; //keeps track of the cards created so far
        for (int suit = 0; suit <= 3; suit++) {
            for (int value = 1; value <= 13; value++) {
                deck[cardCreated] = new Card(suit, value);
                cardCreated++;
            }
        }
        cardsUsed = 0;
    }

    /**
     * shuffles the cards
     */
    public void shuffle() {
        for (int i = CARDS_IN_DECK - 1; i > 0; i--) {
            int random = (int) (Math.random() * (i + 1));
            Card temp = deck[i];
            deck[i] = deck[random];
            deck[random] = temp;
        }
        cardsUsed = 0;
    }

    /**
     * returns the number of cards left in the deck
     * @return number of cards left in the deck
     */
    public int cardsLeft() {
        return CARDS_IN_DECK - cardsUsed;
    }

    /**
     * Deals one card from the deck
     * @return one card from the deck
     */
    public Card getTopCard () {
        if (cardsUsed == CARDS_IN_DECK)
            shuffle();
        cardsUsed++;
        return deck[cardsUsed - 1];
    }

    public String toString() {
        return "There are " + cardsLeft() + " left in the deck";
    }

}
