/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee.scoring;

/**
 *
 * @author essalmen
 */
public enum ScoreNames {
    ONES(0),
    TWOS(1),
    THREES(2),
    FOURS(3),
    FIVES(4),
    SIXES(5),
    SUM(6),
    BONUS(7),
    PAIR(8),
    TWOPAIRS(9),
    THREEOFAKIND(10),
    FOUROFAKIND(11),
    SMALLSTRAIGHT(12),
    LARGESTRAIGHT(13),
    FULLHOUSE(14),
    CHANCE(15),
    YAHTZEE(16),
    TOTAL(17);
    
    private ScoreNames(int i)
    {
        this.index = i;
    }
    
    private int index;
    
    public String scoreText()
    {
        String text = "";
        switch(this.index)
        {
            case 0:
                text = "Ones: ";
                break;
            case 1:
                text = "Twos: ";
                break;
            case 2:
                text = "Threes: ";
                break;
            case 3:
                text = "Fours: ";
                break;
            case 4:
                text = "Fives: ";
                break;
            case 5:
                text = "Sixes: ";
                break;
            case 6:
                text = "Sum: ";
                break;
            case 7:
                text = "Bonus: ";
                break;
            case 8:
                text = "Pair: ";
                break;
            case 9:
                text = "Two pairs: ";
                break;
            case 10:
                text = "Three of a kind: ";
                break;
            case 11:
                text = "Four of a kind: ";
                break;
            case 12:
                text = "Small straight: ";
            case 13:
                text = "Large straight: ";
                break;
            case 14:
                text = "Full house: ";
                break;
            case 15:
                text = "Chance: ";
                break;
            case 16:
                text = "Yahtzee: ";
                break;
            case 17:
                text = "Total: ";
                break;
            
        }
        
        return text;
    }
}
