import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Game21 {

    public static void main( String[] argc ) {

        BlackJack blackJack = new BlackJack();

        BufferedReader reader = new BufferedReader(new InputStreamReader( System.in ));
        try {
            while ( true ) {
                System.out.println( "Please, press any key to start the hand or 'q' to end the game" );
                String symbol = reader.readLine();

                if ( symbol.equalsIgnoreCase( "q" ) )
                    break;

                blackJack.makeHand();

                blackJack.printStatistics();
            }

            blackJack.printDeck();
        } catch( Exception e ) {
            e.printStackTrace();
        }
    }
}
