/**
 * 
 */
package game.capture.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author yangbing
 *
 */
public class FileWriterTool {

	public void fileWriterTool(String data){
		try{

		      File file =new File("fileWriterTool.txt");

		      //if file doesnt exists, then create it
		      if(!file.exists()){
		       file.createNewFile();
		      }

		      //true = append file
//		      FileWriter fileWritter = new FileWriter(file.getName(),true);//追加文件内容
		      FileWriter fileWritter = new FileWriter(file);//重写文件内容
		      BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		      bufferWritter.write(data);
		      bufferWritter.close();

//		      System.out.println("Done");

		     }catch(IOException e){
		      e.printStackTrace();
		     }
	}
}
