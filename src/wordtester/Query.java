/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtester;

enum QueryType {DATE, LANGUAGE, RANGE, ANSWERS,  };

/**
 *
 * @author adrian
 */
public class Query {
     private final static String db_name = "vocabulary";
     private final static String table = "words";
    
    /* here there's need to write some conditions
     * for proper query type
     */
    public static String InterpreteQuery(iQueryCondition qt) {
        if (qt != null)
        {
            switch (qt.getQueryType()) {
                case DATE:
                    return ((QueryConditionDate) qt).getStringQuery();
                case LANGUAGE:
                    return ((QueryConditionLanguage) qt).getStringQuery();
                case RANGE:
                    return ((QueryConditionWords) qt).getStringQuery();
                default:
                    break;
            }
        }
        return All();
    }
    
    /* Method returs String representing query for every record
     * in the db
     */
    static public String All() {
        return ("SELECT language1, word1, language2, "
              + "word2, good_answers, all_answers, date_of_insert from "
              + db_name + "." + table);
    }
    
}
