import java.util.Random;

public class Elf implements Runnable {

	enum ElfState {
		WORKING, TROUBLE, AT_SANTAS_DOOR
	};

	private ElfState state;
	/**
	 * The number associated with the Elf
	 */
	private int number;
	private Random rand = new Random();
	private SantaScenario scenario;
	private boolean bool = true;

	public Elf(int number, SantaScenario scenario) {
		this.number = number;
		this.scenario = scenario;
		this.state = ElfState.WORKING;
	}

	//acquire();
	//semaphore;
	
	public ElfState getState() {
		return state;
	}

	/**
	 * Santa might call this function to fix the trouble
	 * @param state
	 */
	public void setState(ElfState state) {
		this.state = state;
	}


	@Override
	public void run() {
		int elfCounter = 0;
		while (bool) {
      // wait a day
  		try {
  			Thread.sleep(100);
  		} catch (InterruptedException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
			switch (state) {
			case WORKING: {
				// at each day, there is a 1% chance that an elf runs into
				// trouble.
				if (rand.nextDouble() < 0.01) {
					state = ElfState.TROUBLE;
					elfCounter++;
					System.out.println(elfCounter);
				}
				break;
			}
			case TROUBLE:
				// FIXME: if possible, move to Santa's door
				state = ElfState.AT_SANTAS_DOOR;
				break;
			case AT_SANTAS_DOOR:
				// FIXME: if feasible, wake up Santa
				if(elfCounter == 3)
				{
					scenario.santa.elvesInTrouble();
					elfCounter = 0;
				}
				break;
			}
		}
	}

	/**
	 * Report about my state
	 */
	public void report() {
		System.out.println("Elf " + number + " : " + state);
	}

	public void endProcess()
	{
		bool = false;
	}
}
