import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;



public class FactorFindingMain {

	
	public static void main(String[] args) {
		
		mainFactorCheck();
		mainPrimeCheck();
	}

	public static void mainFactorCheck() {
		System.out.println("Threads before: " + Thread.getAllStackTraces().keySet());
		long startTime = System.currentTimeMillis();
//		long testNum = 999999999999999989L; // prime example
		long testNum = 999999999999999988L;
		Collection<Long> factors = findFactors(testNum);  
//TODO: Change to multi-threaded once you implement that...
//		Collection<Long> factors = findFactorsMulti(testNum, 2);  
		long endTime = System.currentTimeMillis();
		// For debugging, print threads before & after, to make sure you didn't leave any extra FactorFindingThreads threads running!
		System.out.println("Threads after: " + Thread.getAllStackTraces().keySet());
		System.out.println("Factors: " + factors);
		System.out.println("Elapsed time (ms): " + (endTime - startTime));		
	}

	public static void mainPrimeCheck() { 
		System.out.println("Threads before: " + Thread.getAllStackTraces().keySet());
		long startTime = System.currentTimeMillis();
//		long testNum = 999999999999999989L; // prime example
		long testNum = 999999999999999988L; // composite example
		boolean result = isPrime(testNum);   
//TODO: Change to multi-threaded once you implement that...
//		boolean result = isPrimeMulti(testNum, 8);  
		long endTime = System.currentTimeMillis();
		// For debugging, print threads before & after, to make sure you didn't leave any extra FactorFindingThreads threads running!
		System.out.println("Threads after: " + Thread.getAllStackTraces().keySet());
		System.out.println("isPrime: " + result);
		System.out.println("Elapsed time (ms): " + (endTime - startTime));		
	}
	

	/** A naive (single-threaded) algorithm for finding the factors of a given number.  
	 * (Side note: more efficient algorithms using fancier math exist, but
	 *   we're using this just to demo parallelizing a task.) 
	 */
	public static Collection<Long> findFactors(long num) {
		Collection<Long> factors = new LinkedList<Long>();
		long sqrtNum = (long) Math.sqrt(num);
		for (long div = 1; div <= sqrtNum; div++) {
			if (num % div == 0) {
				factors.add(div);
				if (div * div != num) { // don't add the square root twice for perfect squares... 
					factors.add(num / div);
				}
			} 
		}
		return factors;		
	}

	/** A naive single-threaded algorithm for testing if the given num is prime..  
	 * 
	 * (Side note:  More efficient primality testing algorithms exist 
	 *             (using complex math) but we will use this as an example 
	 *             to demo using Threads on a simple parallelizable task. */
	public static boolean isPrime(long num) {
		if (num <= 1) {
			return false;
		}
		long sqrtNum = (long) Math.sqrt(num);
		for (long div = 2; div <= sqrtNum; div++) {
			if (num % div == 0) {
				return false;
			}			
		}
		return true;		
	}

	/** Uses the same simple algorithm as above, except that it parallelizes it
	 * by creating/running multiple instances of FactorFindingThread.
	 * 
	 * @param num - number to test to see if its prime 
	 * @param threadCount - how many parallel threads to execute
	 * @return a collection of all the num's factors
	 */
	public static Collection<Long> findFactorsMulti(long num, int threadCount) {

		// TODO: Create an array to hold the right number of FactorFindingThreads
		// TODO: Create a ConcurrentLinkedQueue for the threads to put found factors in.
		// TODO: Create each thread, with the appropriate starting/ending range,
		//       in order for all the threads to cooperatively check from 1 up 
		//       to the sqrt of the number, and start each thread running.
		// TODO: this method should then wait for all the created threads to complete,
		//       before returning the factors that were found.

// DEBUG TIP: You can run this after starting the threads to see which threads are running. 
//		System.out.println("Threads during: " + Thread.getAllStackTraces().keySet());

		return null;	
	}


	/** Uses the same simple prime checking idea as isPrime above, 
	 * except that it parallelizes it by creating/running multiple 
	 * instances of FactorFindingThread.
	 * 
	 * @param num - number to test to see if its prime 
	 * @param threadCount - how many parallel threads to execute
	 * @return true if num is prime, false otherwise.
	 */
	public static boolean isPrimeMulti(long num, int threadCount) {
		
		// TODO: first, check & return false if the number is <= 1.
		
		// TODO: This method should be similar to the findFactorsMulti method, EXCEPT
		//       that it should check numbers between 2 and the sqrt of the number,
		//       and instead of waiting for all the threads to finish, this
		//       method's thread should keep running, and keep polling the
		//       factors queue to see if any factor has been found, and if it has
		//       then we know the number isn't prime, so we can stop processing early.
		//       To stop processing early, this method should *interrupt* all the threads, 
		//       to signal them to stop running, and THEN wait for each to stop running,
		//       before returning false.
		//       To know if the number IS prime, this thread must check to see if ALL
		//       of the FactorFindingThreads have finished running, before returning true.
		return true;	
	}


	
}
