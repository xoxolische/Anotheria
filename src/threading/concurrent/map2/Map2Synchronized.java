package threading.concurrent.map2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Map2Synchronized extends Thread {

	private static String[] data1;

	private static Map<Integer, String> map = new HashMap<>();

	public static void main(String[] args) {
		data1 = getWords();

		Map2Synchronized m1 = new Map2Synchronized();
		m1.start();
		Map2Synchronized m2 = new Map2Synchronized();
		m2.start();

		try {
			m1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			m2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Map<Integer, String> clean = new HashMap<>();
		for (int i = 0; i < data1.length; i++) {
			clean.put(data1[i].hashCode(), data1[i]);
		}
		System.out.println("??? : " + map.size());
		System.out.println("Constant value : " + clean.size());

	}

	@Override
	public void run() {
		for (int i = 0; i < data1.length; i++) {
			sPut(data1[i].hashCode(), data1[i] + this.getName());
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
			List<String> words = new LinkedList<>();
			BufferedReader br = getFileInBuffer("hp.txt");
			String s = br.readLine();
			while (s != null) {
				words.add(s);
				s = br.readLine();
			}
			return words.toArray(new String[words.size()]);
		} catch (Exception e) {
			return null;
		}
	}

}
