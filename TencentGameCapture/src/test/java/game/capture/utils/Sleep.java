/**
 * 
 */
package game.capture.utils;

/**
 * @author yangbing
 *
 */
public class Sleep {
	
	public void sleep(int seconds){
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
