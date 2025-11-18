// Runs unit tests on DynString class
public class DynStringTest {

	public static void test(boolean v, String msg) {
		Test.test(v, msg);
	}

	public static void main(String[] args) {
		{ // Check fields: only one char[]
			var fields = DynString.class.getDeclaredFields();
			test(fields.length == 1, "more than one field");
			test(fields[0].getType().getCanonicalName().equals("char[]"), "field is not char[]");
			// Must not remove existing resizeData() method (should be used internally)
			var hasResizeDataMethod = false;
			for (var m : DynString.class.getDeclaredMethods()) {
				if (m.getName() == "resizeData") {
					hasResizeDataMethod = true;
					break;
				}
			}
			test(hasResizeDataMethod, "missing resizeData() method");
		}

		// * Move this toggle comment down as you implement new features

		{ // Default ctor
			var s = new DynString();
			test(s.empty(), "default ctor: not empty");
		}

		{ // char[] ctor and get/set
			char[] charArray = { 'x', 'y', 'z' };
			var s = new DynString(charArray);
			test(s.size() == 3, "char[] ctor: wrong size");
			test(s.get(0) == 'x', "get(0) wrong value");
			test(s.get(1) == 'y', "get(1) wrong value");
			test(s.get(2) == 'z', "get(2) wrong value");
			s.set(0, 'a');
			test(s.get(0) == 'a', "set(0) incorrect mutation");
		}

		{ // String ctor and get/set
			var s = new DynString("abc");
			test(s.size() == 3, "String ctor: wrong size");
			test(s.get(0) == 'a', "String ctor: wrong value at 0");
			test(s.get(1) == 'b', "String ctor: wrong value at 1");
			test(s.get(2) == 'c', "String ctor: wrong value at 2");
		}

		{ // Copy ctor
			var s1 = new DynString("hijk");
			var s2 = new DynString(s1);
			test(s2.size() == 4, "copy ctor: wrong size");
			test(s1.get(0) == s2.get(0), "copy ctor: wrong value at 0");
			test(s1.get(1) == s2.get(1), "copy ctor: wrong value at 1");
			test(s1.get(2) == s2.get(2), "copy ctor: wrong value at 2");
			test(s1.get(3) == s2.get(3), "copy ctor: wrong value at 3");
		}

		{ // Fill ctor
			var s = new DynString(3, 'o');
			test(s.size() == 3, "fill ctor: wrong size");
			for (int i = 0; i < s.size(); ++i)
				test(s.get(i) == 'o', "fill ctor: wrong value");
		}

		{ // Element mutation
			var s = new DynString("tea");
			s.set(0, 'p');
			test(s.get(0) == 'p', "set element 0");
			s.set(2, 't');
			test(s.get(2) == 't', "set element 2");
		}

		{ // Resize and clear
			var s = new DynString("abcd");

			s.resize(2);
			test(s.size() == 2, "resize down: wrong size");
			test(s.get(0) == 'a', "resize down: wrong value at index 0");
			test(s.get(1) == 'b', "resize down: wrong value at index 1");

			s.resize(4);
			test(s.size() == 4, "resize up: wrong size");
			test(s.get(0) == 'a', "resize up: wrong value at index 0");
			test(s.get(1) == 'b', "resize up: wrong value at index 1");
			test(s.get(2) == '\0', "resize up: wrong value at index 2");
			test(s.get(3) == '\0', "resize up: wrong value at index 3");

			s.clear();
			test(s.empty(), "clear did not empty");
		}

		{ // equals
			var s = new DynString(4, 'o');
			test(s.equals(new char[] { 'o', 'o', 'o', 'o' }), "equals: char[]");
			test(s.equals(new DynString("oooo")), "equals: DynString");
			test(s.equals("oooo"), "equals: String");
			test(!s.equals("ooo"), "equals: String");
			test(!s.equals("ooooo"), "equals: String");
		}

		{ // substr
			var s = new DynString("steal");
			test(s.substr(1, 4).equals("tea"), "substr(int,int): wrong result");
			test(s.substr(0, 2).equals(new DynString("st")), "substr(int,int): wrong result");
			test(s.substr(1).equals("teal"), "substr(int): wrong result");
			test(s.substr(2).equals("eal"), "substr(int): wrong result");
		}

		{ // concat
			var a = new DynString("Hello");
			var b = new DynString("World");
			var c = a.concat(" ").concat(b).concat("!");
			test(c.equals("Hello World!"), "concat: wrong result");
		}
		// */
	}
}
