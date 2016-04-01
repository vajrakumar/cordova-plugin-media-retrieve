###cordova-plugin-media-retrieve
use indexed to store data. when your app start, this plugin will read the image, audio, video list and store in into indexde. So, you can just read the list from the indexedDB.

So, you don't need to use any API to use this plugin.

Todo list:
- [ ] 将从Java中获取的数据写入indexedDB
- [ ] 了解cordova基本原理，看是否需要在deviceready事件触发之后才能操作ContentProvider
- [ ] Java中对传入参数的检查和异常处理
