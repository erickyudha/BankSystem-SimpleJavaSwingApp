package Classes;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class BankDatabase {
    private List<Hashtable<String, String>> db = new ArrayList<>();

    public void addNewAccount(String exportData) {
        String[] dataArr = exportData.split("-");

        Hashtable<String, String> data = new Hashtable<>();
        data.put("id", dataArr[0]);
        data.put("username", dataArr[1]);
        data.put("firstName", dataArr[2]);
        data.put("lastName", dataArr[3]);
        data.put("password", dataArr[4]);
        data.put("balance", dataArr[5]);

        db.add(data);
    }

    public String exportData(String username) {
        String result = null;
        for (Hashtable<String, String> data:
                db) {
            if (data.get("username").equals(username)) {
                result = data.get("id") +
                        "-" + data.get("username") +
                        "-" + data.get("firstName") +
                        "-" + data.get("lastName") +
                        "-" + data.get("password") +
                        "-" + data.get("balance");
            }
        }
        return result;
    }

    public String exportDataById(String id) {
        String result = null;
        for (Hashtable<String, String> data:
                db) {
            if (data.get("id").equals(id)) {
                result = data.get("id") +
                        "-" + data.get("username") +
                        "-" + data.get("firstName") +
                        "-" + data.get("lastName") +
                        "-" + data.get("password") +
                        "-" + data.get("balance");
            }
        }
        return result;
    }


    public boolean isIdExist(String id) {
        boolean result = false;
        for (Hashtable<String, String> data:
             db) {
            if (data.get("id").equals(id)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean isUsernameExist(String username) {
        boolean result = false;
        for (Hashtable<String, String> data:
                db) {
            if (data.get("username").equals(username)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void delAccount(String id) {
        db.removeIf(data -> data.get("id").equals(id));
    }
}
