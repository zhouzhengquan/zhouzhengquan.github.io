window.digitalData = {};
window.digitalData.newEvent1 = function () {
    $(window).trigger('CustomTagEvent1');    
};

window.digitalData.eventHandler1 = function () {
    $(window).on('CustomTagEvent1', function () { //CustomTagEvent 加载完执行
        console.log('CustomTagEvent1');
        _satellite.track('page_view');
    });
};
window.digitalData.eventHandler1();

window.digitalData = {};
window.digitalData.newEvent2 = function () {
    $(window).trigger('CustomTagEvent1');    
};

window.digitalData.eventHandler2 = function () {
    $(window).on('CustomTagEvent2', function () { //CustomTagEvent 加载完执行
        console.log('CustomTagEvent1');
        _satellite.track('page_view');
    });
};
window.digitalData.eventHandler2();