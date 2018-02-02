package path;

import com.csvreader.CsvReader;

import net.sf.json.JSONObject;

import org.apache.commons.collections.functors.ForClosure;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;

import java.awt.print.Pageable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class paste {
	public static Logger logger = Logger.getLogger(paste.class);
	public static ArrayList<String[]> csvFileList = new ArrayList<String[]>();
	public static List<List<String>> pageCombined = new ArrayList<List<String>>();
	public static List<String> pagePath = new ArrayList<>();
	public static List<String> uniqueNode = new ArrayList<>();
	public static List<String> nodeTimes = new ArrayList<>();
	public static List<String> nodesJson = new ArrayList<>();	
	public static List<String> userPathJson = new ArrayList<>();
	public static List<List<String>> pageCombinedNew = new ArrayList<List<String>>();


	
	public static void main(String[] args) {
		//dataProess("C:\\Users\\zzhou\\Desktop\\eclipse\\RecommendationFP-Tree\\new\\pathdemo\\");
		renamePageCombined();
		//logger.info(pageCombined);
		logger.info(uniqueNode.toString());
		logger.info(nodeTimes);
		//logger.info(nodesJson);
		//generate final json format document
		//add nodes
		String nodes = "{'nodes':[";
		nodes = nodes + "{'name':'Start'},";
		for (int i = 0; i<nodesJson.size()-1;i++){
		nodes = nodes + "{" + nodesJson.get(i) + "}," + "\r\n";
		}
		nodes = nodes + "{" + nodesJson.get(nodesJson.size()-1) + "}],";
		//add links
		nodes = nodes + "'links':[";
		for (int i = 0; i<userPathJson.size()-1;i++){
			nodes = nodes + "{" + userPathJson.get(i) + "}," + "\r\n";
		}
		nodes = nodes + "{" + userPathJson.get(userPathJson.size()-1) + "}]}";
		nodes = nodes.replace("\'","\"");
		
		//logger.info(nodes);
		logger.info("Document has been successfully created");
		//print to txt
        byte[] buff=new byte[]{};  
        try   
        {   
            buff=nodes.getBytes();  
            FileOutputStream out=new FileOutputStream("C:/Users/zzhou/Desktop/eclipse/RecommendationFP-Tree/new/pathdemo/out.txt");  
            out.write(buff,0,buff.length);  
              
        }   
        catch (FileNotFoundException e)   
        {  
            e.printStackTrace();  
        }  
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
	}
	public static ArrayList<String[]> processAll(String rootPath) {
		String tempList = rootPath + "pathtestraw.csv";
		CsvReader reader = null;
		try {
			reader = new CsvReader(tempList, ',', Charset.forName("UTF-8"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ignore header
		try {
			reader.readHeaders();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// read by line
		try {
			while (reader.readRecord()) {
				csvFileList.add(reader.getValues());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(csvFileList.size());
		reader.close();
		return csvFileList;
	}

	public static List<List<String>> dataProess(String filePath) {
		filePath = "C:\\Users\\zzhou\\Desktop\\eclipse\\RecommendationFP-Tree\\new\\pathdemo\\";
		processAll(filePath);		
		// to map
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		int loopSize = csvFileList.size();
		for (int i = 0; i < loopSize; i++) {
			Map mapNew = new HashMap();
			mapNew.put(csvFileList.get(i)[0].toString(), csvFileList.get(i)[1].toString());
			list.add(mapNew);
		}
/*		logger.info(list);*/
		// 要求将上面的List<Map>中的map中key相同的value合并
		Map m = mapCombine(list);
		/* logger.info(m); */
		// 将Map Key 转化为List
	/*	List<String> mapKeyList = new ArrayList<String>(m.keySet());*/
		// 将Map Value 转化为List	 
	    List<List<String>> mapValuesList = new ArrayList<List<String>>(m.values());	
		// put to pagecombined
/*		logger.info(mapValuesList.size());*/
		for (int i = 0; i < mapValuesList.size(); i++) {
			pageCombined.add(mapValuesList.get(i));
		}
		
		//get unique path
		
		
		return pageCombined;
	}

	//get frequency
	public static List<String> pageSeperated() {
		String filePath = "C:\\Users\\zzhou\\Desktop\\eclipse\\RecommendationFP-Tree\\new\\pathdemo\\";
		List<List<String>> nodeTest = dataProess(filePath);
		//get unique ABC
		for (int i = 0; i<nodeTest.size();i++){
			for (int j = 0;j<nodeTest.get(i).size();j++){
			uniqueNode.add(nodeTest.get(i).get(j) + (j+1));
			}
		}
		//create nodejson and calculate times
		Set<String> nodesJsonUnique = new HashSet<>();
		for (int i = 0; i< uniqueNode.size();i++){
		int occurTimes = Collections.frequency(uniqueNode,uniqueNode.get(i));
		nodesJsonUnique.add("'name'"+":"+"'"+uniqueNode.get(i)+"'");
		}
		nodesJson.addAll(nodesJsonUnique);
		
		pageCombined = paste.pageCombined;
		List<String> pageCombinedSon = new ArrayList<>();
		String pageCombinedSonNew = null;
		for (int i = 0; i<pageCombined.size();i++){
			for (int j = 0;j<pageCombined.get(i).size();j++){
				pageCombinedSonNew = pageCombined.get(i).get(j)+(j+1);
				pageCombinedSon.add(pageCombinedSonNew);
			}
			pageCombinedNew.add(pageCombinedSon);
			pageCombinedSon =  new ArrayList<>();
		}
		int occurTimes = 0;
		for (int i = 0;i<pageCombinedNew.size();i++){
			String abc = "";

			for (int j = 0; j<pageCombinedNew.get(i).size();j++){
				if (j<pageCombinedNew.get(i).size()-1){
				 abc = abc + pageCombinedNew.get(i).get(j) + ", ";
				}else {
					abc = abc + pageCombinedNew.get(i).get(j);
				}				
				for (int k = 0; k<pageCombinedNew.size();k++){
					boolean judgeTimes = pageCombinedNew.get(k).toString().contains(abc);
					if(judgeTimes){
						occurTimes = occurTimes+1;
					}
				}
				nodeTimes.add(String.valueOf(occurTimes));
				occurTimes = 0;
			}

		}		
		return nodeTimes;
		
	}
	
	//create String pagecombined with timeseries
	public static List<String> renamePageCombined() {
		pageSeperated();
		List<String> renameTimes = nodeTimes;
		List<String> renameTest = uniqueNode;
		Set<String> userPathJsonUnique = new HashSet<>();
		//judge whether is the source of user flow
		//add 1st target
		userPathJsonUnique.add("'source'"+":"+"'Start'"+","+"'target'"+
			    ":"+"'"+renameTest.get(0)+"'"+","+"'value'"+":"+"'"+renameTimes.get(0)+"'"+"\r\n");
		//add 1~n-1 targets
		for (int i = 1;i<renameTest.size()-1;i++){
			if (Integer.valueOf(renameTest.get(i).substring(1,2))<2){
				userPathJsonUnique.add("'source'"+":"+"'Start'"+","+"'target'"+
			    ":"+"'"+renameTest.get(i)+"'"+","+"'value'"+":"+"'"+renameTimes.get(i)+"'"+"\r\n");
			}
			else{
				userPathJsonUnique.add("'source'"+":"+"'"+renameTest.get(i-1)+"'"+","+"'target'"+
			    ":"+"'"+renameTest.get(i)+"'"+","+"'value'"+":"+"'"+renameTimes.get(i)+"'"+"\r\n");
			}
		}
		//add n target
		userPathJsonUnique.add("'source'"+":"+"'"+renameTest.get(renameTest.size()-2)+"'"+","+"'target'"+
			    ":"+"'"+renameTest.get(renameTest.size()-1)+"'"+","+"'value'"
				+":"+"'"+renameTimes.get(renameTest.size()-1)+"'"+"\r\n");
		
		userPathJson.addAll(userPathJsonUnique);
/*		logger.info(renameTest);
		logger.info(userPathJson.size());*/
		return userPathJson;

		
	}

	public static Map mapCombine(List<Map<String, String>> list) {
		Map<Object, List> map = new HashMap<>();
		for (Map m : list) {
			Iterator<Object> it = m.keySet().iterator();
			while (it.hasNext()) {
				Object key = it.next();
				if (!map.containsKey(key)) {
					List newList = new ArrayList<>();
					newList.add(m.get(key));
					map.put(key, newList);
				} else {
					map.get(key).add(m.get(key));
				}
			}
		}
		return map;
	}

}
