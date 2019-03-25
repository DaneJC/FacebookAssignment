/**
 * Class: B.Sc. Cloud Computing
 * Instructor: Maria Boyle
 * Description: FacebookDB class tester
 * Date: 21/03/2019
 * @author Dane Campbell [L00142041]
 * @version 1.0
 */

public class FacebookDBTester{
    public static void main(String[] args) {
        FacebookDB fbDb = new FacebookDB();

        String password = fbDb.getUserPasswordFromDatabase("dane@campbell.com");
        System.out.println(password);
    }

}
