###cordova-plugin-media-retrieve

1. Get image list from storage<br>
`MediaRetrieve.getImageList(onSuccessCallback,onErrorCallBack)`

2. Get audio list from storage<br>
 `MediaRetrieve.getAudioList(onSuccessCallBack,onErrorCallBack)`

3. Get video list from storage<br>
`MediaRetrieve.getVideoList(onSuccessCallBack,onErrorCallBack)`

####return
```
{
    [
        "name":"display name",
        "uri":"content uri",
        "path":"absolute path"
    ]
}
```