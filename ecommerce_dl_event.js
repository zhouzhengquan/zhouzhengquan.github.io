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
       'id': '555'
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
      'name': 'flow 2'
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

