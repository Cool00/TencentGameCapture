/**
 * 
 */
package game.capture.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * @author yangbing
 *
 */
public class ReadLineTool {

	public String readLineTool() throws Exception{
		FileInputStream file = new FileInputStream("fileWriterTool.txt"); 
		// DataInputStream dr = new DataInputStream(f); 
		BufferedReader br= new BufferedReader(new InputStreamReader(file,"utf-8"));
		String line =  null;
		StringBuilder contentBuf = new StringBuilder();
		while((line = br.readLine()) != null){ 
			if(line.contains("data-apkurl")){
//                System.out.println(line);
                contentBuf.append(line); 
            }
		} 
		String buf = contentBuf.toString();  
        int beginIx = buf.indexOf("http");  
        int endIx = buf.indexOf("appname");  
//        String result = buf.substring(beginIx, endIx);  
//        System.out.println("" + buf.substring(beginIx, endIx));
        return buf.substring(beginIx, endIx);
	}
}
