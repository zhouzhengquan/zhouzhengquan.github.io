$(function(){
    $('.link1').click(function () {
        window.digitalData.newEvent1({});
        console.log('datalayer pushed');
    });
});

$(function(){
    $('.link2').click(function () {
        window.digitalData.newEvent2({});
        console.log('datalayer pushed');
    });
});
