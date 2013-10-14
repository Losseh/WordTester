/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtester;

import java.util.Date;
import java.sql.*;

/**
 *
 * @author adrian
 */
public class QueryConditionDate implements iQueryCondition {
    Date start;
    Date end;
    String queryString;
    
    private QueryConditionDate(Date s, Date e, String qs) {
        start = s;
        end = e;
        queryString = qs;
    }
    
    static QueryConditionDate getWordsLaterThan(Date d) {
        String qs = "SELECT * FROM " + DBInterface.getDBName()+ 
                "." + DBInterface.getTable()+
                " WHERE date_of_insert > \"" + 
                new java.sql.Date(d.getYear(),d.getMonth(),d.getDay()) + "\"";
        return new QueryConditionDate(d, new Date(140,0,1), qs);
    }
    
    static QueryConditionDate getWordsEarlierThan(Date d) {
        String qs = "SELECT * FROM " + DBInterface.getDBName()+ 
                "." + DBInterface.getTable()+
                " WHERE date_of_insert < \"" + 
                new java.sql.Date(d.getYear(),d.getMonth(),d.getDay()) + "\"";
        return new QueryConditionDate( new Date(60,0,1), d, qs);
    }
    
    static QueryConditionDate getWordsBetweenDates(Date s, Date e) {
        String qs = "SELECT * FROM " + DBInterface.getTable() + 
                "." + DBInterface.getDBName() +
                " WHERE date_of_insert BETWEEN \"" + 
                new java.sql.Date(s.getYear(),s.getMonth(),s.getDay())
                + "\" AND \"" + 
                new java.sql.Date(e.getYear(),e.getMonth(),e.getDay()) + "\"";
        return new QueryConditionDate(s, e, qs);
    }

    @Override
    public String getStringQuery() {
        return queryString;
    }

    @Override
    public QueryType getQueryType() {
        return QueryType.DATE;
    }
}
