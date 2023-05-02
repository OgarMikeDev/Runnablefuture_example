package runnable_example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public class Main {
    public static void main(String[] args) {
        RunnableFuture<String> future1 = new FutureTask<>(new RunnableExample(12000), "Task 1");
        RunnableFuture<String> future2 = new FutureTask<>(new RunnableExample(3000), "Task 2");
        RunnableFuture<String> future3 = new FutureTask<>(new RunnableExample(20000), "Task 3");

        List<RunnableFuture<String>> taskList = new ArrayList<>();
        taskList.add(future1);
        taskList.add(future2);
        taskList.add(future3);

        ExecutorService executor = Executors.newFixedThreadPool(4);
        taskList.forEach(executor::execute);

        ResultCheckerExample resultCheckerExample = new ResultCheckerExample(taskList);
        executor.execute(resultCheckerExample);

        executor.shutdown();

        System.out.println("The execution of the main program is not interrupted");
    }
}