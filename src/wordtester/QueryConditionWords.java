/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtester;

enum QCWType {BEFORE, AFTER, BETWEEN, STARTING};

/**
 *
 * @author adrian
 */
public class QueryConditionWords implements iQueryCondition {
    String beg_letter;
    String end_letter;
    String queryString;

    
    public QueryConditionWords(String bl, String el) {
        beg_letter = bl;
        end_letter = el;
        queryString = "SELECT * FROM " + DBInterface.getDBName() + "." + DBInterface.getTable() +
                " WHERE ( ( LEFT(word1, 1) > \"" + beg_letter + "\" AND LEFT(word1, 1) < \"" + end_letter + "\" ) OR " +
                "( LEFT(word2, 1) > \"" + beg_letter + "\" AND LEFT(word2, 1) < \"" + end_letter + "\" ) )";
    }
    
   public QueryConditionWords(String letter, QCWType qcwt) {
        beg_letter = end_letter = letter;
        String cmp_sign;
        switch (qcwt) {
            case STARTING:
                cmp_sign = "=";
                break;
            case AFTER:
                cmp_sign = ">";
                break;
            case BEFORE:
                cmp_sign = "<";
                break;
            default:
                cmp_sign = "=";
                System.err.println("Wrong condition on QueryConditionWords constructor");
                break;
        }

        queryString = "SELECT * FROM " + DBInterface.getDBName() + "." + DBInterface.getTable() +
                " WHERE (LEFT(word1, 1) " + cmp_sign + " \"" + letter + "\" OR " +
                "LEFT(word2, 1) " + cmp_sign + " \"" + letter + "\")";
    }
    
    public static QueryConditionWords getWordsBefore(String letter) {
        return new QueryConditionWords(letter, QCWType.BEFORE);
    }
    
    public static QueryConditionWords getWordsAfter(String letter) {
        return new QueryConditionWords(letter, QCWType.AFTER);
    }
    
    public static QueryConditionWords getWordsBetween(String letter1, String letter2) {
        return new QueryConditionWords(letter1, letter2);
    }
    
    public static QueryConditionWords getWordsStartingWith(String letter) {
        return new QueryConditionWords(letter, QCWType.STARTING);
    }

    @Override
    public String getStringQuery() {
        return queryString;
    }
    
    @Override
    public QueryType getQueryType() {
        return QueryType.RANGE;
    }
}
