package threading.concurrent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import utils.BrokenString;

public class Map2 extends Thread {

	// 466544 lines

	private static String[] data1;

	private static BrokenString[] data2;

	private static Map<Integer, String> map = new HashMap<>();

	public static void main(String[] args) {
		data1 = getWords();
		// data2 = getBrokenWords();

		Map2 m1 = new Map2();
		m1.start();
		Map2 m2 = new Map2();
		m2.start();

		try {
			m1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			m2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map<Integer, String> clean = new HashMap<>();
		for (int i = 0; i < data1.length; i++) {
			clean.put(data1[i].hashCode(), data1[i]);
		}
		// System.out.println(map);
		System.out.println("??? : " + map.size());
		System.out.println("Constant value : " + clean.size());

	}

	@Override
	public void run() {
		for (int i = 0; i < data1.length; i++) {
			// System.out.println(data1[i] + " Hello from thread : " + this.getName());
			sPut(data1[i].hashCode(), data1[i] + this.getName());

			// map.put(data2[i].hashCode(), data2[i] + this.getName());
		}
	}

	private static synchronized void sPut(int k, String v) {
		map.put(k, v);
	}

	private static BufferedReader getFileInBuffer(String fileName) {
		try {
			File f = new File("hp.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			return br;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String[] getWords() {
		try {
			// int c = 0;
			List<String> words = new LinkedList<>();
			BufferedReader br = getFileInBuffer("hp.txt");
			String s = br.readLine();
			while (s != null) {
				// c++;
				// String[] w = s.split(" ");
				// for(String sw : w) {
				// words.add(sw);
				// }
				words.add(s);
				s = br.readLine();
			}
			// System.out.println(c);
			// System.exit(0);
			return words.toArray(new String[words.size()]);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

}
