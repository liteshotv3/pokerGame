package PJ4;

import java.util.*;

/** class PlayingCardException: It is used for errors related to Card and Deck objects
 *  Do not modify this class!
 */
class PlayingCardException extends Exception {

    /* Constructor to create a PlayingCardException object */
    PlayingCardException (){
		super ();
    }

    PlayingCardException ( String reason ){
		super ( reason );
    }
}


/** class Card : for creating playing card objects
 *  it is an immutable class.
 *  Rank - valid values are 1 to 13
 *  Suit - valid values are 0 to 3
 *  Do not modify this class!
 */
class Card {
	
    /* constant suits and ranks */
    static final String[] Suit = {"Clubs", "Diamonds", "Hearts", "Spades" };
    static final String[] Rank = {"","A","2","3","4","5","6","7","8","9","10","J","Q","K"};

    /* Data field of a card: rank and suit */
    private int cardRank;  /* values: 1-13 (see Rank[] above) */
    private int cardSuit;  /* values: 0-3  (see Suit[] above) */

    /* Constructor to create a card */
    /* throw PlayingCardException if rank or suit is invalid */
    public Card(int rank, int suit) throws PlayingCardException { 
	if ((rank < 1) || (rank > 13))
		throw new PlayingCardException("Invalid rank:"+rank);
	else
        	cardRank = rank;
	if ((suit < 0) || (suit > 3))
		throw new PlayingCardException("Invalid suit:"+suit);
	else
        	cardSuit = suit;
    }

    /* Accessor and toString */
    /* You may impelemnt equals(), but it will not be used */
    public int getRank() { return cardRank; }
    public int getSuit() { return cardSuit; }
    public String toString() { return Rank[cardRank] + " " + Suit[cardSuit]; }

    
    /* Few quick tests here */
    /*public static void main(String args[])
    {
	try {
	    Card c1 = new Card(1,3);    // A Spades
	    System.out.println(c1);
	    c1 = new Card(10,0);	// 10 Clubs
	    System.out.println(c1);
	    c1 = new Card(10,5);        // generate exception here
	}
	catch (PlayingCardException e)
	{
	    System.out.println("PlayingCardException: "+e.getMessage());
	}
    }*/
}


/** class Decks represents : n decks of playing cards
 *  Use class Card to construct n * 52 playing cards!
 *
 *  Do not add new data fields!
 *  Do not modify any methods
 *  You may add private methods 
 */

class Decks {

    /* this is used to keep track of original n*52 cards */
    private List<Card> originalDecks;   

    /* this starts with n*52 cards deck from original deck   */
    /* it is used to keep track of remaining cards to deal */
    /* see reset(): it resets dealDecks to a full deck      */
    private List<Card> dealDecks;

    /* number of decks in this object */
    private int numberDecks;


    /**
     * Constructor: Creates default one deck of 52 playing cards in originalDecks and
     * 		    copy them to dealDecks.
     *              initialize numberDecks=n
     * Note: You need to catch PlayingCardException from Card constructor
     *	     Use ArrayList for both originalDecks & dealDecks
     */
    public Decks()
    {
        // implement this method!
        numberDecks = 1;
        originalDecks = new ArrayList();
        for (int i = 0; i < 4; i++) { //for suits
            for (int j = 1; j < 15; j++) { //for ranks
                try {
                    Card newCard = new Card(j, i);
                    originalDecks.add(newCard);
                } catch (PlayingCardException e) {
                    System.out.println("PlayingCardException: " + e.getMessage());
                }
            }
        }
        dealDecks = originalDecks;
        
    }


    /**
     * Constructor: Creates n decks (52 cards each deck) of playing cards in
     *              originalDecks and copy them to dealDecks.
     *              initialize numberDecks=n
     * Note: You need to catch PlayingCardException from Card constructor
     *	     Use ArrayList for both originalDecks & dealDecks
     */
    public Decks(int n) {
        // implement this method!
        numberDecks = n;
        originalDecks = new ArrayList();
        dealDecks = new ArrayList();
        for (int k = 0; k < numberDecks; k++) {
            for (int i = 0; i < 4; i++) { //for suits
                for (int j = 1; j < 14; j++) { //for ranks
                    try {
                        Card newCard = new Card(j, i);
                        originalDecks.add(newCard);
                    } catch (PlayingCardException e) {
                        System.out.println("PlayingCardException: " + e.getMessage());
                    }
                }
            }
        }
        dealDecks.addAll(originalDecks);
    }


