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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrian Szymanski
 */
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

    /**
     * DBInterface class constructor
     *
     * @throws Exception
     */
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

    /**
     * Reads database and prints all records to standard output
     *
     * @throws Exception
     */
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

    /**
     *
     * @throws SQLException
     */
    public ArrayList<String> getLanguages() throws SQLException {
        statement = connect.createStatement();
        resultSet = statement.executeQuery("SELECT language1 FROM words");
        ArrayList<String> languages = new ArrayList<String>();
        while (resultSet.next()) {
            languages.add(resultSet.getString("language1"));
        }
        resultSet = statement.executeQuery("SELECT language2 FROM words");
        while (resultSet.next()) {
            String s = resultSet.getString("language2");
            if (!languages.contains(s)) {
                languages.add(s);
            }
        }

        Collections.sort(languages);

        return languages;
    }

    /**
     *
     * @param resultSet
     * @throws SQLException
     */
    private ArrayList<String> getMetaData(ResultSet resultSet) throws SQLException {
        //   Now get some metadata from the database
        // Result set get the result of the SQL query

        ArrayList<String> columns = new ArrayList<>();
        System.out.println("The columns in the table are: ");
        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            //System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
            columns.add(resultSet.getMetaData().getColumnName(i));
        }

        return columns;
    }

    /**
     * Writes obtained result set to standard output
     *
     * @param resultSet Set of record obtained after a query
     * @throws SQLException
     */
    private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        int i = 1;
        ArrayList<String> columns = getMetaData(resultSet);
        while (resultSet.next()) {
            String language1 = "";
            String word1 = "";
            String language2 = "";
            String word2 = "";
            int good_answers = -1;
            int all_answers = -1;
            String toShow = "" + i;
            if (columns.contains("language1")) {
                language1 = resultSet.getString("language1");
                toShow += ". [" + language1 + "] ";
            }
            if (columns.contains("word1")) {
                word1 = resultSet.getString("word1");
                toShow += word1;
            }
            if (columns.contains("language2")) {
                language2 = resultSet.getString("language2");
                toShow += "- [" + language2 + "] ";
            }
            if (columns.contains("word2")) {
                word2 = resultSet.getString("word2");
                toShow += word2;
            }
            if (columns.contains("good_answers")) {
                good_answers = resultSet.getInt("good_answers");
                toShow += " " + good_answers;
            }
            if (columns.contains("all_answers")) {
                all_answers = resultSet.getInt("all_answers");
                toShow += "/" + all_answers;
            }
            if (columns.contains("date_of_insert")) {
                Date date_of_insert = resultSet.getDate("date_of_insert");
                toShow += "\nDate of insert: " + date_of_insert + "\n";
            }

            System.out.println(toShow);
            i++;
        }
    }

    /**
     * Method adds record to the vocabulary database
     *
     * @param l1 Language #1
     * @param w1 Word #1
     * @param l2 Language #2
     * @param w2 Word #2
     * @return
     */
    public boolean addRecord(String l1, String w1, String l2, String w2) {
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

    /**
     *
     * @return Table
     */
    static String getTable() {
        return table;
    }

    /**
     *
     * @return Name of used database
     */
    static String getDBName() {
        return db_name;
    }

    // You need to close the resultSet
    /**
     *
     */
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

    public WordList getWordList(String lang1, String lang2) throws SQLException {
        WordList words = new WordList();
        statement = connect.createStatement();
        resultSet = statement.executeQuery(Query.InterpreteQuery(
                new QueryConditionLanguage(lang1, lang2)));
        while (resultSet.next()) {
            Word word = new Word();
            word.setLanguage1(resultSet.getString("language1"));
            word.setLanguage2(resultSet.getString("language2"));
            word.setWord1(resultSet.getString("word1"));
            word.setWord2(resultSet.getString("word2"));
            word.setGood_answers(resultSet.getInt("good_answers"));
            word.setAll_answers(resultSet.getInt("all_answers"));
            word.setDate_of_insert(resultSet.getDate("date_of_insert"));
            words.add(word);
        }
        words.printAll();
        return words;
    }
}
