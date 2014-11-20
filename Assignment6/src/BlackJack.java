/**
 * Computes and returns the value of this hand in the game
 * of Blackjack.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BlackJack {
	
	private Deck deck = new Deck();

    private int allHandsCount;
    private int wonHandsCount;
    private int loseHandsCount;

    public Card[] playerCards = new Card[ Deck.CARDS_IN_DECK ];
    public int playerCardsCount;

    public Card[] dealerCards = new Card[ Deck.CARDS_IN_DECK ];
    public int deaerlCardsCount;

    public BlackJack() {
        deck.shuffle();
    }

    public void makeHand() {
        prepareHand();

        // first deal for player
        dealCardForPlayer();
        dealCardForPlayer();

        // first deal for dealer
        dealCardForDealer();

        BufferedReader reader = new BufferedReader(new InputStreamReader( System.in ));

        printCurrentState();
        while ( true ) {

            System.out.println( "Do you want to (H)it, (S)tand or (Q)uit the hand ?" );

            String line = null;
            try {
                line = reader.readLine();
            } catch( Exception e ) {
                System.out.println( "We have an error" );
                e.printStackTrace();
                return;
            }

            if ( line.equalsIgnoreCase( "Q" ) )
                return;
            else if ( line.equalsIgnoreCase( "S" ) || countCardsValue( playerCards, playerCardsCount ) == 21 ) {
                int dealersValue = dealersTurn();
                printCurrentState();

                if ( dealersValue > 21 || dealersValue < countCardsValue( playerCards, playerCardsCount ) ) {
                    System.out.println( "You won this hand !\n" );
                    allHandsCount++;
                    wonHandsCount++;
                } else if ( dealersValue > countCardsValue( playerCards, playerCardsCount ) ) {
                    System.out.println( "You lose this hand\n" );
                    allHandsCount++;
                    loseHandsCount++;
                } else {
                    System.out.println( "Nobody won\n" );
                    allHandsCount++;
                }

                return;
            } else if ( line.equalsIgnoreCase( "H" ) ) {
                dealCardForPlayer();
                printCurrentState();

                if ( countCardsValue( playerCards, playerCardsCount ) > 21 ) {
                    System.out.println( "You lose this hand\n" );
                    allHandsCount++;
                    loseHandsCount++;
                    return;
                }
            }
        }
    }

    private int dealersTurn() {
        while ( countCardsValue( dealerCards, deaerlCardsCount ) < 16 ) {
            dealCardForDealer();
        }

        return countCardsValue( dealerCards, deaerlCardsCount );
    }

    private int countCardsValue( Card[] cards, int cardsCount ) {
        int acesCount = 0;
        int cardsValue = 0;

        for ( int i = 0; i < cardsCount; i++ ) {
            Card card = cards[i];

            if ( card.getCard() == Card.Ace )
                acesCount++;
            else
                cardsValue += card.getCard() > 10 ? 10 : card.getCard();
        }

        if ( acesCount == 0 )
            return cardsValue;
        else if ( (cardsValue + acesCount) > 21 )
            return cardsValue + acesCount;
        else {
            for ( int i = 0; i < acesCount; i++ ) {
                if ( (cardsValue + 11) > 21 )
                    cardsValue++;
                else cardsValue += 11;
            }
        }

        return cardsValue;
    }

    private void dealCardForPlayer() {
        playerCards[ playerCardsCount++ ] = getNextCard();
    }

    private void dealCardForDealer() {
        dealerCards[ deaerlCardsCount++ ] = getNextCard();
    }

    /**
     * Prints the current state of the hand
     */
    private void printCurrentState() {
        System.out.println( "Dealer: " );
        printCards( dealerCards, deaerlCardsCount );
        System.out.println( "Dealer value: " + countCardsValue( dealerCards, deaerlCardsCount ) );

        System.out.println( "\nPlayer cards: " );
        printCards( playerCards, playerCardsCount );
        System.out.println( "Player value: " + countCardsValue( playerCards, playerCardsCount ) + "\n" );
    }

    private void printCards( Card[] cards, int count ) {
        for ( int i = 0; i < count; i++ )
            System.out.println( cards[i] );
    }

    /**
     * Get the next card from the deck. If there is no cards left in the deck -
     * we take the new deck (We just shuffle the oldest - it`s the same thing)
     * @return the next card from the deck
     */
    private Card getNextCard() {
        if ( deck.cardsLeft() == 0 )
            deck.shuffle();

        return deck.getTopCard();
    }

    private void prepareHand() {
        for ( int i = 0; i < playerCards.length; i++ )
            playerCards[i] = dealerCards[i] = null;

        playerCardsCount = deaerlCardsCount = 0;
    }

    public void printStatistics() {
        System.out.println( "You played " + allHandsCount +
                            " hands. You won " + wonHandsCount + " of them. " +
                            "You lose " + loseHandsCount + " of them.");
    }

    public void printDeck() {
        System.out.println( deck.toString() );

        // copy cards into the new array
        Card[] sortedCards = new Card[ deck.cardsLeft() ];

        for ( int i = 0; deck.cardsLeft() > 0; i++ )
            sortedCards[i] = deck.getTopCard();

        Arrays.sort( sortedCards );

        for ( int i = 0; i < sortedCards.length; i++ )
            System.out.println( sortedCards[i] );
    }
}

    	   