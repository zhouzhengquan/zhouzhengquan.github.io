package path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import path.*;

public class testtxt {
	public static Logger logger = Logger.getLogger(testtxt.class);
	public static ArrayList<String[]> test = new ArrayList<String[]>();
	public static List<String> nodeTimes = new ArrayList<>();
	public static List<String> uniqueNode = new ArrayList<>();
	public static List<List<String>> pageCombinedTest = new ArrayList<List<String>>();
	public static List<List<String>> pageCombinedTestNew = new ArrayList<List<String>>();

	public static void main(String [] args){ 	
		paste.pageSeperated();	
		pageCombinedTest = paste.pageCombined;
		List<String> pageCombinedSon = new ArrayList<>();
		String pageCombinedSonNew = null;
		for (int i = 0; i<pageCombinedTest.size();i++){
			for (int j = 0;j<pageCombinedTest.get(i).size();j++){
				pageCombinedSonNew = pageCombinedTest.get(i).get(j)+(j+1);
				pageCombinedSon.add(pageCombinedSonNew);
			}
			pageCombinedTestNew.add(pageCombinedSon);
			pageCombinedSon =  new ArrayList<>();
		}
		List<String> nodetimesnew = new ArrayList<>();
		int occurTimes = 0;
		for (int i = 0;i<pageCombinedTestNew.size();i++){
			String abc = "";

			for (int j = 0; j<pageCombinedTestNew.get(i).size();j++){
				if (j<pageCombinedTestNew.get(i).size()-1){
				 abc = abc + pageCombinedTestNew.get(i).get(j) + ", ";
				}else {
					abc = abc + pageCombinedTestNew.get(i).get(j);
				}				
				for (int k = 0; k<pageCombinedTestNew.size();k++){
					boolean judgeTimes = pageCombinedTestNew.get(k).toString().contains(abc);
					if(judgeTimes){
						occurTimes = occurTimes+1;
					}
				}
				nodetimesnew.add(String.valueOf(occurTimes));
				occurTimes = 0;
			}

		}
		logger.info(nodetimesnew);
	
}
}
