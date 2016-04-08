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
        String baseUri="";
        if(action.equals("image")) {
            String str[] = {
                    MediaStore.Images.Media._ID,
                    MediaStore.Images.Media.DISPLAY_NAME,
                    MediaStore.Images.Media.DATA};
            cursor = cordova.getActivity().getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, str,
                    null, null, null);
            baseUri="content://media/external/images/media/";
        }
        else if(action.equals("audio")){
            String str[] = {
                    MediaStore.Audio.Media._ID,
                    MediaStore.Audio.Media.DISPLAY_NAME,
                    MediaStore.Audio.Media.DATA};
            cursor = cordova.getActivity().getContentResolver().query(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, str,
                    null, null, null);
            baseUri="content://media/external/audio/media/";
        }else if(action.equals("video")){
            String str[] = {
                    MediaStore.Video.Media._ID,
                    MediaStore.Video.Media.DISPLAY_NAME,
                    MediaStore.Video.Media.DATA};
            cursor = cordova.getActivity().getContentResolver().query(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI, str,
                    null, null, null);
            baseUri="content://media/external/video/media/";
        }
        if (cursor != null) {

            while (cursor.moveToNext()) {
                JSONObject objJSON=new JSONObject();

                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String path = cursor.getString(2);

                Uri uri=Uri.parse(baseUri+id);

                try{
                    objJSON.put("name", name);
                    objJSON.put("uri", uri);
                    objJSON.put("path", path);
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