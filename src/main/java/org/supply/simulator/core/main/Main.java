package org.supply.simulator.core.main;

import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.supply.simulator.display.manager.chunk.impl.BasicChunk;
import org.supply.simulator.display.manager.chunk.impl.BasicChunkData;
import org.supply.simulator.display.manager.chunk.impl.FloatPositionByteColorChunkData;
import org.supply.simulator.executor.DispatchService;
import org.supply.simulator.executor.TaskManager;
import org.apache.logging.log4j.LogManager;
import org.supply.simulator.logging.HasLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 2/15/14
 * Time: 9:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main extends HasLogger {

    private static Logger logger = LogManager.getLogger(Main.class);
    private static DispatchService dispatchService;
    private static TaskManager taskManager;
    private static SessionFactory sessionFactory;

    static { //load everything
        new ClassPathXmlApplicationContext("/application-context.xml");
    }

    /**
     * The main method.
     *
     * @param args No arguments are currently used
     * @throws Exception No checked exceptions are thrown by this method.
     */
    public static void main(String[] args) throws Exception {
        logger.info("=========================== Starting main!! ===========================");
        logger.debug("Dispatch service is " + dispatchService.getClass().getName());
        logger.debug("SessionFactory is " + sessionFactory);

//        taskManager.execute(new Runnable() {
//            @Override
//            public void run() {
                logger.info("it's on");

                Long count = null;
                try {
//                    count = (Long) sessionFactory.openSession().createQuery("select count(*) from org.supply.simulator.display.manager.chunk.impl.BasicChunkData").uniqueResult();
//                    BasicChunkData<String,String,Integer> data = new BasicChunkData<String,String,Integer>();
                    BasicChunkData<List<Float>,List<Byte>> data = new FloatPositionByteColorChunkData();
                    List<Byte> colors = new ArrayList<Byte>();
                    List<Float> positions = new ArrayList<Float>();
//                    List<Integer> indices = new ArrayList<Integer>();

                    colors.add((byte)11);
                    colors.add((byte)12);

                    positions.add(1.2f);
                    positions.add(2.5f);

//                    indices.add(1);
//                    indices.add(2);


                    data.setColors(colors);
                    data.setPositions(positions);
                    //data.setIndices(indices);

//                    BasicChunk chunk = new BasicChunk();
//                    chunk.setData(data);
////                    data.setColors("red");
////                    data.setPositions("right");
////                    data.setIndices(1000000);
//
//                    Session session = sessionFactory.openSession();
//                    Transaction tx = session.beginTransaction();
//                    session.saveOrUpdate(chunk);
//                    session.flush();
////                    session.getTransaction().
//                    tx.commit();
//
//                    session.close();



                    BasicChunk chunk;
//                    chunk.setData(data);
//                    data.setColors("red");
//                    data.setPositions("right");
//                    data.setIndices(1000000);

                    Session session = sessionFactory.openSession();
                    Transaction tx = session.beginTransaction();
                    chunk = (BasicChunk)session.createQuery("from org.supply.simulator.display.manager.chunk.impl.BasicChunk where id = 1").uniqueResult();
                    logger.info(chunk);
                    logger.info(chunk.getData());
                    logger.info(chunk.getData().getColors());
                    session.flush();
//                    session.getTransaction().
                    tx.commit();

                    session.close();
                } catch (Exception e) {
                    logger.info(e);
                }
//                logger.info(count);
//            }
//        });

        logger.debug("Task manager is " + taskManager.getClass().getName());
        logger.info("================================ Done!! ===============================");
    }

    public void setDispatchService(DispatchService dispatchService) {
        Main.dispatchService = dispatchService;
    }

    public void setTaskManager(TaskManager taskManager) {
        Main.taskManager = taskManager;
    }

    @Override
    public void setLogger(Logger logger) {
        Main.logger = logger;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        Main.sessionFactory = sessionFactory;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }
}
