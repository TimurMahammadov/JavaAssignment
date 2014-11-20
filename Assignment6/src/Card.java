
public class Card {
	 private String suitValue;
//   private Card cardValue;

   //constants for the suits of the cards
   public final static int Spades = 0;
   public final static int Hearts = 1;
   public final static int Diamonds = 2;
   public final static int  Clubs = 3;

   // suite and value of the card
   private final int suit;
   private final int value;

   //constants for non numerical cards
   public final static int Ace = 1;
   public final static int Jack = 11;
   public final static int Queen = 12;
   public final static int King = 13;

   /**
    * Constructor creating new playing card, with suit and a value
    * @param suitValue suite value
    * @param cardValue card value
    */
   public Card(int suitValue, int cardValue) {
       suit = suitValue;
       value = cardValue;
   }

   /**
    * method returning the suit of the card
    * @return suit of the card
    */
   public int getSuit() {
       return suit;
   }

   /**
    * method returning the value of the card
    * @return the value of the card
    */
   public int getCard() {
       return value;
   }

   /**
    * method for a string representation of the card suit
    * @return string representation of the card suit
    */
   public String cardSuitToString() {
       switch (suit) {
           case 0:
               return "Spades";
           case 1:
               return "Hearts";
           case 2:
               return "Diamonds";
           case 3:
               return "Clubs";
           default:
               return "unknown";
       }
   }

   /**
    * Return a String representing the card's value.
    * @return String representing the card's value.
    */
   public String getValueAsString() {
       switch (value) {
           case 1:
               return "Ace";
           case 2:
               return "2";
           case 3:
               return "3";
           case 4:
               return "4";
           case 5:
               return "5";
           case 6:
               return "6";
           case 7:
               return "7";
           case 8:
               return "8";
           case 9:
               return "9";
           case 10:
               return "10";
           case 11:
               return "Jack";
           case 12:
               return "Queen";
           case 13:
               return "King";
           default:
               return "unknown";
       }
   }


   /**
    * compares 2 cards so they can be sorted for the bonus offered
    * @param obj object to compare
    * @return negative if object2 < this, if they are equal, positive othervise
    */
   public int compareTo(Object obj) {

       if ( !(obj instanceof Card ) )
           throw new IllegalArgumentException( "You can compare only with Card" );

       Card otherCard = (Card) obj;

       if ( suit > otherCard.suit  )
           return 1;
       if ( suit == otherCard.suit ) {
           if ( value > otherCard.value )
               return 1;
           if ( value == otherCard.value )
               return 0;
           else
               return -1;
       } else
           return -1;
   }

   public String toString() {
       return cardSuitToString() + " " + getValueAsString();
   }

}
