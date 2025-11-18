/* IS52059B: Object-oriented Programming
Computing, Goldsmiths */

// Testing function
// This is similar to assert, but does not halt execution upon failure and does not require special compiler flags to activate.
public final class Test {
	public static void test(boolean v, String msg) {
		try{
			if(!v) throw new RuntimeException(msg);
		} catch(Exception e){
			System.out.println("Test failed: " + e.getMessage());
		}
	}
}
