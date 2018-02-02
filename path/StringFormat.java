package path;

import com.csvreader.CsvReader;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StringFormat {
	 public static Logger logger = Logger.getLogger(StringFormat.class); 
	public static ArrayList<String[]> csvFileList = new ArrayList<String[]>();
	public static ArrayList<String[]> IDVehicleList = new ArrayList<String[]>();
	public static void main(String[] args) {
		processAll("C:/Users/zzhou/Desktop/eclipse/RecommendationFP-Tree/data");
	}

	public static void processAll(String rootPath) {
		String tempList = rootPath + "/Report/Report3.csv";
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
		logger.info(csvFileList.size());
		reader.close();
		//start merge
        List<Map> list = new ArrayList<>();
        Map<String, Object> m1 = new HashMap<String, Object>(); 
        for (int i =0; i< csvFileList.size();i++){
        	m1.put(csvFileList.get(i)[0], csvFileList.get(i)[1]);
        }
        list.add(m1); 
        Map m = mapCombine(list);  
        System.out.println(m);          
    }       
        public static Map mapCombine(List<Map> list) {  
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

