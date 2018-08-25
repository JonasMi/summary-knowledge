package summary.knowledge.common.lamada;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.Test;

public class LamadaTests {

	@SuppressWarnings("unused")
	@Test
	public void test1() {

		// 1
		FileFilter[] fileFilters = new FileFilter[] { f -> f.exists(), f -> f.canRead(),
				f -> f.getName().startsWith("p") };
		for (int i = 0; i < fileFilters.length; i++) {
			fileFilters[i].accept(new File(""));
		}
		// 2
		List<Map<String, String>> list = new ArrayList<>();
		Stream<String> names = list.stream().<String>map(p -> p.get(""));
		// 3ת��
		Object o = (Runnable) () -> System.out.println("");

		//
		List<String> s = Collections.checkedList(new ArrayList<>(), String.class);

	}

	public void test2() {

	}


	public Runnable toDoLater() {
		return () -> System.out.println("later");
	}
}
