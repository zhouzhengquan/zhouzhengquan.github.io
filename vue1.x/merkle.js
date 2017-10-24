window.digitalData = {};
window.digitalData.newEvent1 = function () {
    $(window).trigger('CustomTagEvent1');    
};

window.digitalData.eventHandler1 = function () {
    $(window).on('CustomTagEvent1', function () { //CustomTagEvent 加载完执行
        console.log('CustomTagEvent1');
        _satellite.track('vuepageview1');
    });
};
window.digitalData.eventHandler1();

window.digitalData.newEvent2 = function () {
    $(window).trigger('CustomTagEvent2');    
};

window.digitalData.eventHandler2 = function () {
    $(window).on('CustomTagEvent2', function () { //CustomTagEvent 加载完执行
        console.log('CustomTagEvent2');
        _satellite.track('vuepageview2');
    });
};
window.digitalData.eventHandler2();
