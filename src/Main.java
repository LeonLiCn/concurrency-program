import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.get(null);
        Map<String, Object> map1 = new HashMap<>();
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        String s = "ss";
        System.out.println("Hello World!");
    }
}
