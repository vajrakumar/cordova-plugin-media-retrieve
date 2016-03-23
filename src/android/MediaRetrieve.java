package xyz.luckyqiao.cordova;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;


public class MediaRetrieve extends CordovaPlugin {

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        JSONArray jsonRes=new JSONArray();
        Cursor cursor=null;
        if(action.equals("image")) {
            String str[] = {
                    MediaStore.Images.Media.DISPLAY_NAME,
                    MediaStore.Images.Media.DATA};
             cursor = cordova.getActivity().getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, str,
                    null, null, null);
        }
        else if(action.equals("audio")){
            String str[] = {
                    MediaStore.Audio.Media.DISPLAY_NAME,
                    MediaStore.Audio.Media.DATA};
            cursor = cordova.getActivity().getContentResolver().query(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, str,
                    null, null, null);
        }
        if (cursor != null) {

            while (cursor.moveToNext()) {
                JSONObject objJSON=new JSONObject();

                String name = cursor.getString(0); // 图片ID
                String uri = Uri.parse("file://" + cursor.getString(1)).toString(); // 图片绝对路径

                objJSON.put(name,uri);
                jsonRes.put(objJSON);

            }
            cursor.close();
            callbackContext.success(jsonRes);
        }
        return true;
    }
}