// Mutable string
public final class DynString {
	private char[] data;

	// Construct DynString of given length
	public DynString(int len) {
		resizeData(len);
	}

	// Default constructor, empty.
	public DynString() {
		resizeData(0);
	}

	// Construct from char array
	public DynString(char[] src) {
		this(src.length);
		for (int i = 0; i < size(); ++i) {
			data[i] = src[i];
		}
	}

	// Construct from another DynString
	public DynString(DynString other) {
		this(other.size());
		for (int i = 0; i < other.size(); ++i) {
			data[i] = other.get(i);
		}
	}

	// String constructor
	public DynString(String s) {
		this(s.length());
		for (int i = 0; i < s.length(); ++i) {
			data[i] = s.charAt(i);
		}
	}

	// Fill ctor
	public DynString(int len, char c) {
		this(len);
		for (int i = 0; i < len; ++i) {
			data[i] = c;
		}
	}

	// Get current size
	public int size() {
		return data.length;
	}

	// Get element
	public char get(int idx) {
		return data[idx];
	}

	// Set element
	public void set(int idx, char c) {
		data[idx] = c;
	}

	// Empty check
	public boolean empty() {
		return size() == 0;
	}

	// Delete old data and replace with empty char array of specified length
	private void resizeData(int n) {
		data = new char[n];
	}

	// Resize preserving existing contents by truncating
	public void resize(int n) {
		char[] old = data;
		resizeData(n); // replace old data with new empty char array of new length
		int toCopy = Math.min(old.length, n);
		for (int i = 0; i < toCopy; ++i) {
			data[i] = old[i];
		}
	}

	// Clear data
	public void clear() {
		resize(0);
	}

	// Concatenate with another DynString
	public DynString concat(DynString other) {
		int newLen = this.size() + other.size();
		DynString res = new DynString(newLen);
		for (int i = 0; i < this.size(); ++i)
			res.data[i] = this.data[i];
		for (int i = 0; i < other.size(); ++i)
			res.data[this.size() + i] = other.data[i];
		return res;
	}

	// Concatenate with String
	public DynString concat(String s) {
		int newLen = this.size() + s.length();
		DynString res = new DynString(newLen);
		for (int i = 0; i < this.size(); ++i)
			res.data[i] = this.data[i];
		for (int i = 0; i < s.length(); ++i)
			res.data[this.size() + i] = s.charAt(i);
		return res;
	}

	// Substring [start, end) - end exclusive
	public DynString substr(int start, int end) {
		if (start < 0)
			start = 0;
		if (end > size())
			end = size();
		int len = Math.max(0, end - start);
		DynString res = new DynString(len);
		for (int i = 0; i < len; ++i)
			res.data[i] = this.data[start + i];
		return res;
	}

	// Substring from start to end
	public DynString substr(int start) {
		return substr(start, size());
	}

	// Equals handling char[], DynString, and String
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (o instanceof DynString other) {
			if (other.size() != this.size())
				return false;
			for (int i = 0; i < size(); ++i)
				if (this.data[i] != other.data[i])
					return false;
			return true;
		}
		if (o instanceof char[] carr) {
			if (carr.length != this.size())
				return false;
			for (int i = 0; i < carr.length; ++i)
				if (this.data[i] != carr[i])
					return false;
			return true;
		}
		if (o instanceof String s) {
			if (s.length() != this.size())
				return false;
			for (int i = 0; i < s.length(); ++i)
				if (this.data[i] != s.charAt(i))
					return false;
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return new String(data);
	}
}