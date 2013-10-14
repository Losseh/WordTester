/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtester;

/**
 *
 * @author adrian
 */
public class WordTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        DBInterface db = new DBInterface();
//        db.addRecord("Polish", "dupa", "Russian", "zhopa");
//        db.addRecord("German", "die Katze", "Polish", "kot");
//        db.addRecord("Polish", "kot", "French", "le chat");
//        db.addRecord("Danish", "roed", "German", "rot");
//        db.addRecord("Swedish", "jag", "Russian", "ja");
        db.readDataBase();
    }
}
