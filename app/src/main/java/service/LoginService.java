package service;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class LoginService {
    public static boolean saveInfo(Context context, String username,
                                   String password) {
        //getFileDir : /data/data/包名/files
        //getCacheDir : /data/data/包名/cache
        File file = new File(context.getFilesDir(), "info0.txt");//"data/data/com.example.a37013.filetest1/cache"

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write((username + "#" + password).getBytes());
            fos.flush();
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static HashMap<String,String> getInfo(Context context)
    {
        File file = new File(context.getFilesDir(),"info0.txt");
        try{
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String[] result = br.readLine().split("#");
            System.out.println(result);
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("username", result[0]);
            map.put("password", result[1]);
            br.close();
            return map;
        } catch (Exception e) {
            Toast.makeText(context, "无法读取用户信息",Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}
