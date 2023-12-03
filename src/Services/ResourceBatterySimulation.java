package Services;

import Model.Battery;
import Model.EnergySource;

import java.util.Random;
import java.util.concurrent.*;

public class ResourceBatterySimulation {
	
	public ResourceBatterySimulation() {
		
	}
	
	public void Simulating() {
		ResourceBuffer resourceBuffer = new ResourceBuffer(5);
		int numberOfEnergySources = 5;
		ExecutorService energySources = Executors.newFixedThreadPool(numberOfEnergySources);
		Random random = new Random();           
		
		while(true) {
			int ranVal = random.nextInt(100);
			Battery battery = new Battery(ranVal, resourceBuffer);
			new Thread(battery).start();
			
			for (int i = 0; i < numberOfEnergySources; i++) {
	            energySources.execute(new EnergySource("Energy Source-" + i, resourceBuffer));
	        }
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
