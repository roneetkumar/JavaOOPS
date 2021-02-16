import model.Database;

public class TestDB {



    public static void main(String[] args){
        System.out.println("Running test database");

        Database db = new Database();

        try {
            db.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.disconnect();


    }

}
