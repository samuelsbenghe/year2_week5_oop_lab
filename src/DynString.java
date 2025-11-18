// Edit this file so that the unit tests in DynStringTest.main() compile and run without reporting any errors.

// Mutable string
public class DynString {
	private char[] data;

	// Construct of given size
	public DynString(int len){
		resizeData(len);
	}

	// Construct copying values from char array
	public DynString(char[] src){
		this(src.length);
		for(int i=0; i<size(); ++i){
			data[i] = src[i];
		}
	}

	// Get current size
	public int size(){
		return data.length;
	}

	// Resize internal array; zeroes all values
	private void resizeData(int n){
		data = new char[n];
	}
}
