import java.util.concurrent.ConcurrentLinkedQueue;


public class FactorFindingThread extends Thread {
	private long num;
	private long start;
	private long stop;
	private ConcurrentLinkedQueue<Long> factorsFound;
	
	/** 
	 * 
	 * @param num - the number to find factors of
	 * @param start - the first number this thread should start testing for factors at (inclusive)
	 * @param stop - the high range to check for factors up until (exclusive).   
	 * @param factorsFound - the thread-safe collection that any discovered factors should be added to
	 */
	public FactorFindingThread(long num, long start, long stop, ConcurrentLinkedQueue<Long> factorsFound) {
		super("FactorFinder (" + start + "-" + stop + ")");
		this.num = num;
		this.start = start;
		this.stop = stop;	
		this.factorsFound = factorsFound;
	}
	
	@Override
	public void run() {
		//check possible divisors for num, between start (inclusive) and stop (exclusive)
		//any factors found (along with their pairing factor) should be added to the factorsFound queue.
		//Also, while running keep checking whether this thread has been interrupted, and if so stop early.   
		
		for (long div = start; div < stop; div++) {
			if (num % div == 0) {
				factorsFound.add(div);
				if (div * div != num) { // don't add the square root twice for perfect squares... 
					factorsFound.add(num / div);
				}
			} 
			if (Thread.currentThread().isInterrupted()){
				return;
			}
		}
	}
	

}
