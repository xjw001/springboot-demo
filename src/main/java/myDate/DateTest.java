package myDate;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class DateTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("20201103", DateTimeFormatter.ofPattern("yyyyMMdd"));
            }
        };
        List<Future<LocalDate>> dateList = new ArrayList<Future<LocalDate>>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            dateList.add(executorService.submit(task));
        }
        for (Future<LocalDate> d : dateList){
            System.out.println(d.get());
        }
        executorService.shutdown();
    }
}
