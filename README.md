# cordova-plugin-media-retrieve
A cordova plugin that can list the media files

1. Get image list from storage
```MediaRetrieve.getImageList(onSuccessCallback,onErrorCallBack)```
2. Get audio list from storage
```MediaRetrieve.getAudioList(onSuccessCallBack,onErrorCallBack)```

in `onSuccessCallBack`, a json object which contains `name:path` array  is passed