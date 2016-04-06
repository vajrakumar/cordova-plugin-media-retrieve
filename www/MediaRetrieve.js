var cordova = require('cordova');

var MediaRetrieve = {
    getImageList : function(successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, 'MediaRetrieve', 'image', []);
    },
    getAudioList : function(successCallback, errorCallback){
        cordova.exec(successCallback, errorCallback, 'MediaRetrieve', 'audio', []);
    },
    getVideoList : function(successCallback, errorCallback){
        cordova.exec(successCallback, errorCallback, 'MediaRetrieve', 'video', []);
    }
};

module.exports = MediaRetrieve; 