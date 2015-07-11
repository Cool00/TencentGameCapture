/**
 * 
 */
package game.capture.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * @author yangbing
 *
 */
public class JsonFileOutputWriterTool {

	public void jsonFileWriterTool(String data){
		try{

		      File file =new File("TencentGameJson.json");

		      //if file doesnt exists, then create it
		      if(!file.exists()){
		       file.createNewFile();
		      }
		      
		      //true = append file
		      FileOutputStream writerStream = new FileOutputStream(file.getName(),true);    

		      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8")); 
		      writer.write(data);
		      writer.close();

		     }catch(IOException e){
		      e.printStackTrace();
		     }
	}
}
