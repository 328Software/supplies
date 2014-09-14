package org.supply.simulator.core.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.supply.simulator.display.assetengine.indices.impl.BasicChunkIndexEngine;
import org.supply.simulator.display.assetengine.shader.impl.BasicShaderEngine;
import org.supply.simulator.display.assetengine.texture.impl.BasicTextureEngine;
import org.supply.simulator.display.core.impl.BasicDisplayCore;
import org.supply.simulator.display.manager.Manager;
import org.supply.simulator.display.manager.chunk.impl.DAOWiredChunkManager;
import org.supply.simulator.display.manager.impl.BasicChunkManager;
import org.supply.simulator.display.renderer.chunk.impl.BasicChunkRenderer;
import org.supply.simulator.display.window.Window;
import org.supply.simulator.display.window.impl.BasicPlayWindow;
import org.supply.simulator.executor.DispatchService;
import org.supply.simulator.executor.TaskManager;
import org.supply.simulator.logging.HasLogger;

import java.util.ArrayList;

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





        //TODO wire all this up with spring
        ///////
//        BasicDisplayCore displayCore = new BasicDisplayCore();
//        BasicPlayWindow playWindow = new BasicPlayWindow();
//        BasicShaderEngine shaderEngine = new BasicShaderEngine();
//        BasicTextureEngine textureEngine = new BasicTextureEngine();
//        DAOWiredChunkManager chunkManager = new DAOWiredChunkManager();
//        BasicChunkIndexEngine indexEngine = new BasicChunkIndexEngine();
//
//
//        chunkManager.setIndexEngine(indexEngine);
//        //chunkManager.setChunkDAO();
//
//        playWindow.setChunkManager(chunkManager);
//        playWindow.setShaderEngine(shaderEngine);
//        playWindow.setTextureEngine(textureEngine);
//        displayCore.setWindow(playWindow);
        /////
        try {
            displayCore.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        taskManager.schedule(displayCore);
//        dispatchService.
//        dispatchService.addTask(displayCore);
//        while(taskManager.getAllScheduledTasks().size()>0) {
//            Thread.yield();
//        }


//        taskManager.execute(new Runnable() {
//            @Override
////            public void run() {
//                logger.info("it's on");
//
//                Long count = null;
//                try {
//
//                    BasicPlayWindow window;
//
//                    BasicDisplayCore.build("YOU SUCK");
//
//                    MockShaderEngine<ShaderProgramType,BasicShaderHandle> shaderEngine = new MockShaderEngine<ShaderProgramType,BasicShaderHandle>();
//
//                    shaderEngine.set(ShaderProgramType.PLAY, "shaders/vertex.glsl");
//                    shaderEngine.set(ShaderProgramType.PLAY, "shaders/fragments.glsl");
//
//
//                    window = new BasicPlayWindow();
//                    window.setShaderEngine(shaderEngine);
//                    window.setCamera(new MockCamera());
//                    window.setChunkManager(manager);
//
//                    window.build();
//                    manager.update(null);
//
//                    while (!Display.isCloseRequested()) {
//                        window.render();
//
//                        BasicDisplayCore.render();
//                    }
//
//                    window.destroy();
//                    BasicDisplayCore.destroy();
//
//                } catch (Exception e) {
//                    logger.info(e);
//                    e.printStackTrace();
//                }
////                logger.info(count);
////            }
////        });

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

    public static void setManager(Manager<BasicChunkRenderer> manager) {
        Main.manager = manager;
    }

    public void setDisplayCore(BasicDisplayCore displayCore) {
        Main.displayCore = displayCore;
    }
}
