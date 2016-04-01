package com.luckyqiao.cordova;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;


public class MediaRetrieve extends CordovaPlugin {


    public boolean execute(final String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {

       cordova.getThreadPool().execute(new Runnable() {
           @Override
           public void run() {
               runQuery(action,callbackContext);
           }
       });
        return true;
    }

    private void runQuery(String action, CallbackContext callback){
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
        }else if(action.equals("video")){
            String str[] = {
                    MediaStore.Video.Media.DISPLAY_NAME,
                    MediaStore.Video.Media.DATA};
            cursor = cordova.getActivity().getContentResolver().query(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI, str,
                    null, null, null);
        }
        if (cursor != null) {

            while (cursor.moveToNext()) {
                JSONObject objJSON=new JSONObject();

                String name = cursor.getString(0);
                String uri = Uri.parse("file://" + cursor.getString(1)).toString();

                try{
                    objJSON.put("name", name);
                    objJSON.put("url", uri);
                }catch (JSONException e){
                    System.out.println(e.getMessage());
                }
                jsonRes.put(objJSON);

            }
            cursor.close();
            callback.success(jsonRes);
        }
    }
}