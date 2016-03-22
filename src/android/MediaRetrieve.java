package org.apache.cordova.media;

import java.util.ArrayList;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * This class launches the camera view, allows the user to take a picture, closes the camera view,
 * and returns the captured image.  When the camera view is closed, the screen displayed before
 * the camera view was shown is redisplayed.
 */

public class MediaRetrieve extends CordovaPlugin{

       /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param args              JSONArry of arguments for the plugin.
     * @param callbackContext   The callback id used when calling back into JavaScript.
     * @return                  A PluginResult object with a status and message.
     */
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
 // 扫描外部设备中的照片
                String str[] = {MediaStore.Images.Media._ID,
                        MediaStore.Images.Media.DISPLAY_NAME,
                        MediaStore.Images.Media.DATA};
                Cursor cursor = cordova.getActivity().getContentResolver().query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, str,
                        null, null, null);
                if (cursor != null) {
                    String result = "{";
                    while (cursor.moveToNext()) {
                        String uid = cursor.getString(0); // 图片ID
                        String name = cursor.getString(1); // 图片文件名
                        String uri = Uri.parse("file://" + cursor.getString(2)).toString(); // 图片绝对路径
                        result = result + "[\"uid\":\"" + uid + "\",\"name\":\"" + name + "\",\"uri\":\"" + uri + "\"],";
                    }
                    cursor.close();
                    result = result + "}";
                    callbackContext.success(result);
                }
                return true;
    }

}