
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Search for shortest paths between start and end points on a circuit board
 * as read from an input file using either a stack or queue as the underlying
 * search state storage structure and displaying output to the console or to
 * a GUI according to options specified via command-line arguments.
 * 
 * @author mvail
 */
public class CircuitTracer {

	/**
	 * Launch the program.
	 * 
	 * @param args three required arguments:
	 *             first arg: -s for stack or -q for queue
	 *             second arg: -c for console output or -g for GUI output
	 *             third arg: input file name
	 */
	public static void main(String[] args) {
		new CircuitTracer(args); 
	}

	/** Print instructions for running CircuitTracer from the command line. */
	private void printUsage() {
		System.out.println("""
			Usage: java CircuitTracer -s|-q -c|-g filename
			 -s for stack or -q for queue
			 -c for console or -g for GUI
			""");
	}

	/**
	 * Set up the CircuitBoard and all other components based on command
	 * line arguments.
	 * 
	 * @param args command line arguments passed through from main()
	 */
	public CircuitTracer(String[] args) {
		CircuitBoard circuitBoard = null;

		if (args.length != 3) {
			printUsage();
			return; 
		}
		// saving command line args
		String storageOption = args[0];
        String outputOption = args[1];    
        String inputFileName = args[2];

		//check if the storage and output options are valid
		if (!storageOption.equals("-s") && !storageOption.equals("-q")) {
            printUsage();
            return;
        }
	
        if (!outputOption.equals("-c") && !outputOption.equals("-g")) {
            printUsage();
            return;
        }

		// Create the storage structure
		Storage<TraceState> stateStore = null;

		switch (args[0]) {
			case "-s":
				stateStore = Storage.getStackInstance();
				break;
			case "-q":
				stateStore = Storage.getQueueInstance();
				break;
			default:
				printUsage();
				return;
		}

		// Initialize the circuit board from the file
		try {
            circuitBoard = new CircuitBoard(inputFileName);
        } catch (FileNotFoundException e) {
            System.out.println(e + " File was not found.");
            return;
        } catch (InvalidFileFormatException e) {
            System.out.println(e + " File is not in the correct format.");
            return;
        }

		// Find and display the paths
		ArrayList<TraceState> bestPaths = new ArrayList<TraceState>();

		int startX = circuitBoard.getStartingPoint().x;
		int startY = circuitBoard.getStartingPoint().y;


		if (circuitBoard.isOpen(startX + 1, startY)) {
			stateStore.store(new TraceState(circuitBoard, startX + 1, startY));
		}
		if (circuitBoard.isOpen(startX - 1, startY)) {
			stateStore.store(new TraceState(circuitBoard, startX - 1, startY));
		}
		if (circuitBoard.isOpen(startX, startY + 1)) {
			stateStore.store(new TraceState(circuitBoard, startX, startY + 1));
		}
		if (circuitBoard.isOpen(startX, startY - 1)) {
			stateStore.store(new TraceState(circuitBoard, startX, startY - 1));
		}

		while (!stateStore.isEmpty()) {
			TraceState currentTrace = stateStore.retrieve();

			if (currentTrace.isSolution()) {
				if (bestPaths.isEmpty() || currentTrace.pathLength() == bestPaths.get(0).pathLength()) {
					bestPaths.add(currentTrace);

				} else if (currentTrace.pathLength() < bestPaths.get(0).pathLength()) {
					bestPaths.clear();
					bestPaths.add(currentTrace);
				}
			} else {

				int currentX  = currentTrace.getRow();
				int currentY  = currentTrace.getCol();

				if (currentTrace.isOpen(currentX - 1, currentY)) {
					stateStore.store(new TraceState(currentTrace, currentX - 1, currentY));
				}
				if (currentTrace.isOpen(currentX + 1, currentY)) {
					stateStore.store(new TraceState(currentTrace, currentX + 1, currentY));
				}
				if (currentTrace.isOpen(currentX, currentY - 1)) {
					stateStore.store(new TraceState(currentTrace, currentX, currentY - 1));
				}
				if (currentTrace.isOpen(currentX, currentY + 1)) {
					stateStore.store(new TraceState(currentTrace, currentX, currentY + 1));
				}
			}
		}

		// Output the best paths
		switch (args[1]) {
			case "-c":
				for (TraceState path : bestPaths) {
					System.out.println(path.getBoard().toString());
				}
				break;
			case "-g":
				System.out.println("Did not implement GUI output.");
				break;
			default:
				printUsage();
				return;
		}
	}

} // class CircuitTracer
