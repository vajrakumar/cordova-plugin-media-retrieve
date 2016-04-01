var cordova = require('cordova');

(function() {

    function updateImageList() {
        var onSuccess = function(data) {
        	console.log(data);
        }

        var onError = function(e) {
            console.log(e);
        }

        cordova.exec(onSuccess, onError, 'MediaRetrieve', 'image', []);
    }

    function updateAudioList() {
        var onSuccess = function(data) {
        	console.log(data)
        }

        var onError = function(e) {
            console.log(e);
        }

        cordova.exec(onSuccess, onError, 'MediaRetrieve', 'audio', []);
    }

    function updateVideoList() {
        var onSuccess = function(data) {
        	console.log(data);
        }

        var onError = function(e) {
            console.log(e);
        }

        cordova.exec(onSuccess, onError, 'MediaRetrieve', 'video', []);
    }

    updateImageList();
    updateAudioList();
    updateVideoList();

})();
