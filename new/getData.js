var fso=new ActiveXObject(Scripting.FileSystemObject); 
var f=fso.createtextfile("C:\\Users\\zzhou\\Desktop\\eclipse\\RecommendationFP-Tree\\new\\pathtest.txt",1,true); 
while (!f.AtEndOfStream) 
    { 
    f.Readline(); 
    }
    f.close(); 