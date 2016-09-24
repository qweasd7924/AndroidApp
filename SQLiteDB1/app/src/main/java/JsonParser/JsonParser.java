package JsonParser;


import android.content.Context;
import android.content.res.AssetManager;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

/**
 * Created by Павел on 20.09.2016.
 */
public class JsonParser {
    public JSONObject parseUserInfo() {
        JSONParser parser = new JSONParser();
        JSONObject userInfo = null;

        try {
            String a = System.lineSeparator();
//            userInfo = (JSONObject) parser.parse(new FileReader("." + a +
//                    "res" + a +
//                    "client.xml"));


            userInfo = (JSONObject) parser.parse(new FileReader("./res/client.xml"));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            File fileClient = new File("H:\\client.xml");
//            Reader reader = new FileReader(fileClient);
//            BufferedReader br  = new BufferedReader(reader,1024);
//            String info = br.readLine();
////            JSONTokener tokener = new JSONTokener(info);
//            userInfo = new JSONObject();
//
//            String address = userInfo.get("address").toString();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return userInfo;
    }

    public static void main(String[] args) {
        JsonParser a = new JsonParser();
        a.parseUserInfo();
    }
}
