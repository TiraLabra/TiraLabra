/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sanapuuro.sanapuuro.grid.LetterContainer;
import sanapuuro.sanapuuro.words.WordEvaluator;
import sanapuuro.sanapuuro.words.WordEvaluator.EvaluationResult;

/**
 *
 * @author skaipio
 */
public class Evaluation {
    private final WordEvaluator evaluator = new WordEvaluator();
    private final Map<Player, GameTimer> timers = new HashMap<>();
    private final Map<Player, Integer> scores = new HashMap<>();
    
    public void registerPlayer(Player player, GameTimer timer){
        this.timers.put(player, timer);
        this.scores.put(player, 0);
    }
    
    public EvaluationResult submitWord(Player submitter, List<LetterContainer> letterContainers){
        EvaluationResult evaluation = this.evaluator.evalute(letterContainers);
        if (evaluation.succeeded){
            int currentScore = this.scores.get(submitter);
            int addTime = this.evaluateTimeIncrease(letterContainers);
            this.scores.put(submitter, currentScore+evaluation.getScore());
            this.timers.get(submitter).addTime(addTime);
        }
        return evaluation;
    }
    
    private int evaluateTimeIncrease(List<LetterContainer> letterContainers){
        int letters = letterContainers.size();
        int n = letters-3;
        return (letters-2)*2+n*(n+1)/2;
    }
}
