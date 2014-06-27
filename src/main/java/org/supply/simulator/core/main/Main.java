package org.supply.simulator.core.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 2/15/14
 * Time: 9:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static ApplicationContext context = new ClassPathXmlApplicationContext("/org/supply/simulator/core/main/test.xml");
    public static SomeBean myStaticBean;

    public Main() {
        System.out.println("hello");
    }

    private static String testString;


    public static void main(String[] args) throws Exception {
        System.out.println("and i was running");
        System.out.println(testString);
        System.out.println(myStaticBean.bigData);
        System.out.println(myStaticBean.id);
        System.out.println(myStaticBean.usefulNumber);
    }

    public static void setTestString(String ts) {
        testString = ts;
    }


    public static class SomeBean {
        int usefulNumber;
        String id;
        Double bigData;



        public void setUsefulNumber(int usefulNumber) {
            this.usefulNumber = usefulNumber;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setBigData(Double bigData) {
            this.bigData = bigData;
        }
    }

    public static void setMyStaticBean(SomeBean myStaticBean) {
        Main.myStaticBean = myStaticBean;
    }
}
