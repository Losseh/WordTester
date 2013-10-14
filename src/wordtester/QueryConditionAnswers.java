/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtester;

enum QCAType {ANS_RATE_SM, ANS_RATE_BIG, ANS_NUMBER_SM, ANS_NUMBER_BIG};

/**
 *
 * @author adrian
 */
public class QueryConditionAnswers implements iQueryCondition {
    String queryString;
    
    public QueryConditionAnswers(double num, QCAType qcat) {
        queryString = "SELECT * FROM " + DBInterface.getDBName() + "." + DBInterface.getTable() + " WHERE ";
        switch (qcat) {
            case ANS_RATE_SM:
                queryString += "(good_answers/all_answers < " + num + ")";
                break;
            case ANS_RATE_BIG:
                queryString += "(good_answers/all_answers > " + num + ")";
                break;
            case ANS_NUMBER_SM:
                queryString += "(all_answers < " + (int)num + ")";
                break;
            case ANS_NUMBER_BIG:
                queryString += "(all_answers > " + (int)num + ")";
                break;
            default:
                break;
        }
        System.out.println(queryString);
    }
    
    public static QueryConditionAnswers getRateSmallerThan(double num) {
        return new QueryConditionAnswers(num, QCAType.ANS_RATE_SM);
    }
    
    public static QueryConditionAnswers getRateBiggerThan(double num) {
        return new QueryConditionAnswers(num, QCAType.ANS_RATE_BIG);
    }
    
    public static QueryConditionAnswers getAnsNumberSmallerThan(double num) {
        return new QueryConditionAnswers(num, QCAType.ANS_NUMBER_SM);
    }
    
    public static QueryConditionAnswers getAnsNumberBiggerThan(double num) {
        return new QueryConditionAnswers(num, QCAType.ANS_NUMBER_BIG);
    }

    @Override
    public String getStringQuery() {
        return queryString;
    }
    
    @Override
    public QueryType getQueryType() {
        return QueryType.ANSWERS;
    }
}
