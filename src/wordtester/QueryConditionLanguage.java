/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtester;

/**
 *
 * @author adrian
 */
public class QueryConditionLanguage implements iQueryCondition {

    String language;
    String queryString;

    public QueryConditionLanguage(String l) {
        language = l;
        queryString = "SELECT * FROM " + DBInterface.getDBName() + "."
                + DBInterface.getTable() + " WHERE language1=\"" + language
                + "\" OR language2=\"" + language + "\"";
    }

    public QueryConditionLanguage(String l1, String l2) {
        queryString = "SELECT * FROM " + DBInterface.getDBName() + "."
                + DBInterface.getTable() + " WHERE " +
                "(language1=\"" + l1 + "\" AND language2=\"" + l2 + "\") OR " + 
                "(language1=\"" + l2 + "\" AND language2=\"" + l1 + "\")";
    }

    @Override
    public String getStringQuery() {
        return queryString;
    }

    @Override
    public QueryType getQueryType() {
        return QueryType.LANGUAGE;
    }
}
