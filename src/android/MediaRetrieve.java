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
        JSONObject data=new JSONObject();
        JSONArray resArray=new JSONArray();
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
                                MediaStore.Audio.Media.DATA,
                                MediaStore.Audio.Media.ALBUM,
                                MediaStore.Audio.Media.ALBUM_KEY,
                                MediaStore.Audio.Media.ARTIST,
                                MediaStore.Audio.Media.ARTIST_KEY,
                                MediaStore.Audio.Media.BOOKMARK,
                                MediaStore.Audio.Media.COMPOSER,
                                MediaStore.Audio.Media.DATE_ADDED,
                                MediaStore.Audio.Media.DURATION,
                                MediaStore.Audio.Media.IS_ALARM,
                                MediaStore.Audio.Media.IS_MUSIC,
                                MediaStore.Audio.Media.IS_NOTIFICATION,
                                MediaStore.Audio.Media.IS_PODCAST,
                                MediaStore.Audio.Media.IS_RINGTONE,
                                MediaStore.Audio.Media.TRACK,
                                MediaStore.Audio.Media.ALBUM_ID
                        };
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
                JSONObject item=new JSONObject();

                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String path = cursor.getString(2);
                String album = cursor.getString(3);
                String albumKey = cursor.getString(4);
                String artist = cursor.getString(5);
                String artistKey = cursor.getString(6);
                String bookmark = cursor.getString(7);
                String composer = cursor.getString(8);
                String dateAdded = cursor.getString(9);
                String Duration = cursor.getString(10);
                String isAlarm = cursor.getString(11);
                String isMusic = cursor.getString(12);
                String isNotification = cursor.getString(13);
                String isProdcast = cursor.getString(14);
                String isRingtone = cursor.getString(15);
                String track = cursor.getString(16);
                String albumId = cursor.getString(17);

                Uri uri=Uri.parse(baseUri+id);

                try{
                    item.put("id", id);
                    item.put("name", name);
                    item.put("uri", uri);
                    item.put("path", "file://" +path);
                    item.put("album", album);
                    item.put("albumKey", albumKey);
                    item.put("artist", artist);
                    item.put("artistKey", artistKey);
                    item.put("bookmark", bookmark);
                    item.put("composer", composer);
                    item.put("dateAdded", dateAdded);
                    item.put("Duration", Duration);
                    item.put("isAlarm", isAlarm);
                    item.put("isMusic", isMusic);
                    item.put("isNotification", isNotification);
                    item.put("isProdcast", isProdcast);
                    item.put("isRingtone", isRingtone);
                    item.put("track", track);
                    item.put("albumId", albumId);

                }catch (JSONException e){
                    System.out.println(e.getMessage());
                    callback.error(e.getMessage());
                    return;
                }
                resArray.put(item);

            }
            cursor.close();
            try {
                data.put("data",resArray);
            }catch (JSONException e){
                System.out.println(e.getMessage());
                callback.error(e.getMessage());
                return;
            }

            callback.success(data);
        }
    }
}
