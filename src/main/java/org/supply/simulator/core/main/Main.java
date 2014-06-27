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
    private static String testString;

    public Main() {
        System.out.println("hello");
    }


    public static void main(String[] args) throws Exception {
        System.out.println("and i was running");
        System.out.println(testString);
        System.out.println(myStaticBean.bigData);
        System.out.println(myStaticBean.id);
        System.out.println(myStaticBean.usefulNumber);
    }

    public void setTestString(String ts) {
        Main.testString = ts;
    }


    public void setMyStaticBean(SomeBean myStaticBean) {
        Main.myStaticBean = myStaticBean;
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
}
