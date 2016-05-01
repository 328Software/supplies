package org.supply.simulator.core.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.supply.simulator.display.core.impl.BasicDisplayCore;
import org.supply.simulator.display.manager.Manager;
import org.supply.simulator.display.renderer.chunk.impl.BasicChunkRenderer;
import org.supply.simulator.executor.DispatchService;
import org.supply.simulator.executor.TaskManager;
import org.supply.simulator.logging.HasLogger;

//import org.supply.simulator.display.extra.MockShaderEngine;

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
    private static Manager<BasicChunkRenderer> manager;
    private static BasicDisplayCore displayCore;
    private static MainMenus mainMenus;

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




        logger.debug("Task manager is " + taskManager.getClass().getName());
        logger.info("================================ Done!! ===============================");

        boolean close = false;
        while(!close) {
            Options gameOptions = mainMenus.start();
            Game game = Game.newInstance(gameOptions);
            mainMenus.destroyMenus();
            close = game.start();
        }


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

    public static void setManager(Manager<BasicChunkRenderer> manager) {
        Main.manager = manager;
    }

    public void setDisplayCore(BasicDisplayCore displayCore) {
        Main.displayCore = displayCore;
    }

    public static void setMainMenus(MainMenus mainMenus) {
        Main.mainMenus = mainMenus;
    }
}
