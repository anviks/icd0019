package collections.benchmark;

import oo.hide.Timer;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class Runner {

    @Test
    public void benchmarkDifferentImplementations() {

        Set<Integer> set1 = new MySet();
        Set<Integer> set2 = new TreeSet<>();
        Set<Integer> set3 = new HashSet<>();

        Timer timer1 = new Timer();
        for (int i = 0; i < 30_000; i++) {
            set1.add(i);
        }
        System.out.println(timer1.getPassedTime());

        Timer timer2 = new Timer();
        for (int i = 0; i < 30_000; i++) {
            set2.add(i);
        }
        System.out.println(set2.size());
        System.out.println(timer2.getPassedTime());

        Timer timer3 = new Timer();
        for (int i = 0; i < 30_000; i++) {
            set3.add(i);
        }
        System.out.println(set3.size());
        System.out.println(Arrays.toString(set3.toArray()));
        System.out.println(timer3.getPassedTime());
    }

}
