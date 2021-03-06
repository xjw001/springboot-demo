package MyThread2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class AtomicTest {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(10);
        System.out.println(i.getAndAdd(1));
        System.out.println(i.get());
        i.updateAndGet(value -> value *5);
        System.out.println(i.get());
    }
}
