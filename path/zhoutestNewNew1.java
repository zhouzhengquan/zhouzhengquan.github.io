package path;
public class zhoutestNewNew1 {
	 public static void main(String args[]) {
		 
		 int a = "b".compareTo("b");
		 
	  String[] strs = {"Apple","peach","banana","Pear"};  
	  for (int i = 0; i < strs.length-1; i++) {   
	   for (int j =i+1; j < strs.length; j++) {
	    int intTemp = strs[i].compareToIgnoreCase(strs[j]);
	    String strTemp = "";
	    if(intTemp>0){
	     strTemp = strs[j];
	     strs[j] = strs[i];
	     strs[i] = strTemp;
		   System.out.println(strTemp);
	    }
	    
	   }

	  }  
	  
	  for (int i = 0; i < strs.length; i++) {
	   System.out.println(a);
	  }
	 }
	}
