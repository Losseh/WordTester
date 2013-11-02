/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtester;

import java.util.Date;

/**
 *
 * @author adrian
 */
public class Word {

    public String language1;
    public String language2;
    public String word1;
    public String word2;
    public int good_answers;
    public int all_answers;
    public Date date_of_insert;

    /*
     * 
     */
    Word() {
    }

    /*
     * 
     */
    public void setAll_answers(int all_answers) {
        this.all_answers = all_answers;
    }

    /*
     * 
     */
    public void setDate_of_insert(Date date_of_insert) {
        this.date_of_insert = date_of_insert;
    }

    /*
     * 
     */
    public void setGood_answers(int good_answers) {
        this.good_answers = good_answers;
    }

    /*
     * 
     */
    public void setLanguage1(String language1) {
        this.language1 = language1;
    }

    /*
     * 
     */
    public void setLanguage2(String language2) {
        this.language2 = language2;
    }

    /*
     * 
     */
    public void setWord1(String word1) {
        this.word1 = word1;
    }

    /*
     * 
     */
    public void setWord2(String word2) {
        this.word2 = word2;
    }

    /*
     * 
     */
    public int getAll_answers() {
        return all_answers;
    }

    /*
     * 
     */
    public Date getDate_of_insert() {
        return date_of_insert;
    }

    /*
     * 
     */
    public int getGood_answers() {
        return good_answers;
    }

    /*
     * 
     */
    public String getLanguage1() {
        return language1;
    }

    /*
     * 
     */
    public String getLanguage2() {
        return language2;
    }

    /*
     * 
     */
    public String getWord1() {
        return word1;
    }

    /*
     * 
     */
    public String getWord2() {
        return word2;
    }

    void print() {
        System.out.println(word1 + " [" + language1 + "] - " +
                word2 + " [" + language2 + "], " + good_answers + 
                "/" + all_answers);
    }
}
