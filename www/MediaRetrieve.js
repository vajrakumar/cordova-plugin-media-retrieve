var cordova = require('cordova');

var MediaRetrieve = {
    getImageList : function(successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, 'MediaRetrieve', 'image', []);
    },
};

module.exports = MediaRetrieve; 
