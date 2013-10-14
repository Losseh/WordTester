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
        queryString = "SELECT * FROM " + DBInterface.getDBName() + "." + 
                DBInterface.getTable() + " WHERE language1=\"" + language +
                "\" OR language2=\"" + language + "\"";
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
