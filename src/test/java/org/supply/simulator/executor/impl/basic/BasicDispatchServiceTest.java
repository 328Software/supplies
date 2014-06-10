package org.supply.simulator.executor.impl.basic;

import com.carrotsearch.junitbenchmarks.AbstractBenchmark;
import org.junit.Before;
import org.junit.Test;
import org.supply.simulator.executor.RepeatableTask;
import org.supply.simulator.executor.ScheduleInformation;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created by Brandon on 6/9/2014.
 */
public class BasicDispatchServiceTest  {



    BasicDispatchService dispatchService;

    BasicRepeatableTask task1;

    @Before
    public void createFixture() {
        task1 = new BasicRepeatableTask() {
            @Override
            public void run() {
                System.out.println("Hello world!");
            }
        };
        BasicRepeatingScheduleInformation info = new BasicRepeatingScheduleInformation();
        info.setIntervalMillis(100);
        info.setNumberOfExecutions(5);
//        info.setMaxDurationMillis();
        task1.setScheduleInformation(info);

        dispatchService = new BasicDispatchService();
        dispatchService.setExecutor(new ScheduledThreadPoolExecutor(1));
    }

    @Test
    public void testAddTask() {

        dispatchService.addTask(task1);

        //without concurrency this can cause some issues
        while(dispatchService.getTaskCount() > 0);
    }

    @Test
    public void testRemoveTask() {
        dispatchService.addTask(task1);

        assert(1 == dispatchService.getTaskCount());

        dispatchService.removeTask(task1);

        assert(0 == dispatchService.getTaskCount());
    }
}
