package com.mycompany.tiralabra_maven;

/**
 * General game statistics.
 * @author Joel Nummelin
 */
public class Statistics {
    private int sessionWins;
    private int sessionLosses;
    private int sessionDraws;
    private int totalWins;
    private int totalLosses;
    private int totalDraws;
    
    /**
     * 
     * @param totalWins
     * @param totalLosses
     * @param totalDraws 
     */
    public Statistics(int totalWins, int totalLosses , int totalDraws){
        this.totalWins = totalWins;
        this.totalLosses = totalLosses;
        this.totalDraws = totalDraws;
    }

    public int getSessionWins() {
        return sessionWins;
    }

    public int getSessionLosses() {
        return sessionLosses;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public int getTotalLosses() {
        return totalLosses;
    }

    public int getSessionDraws() {
        return sessionDraws;
    }

    public int getTotalDraws() {
        return totalDraws;
    }
    
    
    
    public void win(){
        totalWins++;
        sessionWins++;
    }
    
    public void lose(){
        totalLosses++;
        sessionLosses++;
    }
    
    public void draw(){
        totalDraws++;
        sessionDraws++;
    }
    
    public double getTotalWinPerCent(){
        if (totalWins == 0 && totalLosses == 0){
            return 0;
        }
        return 100 * (1.0 * totalWins / (totalWins + totalLosses + totalDraws));
    }
    
    public double getSessionWinPerCent(){
        if (sessionWins == 0 && sessionLosses == 0){
            return 0;
        }
        return 100 * (1.0 * sessionWins / (sessionWins + sessionLosses + sessionDraws));
    }
    
    public double getTotalDrawPerCent(){
        if (totalDraws == 0){
            return 0;
        }
        return 100 * (1.0 * totalDraws / (totalDraws + totalLosses + totalWins));
    }
    
    public double getSessionDrawPerCent(){
        if (sessionDraws == 0){
            return 0;
        }
        return 100 * (1.0 * sessionDraws / (sessionDraws + sessionLosses + sessionWins));
    }
    
}
