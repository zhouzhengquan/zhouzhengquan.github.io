var chars = ['0','1','2','3','4','5','6','7','8','9'];

function generateMixed(n) {
     var res = "";
     for(var i = 0; i < n ; i ++) {
         var id = Math.ceil(Math.random()*9);
         res += chars[id];
     }
     return res;
}

var ID_random = generateMixed(10); 

function ikea_pageflow_1(){
   window.dataLayer.push({
     'event': 'GA-event',
     'page': {
      'name': 'flow 1'
     },
      'eventDetail': { 
        'category': 'flow 1 category', 
        'action': 'flow 1 action',
        'label': 'flow 1 label'
      },
     'user': {
       'id': ID_random
     },
     'site': {
       'environment': 'live'
     },
   });
 }

 function ikea_pageflow_2(){
   window.dataLayer.push({
     'event': 'GA-event',
     'page': {
      'name': ID_random
     },
     'eventDetail': { 
        'category': 'flow 2 category', 
        'action': 'flow 2 action',
        'label': 'flow 2 label'
      },
     'user': {
       'id': '666'
     },
     'site': {
       'environment': 'live'
     },
   });
 }

