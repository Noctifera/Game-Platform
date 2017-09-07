package modal;


public class GhostThread extends Thread {
	Ghost gh;

	private volatile boolean supress = false;

	public GhostThread(Ghost gh) {
		super();
		this.gh = gh;
	}
	public void run(){
		while(!supress){
			gh.update();


			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
	}


}
