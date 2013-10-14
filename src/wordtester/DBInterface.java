/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtester;

/**
 *
 * @author adrian
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBInterface {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;
  
  private final String user = "losseh";
  private final String host = "localhost";
  private final String password = "";
  private final static String db_name = "vocabulary";
  private final static String table = "words";
  private final static String sep = ".";

  public DBInterface() throws Exception {
    try {
        // This will load the MySQL driver, each DB has its own driver
        Class.forName("com.mysql.jdbc.Driver");
        // Setup the connection with the DB
        connect = DriverManager
            .getConnection("jdbc:mysql://" + host + "/" + db_name + "?"
                + "user=" + user + "&password=" + password);
    } catch (Exception e) {
        throw e;
    }
  }

  public void readDataBase() throws Exception {
    try {
      //addRecord("Test language", "Test word", "Test language1", "Test word1");
      
      // Remove again the insert comment
      //preparedStatement = connect.prepareStatement("delete from " + db_name + "." + table + " where word1= ? ; ");
      //preparedStatement.setString(1, "Test word");
      //preparedStatement.executeUpdate();
     
      statement = connect.createStatement();
      resultSet = statement.executeQuery(Query.InterpreteQuery(QueryConditionAnswers.getAnsNumberBiggerThan(20)));
      
      writeResultSet(resultSet);
      //writeMetaData(resultSet);
      
    } catch (Exception e) {
      throw e;
    }
  }

  private void writeMetaData(ResultSet resultSet) throws SQLException {
    //   Now get some metadata from the database
    // Result set get the result of the SQL query
    
    System.out.println("The columns in the table are: ");
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }

  private void writeResultSet(ResultSet resultSet) throws SQLException {
    // ResultSet is initially before the first data set
    int i = 1;
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
      String language1 = resultSet.getString("language1");
      String word1 = resultSet.getString("word1");
      String language2 = resultSet.getString("language2");
      String word2 = resultSet.getString("word2");
      int good_answers = resultSet.getInt("good_answers");
      int all_answers = resultSet.getInt("all_answers");
      Date date_of_insert = resultSet.getDate("date_of_insert");
              
      System.out.println(i + ". [" + language1 + "] " + word1 + " - [" + language2 + "] " + word2 + " " +
              good_answers + "/" + all_answers);
      System.out.println("Date of insert: " + date_of_insert);
      System.out.println("");
      i++;
    }
  }
  
  public boolean addRecord (String l1, String w1, String l2, String w2) {
      try {
          preparedStatement = connect.prepareStatement("insert into " + db_name + sep + table + " values (?, ?, ?, ? ,?, ?, ?, default)");
          // "myuser, webpage, datum, summary, COMMENTS from FEEDBACK.COMMENTS");
          // Parameters start with 1
          preparedStatement.setString(1, l1);
          preparedStatement.setString(2, w1);
          preparedStatement.setString(3, l2);
          preparedStatement.setString(4, w2);
          preparedStatement.setInt(5, 0);
          preparedStatement.setInt(6, 0);
          preparedStatement.setDate(7, new java.sql.Date(System.currentTimeMillis()));
          preparedStatement.executeUpdate();
          return true;
      } catch (SQLException ex) {
          Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
      }
      return false;
  }

  static String getTable() {
      return table;
  }
  
   static String getDBName() {
      return db_name;
  }  

  // You need to close the resultSet
  private void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }

} 