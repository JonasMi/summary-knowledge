package summary.knowledge.common.cache.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

import com.google.common.util.concurrent.MoreExecutors;

public class MapTests {
	
	private Object object;
	
	//VisualVM
	
	@Test
	public void test() {
		List<String> list = Collections.synchronizedList(new ArrayList<>());
		Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
		ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
		LinkedBlockingQueue<String> linkedBlockQueue = new LinkedBlockingQueue<>();
		LinkedTransferQueue<String> linkedTransferQueue = new LinkedTransferQueue<>();
		
		ReentrantLock lock = new ReentrantLock();
		
		
		
		int a[] = new int[10];
		Arrays.fill(a, 100);
		
		int b[] = new int[20];
		
		
		System.arraycopy(a, 0, b, 10, a.length);
		
		System.out.println(Arrays.toString(b));
		
	}
}

