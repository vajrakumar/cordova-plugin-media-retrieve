# cordova-plugin-media-retrieve
> A cordova plugin which can list all the media files

## Install

Install with [npm](https://www.npmjs.com/package/cordova-plugin-media-retrieve)

`cordova plugin add cordova-plugin-media-retrieve`

## Usage

* Get image list from storage<br>
`MediaRetrieve.getImageList(onSuccessCallback,onErrorCallBack)`


* Get audio list from storage<br>
 `MediaRetrieve.getAudioList(onSuccessCallBack,onErrorCallBack)`


* Get video list from storage<br>
`MediaRetrieve.getVideoList(onSuccessCallBack,onErrorCallBack)`

## Return

return a **json object** which contains the data you want to get
```
{
    "data":[
        {
            "name":"display name",
            "uri":"content uri",
            "path":"absolute path"
        },
        {
            ...
        }
    ]
}
```