    /**
     * Task: Shuffles cards in deal deck.
     * Hint: Look at java.util.Collections
     */
    public void shuffle()
    {
        // implement this method!
        /*
        List<Card> tempStack1 = new ArrayList();
        List<Card> tempStack2 = new ArrayList();
        List<Card> tempStack3 = new ArrayList();
        List<Card> tempStack4 = new ArrayList();
        List<Card> tempStack5 = new ArrayList();
        List<Card> tempStack6 = new ArrayList();
        List<Card> tempStack7 = new ArrayList();
        List[] stackHolder = {tempStack1, tempStack2, tempStack3, tempStack4,
        tempStack5, tempStack6, tempStack7};
        
        while (!dealDecks.isEmpty()) {
            for (int i = 0; i < 7; i++) {
                if(!dealDecks.isEmpty()) {
                    stackHolder[i].add(dealDecks.remove(0));
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            dealDecks.addAll(stackHolder[i]);
        }
        */

        List<Card> tempStack1 = new ArrayList();
        List<Card> tempStack2 = new ArrayList();
        for (int i = 0; i < 7; i++) {
            Random r = new Random();
            for (int j = 0; j < r.nextInt(5); j++) {
            tempStack1.add(dealDecks.remove(0));
            }
            for (int j = 0; j < r.nextInt(5); j++) {
            tempStack2.add(dealDecks.remove(0));
        }
            while (!dealDecks.isEmpty()) {
                    tempStack1.add(dealDecks.remove(0));
                    if (!dealDecks.isEmpty()) {
                    tempStack2.add(dealDecks.remove(0));
                    }
                }
            dealDecks.addAll(tempStack1);
            dealDecks.addAll(tempStack2);
            tempStack1.clear();
            tempStack2.clear();
        }
    }

    /**
     * Task: Deals cards from the deal deck.
     *
     * @param numberCards number of cards to deal
     * @return a list containing cards that were dealt
     * @throw PlayingCardException if numberCard > number of remaining cards
     *
     * Note: You need to create ArrayList to stored dealt cards
     *       and should removed dealt cards from dealDecks
     *
     */
    public List<Card> deal(int numberCards) throws PlayingCardException
    {
        // implement this method!
        
        List<Card> dealt = new ArrayList();
        if (numberCards > dealDecks.size()) {
            throw new PlayingCardException("Less then " + numberCards 
                    + " cards remaining in deck.");
        } else {
        for (int i = 0; i < numberCards; i++) {
            dealt.add(dealDecks.remove(0));
        }
        return dealt;
        }
    }

    /**
     * Task: Resets deal deck by getting all cards from the original deck.
     */
    public void reset()
    {
        // implement this method!
        dealDecks.clear();
        dealDecks.addAll(originalDecks);        
    }

    /**
     * Task: Return number of remaining cards in deal deck.
     */
    public int remain()
    {
	return dealDecks.size();
    }

    /**
     * Task: Returns a string representing cards in the deal deck 
     */
    public String toString()
    {
	return ""+dealDecks;
    }


    /* Quick test                   */
    /*                              */
    /* Do not modify these tests    */
    /* Generate 2 decks of cards    */
    /* Loop 2 times:                */
    /*   Deal 30 cards for 4 times  */
    /*   Expect exception last time */
    /*   reset()                    */

    public static void main(String args[]) {

        System.out.println("*******    Create 2 decks of cards      *********\n\n");
        Decks decks  = new Decks(2);
         
	for (int j=0; j < 2; j++)
	{
        	System.out.println("\n************************************************\n");
        	System.out.println("Loop # " + j + "\n");
		System.out.println("Before shuffle:"+decks.remain()+" cards");
		System.out.println("\n\t"+decks);
        	System.out.println("\n==============================================\n");

                int numHands = 4;
                int cardsPerHand = 30;

        	for (int i=0; i < numHands; i++)
	 	{
	    		decks.shuffle();
		        System.out.println("After shuffle:"+decks.remain()+" cards");
		        System.out.println("\n\t"+decks);
			try {
            		    System.out.println("\n\nHand "+i+":"+cardsPerHand+" cards");
            		    System.out.println("\n\t"+decks.deal(cardsPerHand));
            		    System.out.println("\n\nRemain:"+decks.remain()+" cards");
		            System.out.println("\n\t"+decks);
        	            System.out.println("\n==============================================\n");
			}
			catch (PlayingCardException e) 
			{
		 	        System.out.println("*** In catch block : PlayingCardException : msg : "+e.getMessage());
			}
		}

                System.out.println("Deck will reset now");
		decks.reset();
                System.out.println("After deck rest there are " + decks.remain() + " cards in the deck.");
                        System.out.println("They are " + decks);
	}
    }

}
