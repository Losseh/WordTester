/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtester;

import java.util.ArrayList;

/**
 *
 * @author adrian
 */
class WordList extends ArrayList<Word> {
    
    void sortByGoodAnswers() {
        
    }
    
    void sortByAllAnswers() {
        
    }
    
    /*
     * 
     */
    void sortByAnswersRatio() {
        
    }
    
    void printAll() {
        for (Word w : this) {
            w.print();
        }
    }
}
