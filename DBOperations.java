/**
 * Class: B.Sc. Cloud Computing
 * Instructor: Maria Boyle
 * Description: A DBOperations interface - All classes implementing this interface must override its abstract methods
 * Date: 21/03/2019
 * @author Dane Campbell [L00142041]
 * @version 1.0
 */
 
public interface DBOperations{ 
   public abstract void createConnection(String dbUrl);
   public abstract void createDatabase();
   public abstract void closeConnection();
   public abstract boolean insertIntoDatabase(String sqlString);  // boolean return flag added for signup confirmation dialog when true
}