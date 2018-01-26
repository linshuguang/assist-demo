package vadim.demo.jvm.app;

/**
 * Created by kenya on 2018/1/26.
 */
public class Text {

    int compute(int n){
        long sleepTime = n;
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return n;
    }


    private void test(int n){
        System.out.println("[Application - Text display] Text display is going to "+n);
        int result = compute(n);
        System.out.println("[Application - Text display] result is "+result);
    }

    public void display(String text) {
        long sleepTime = 1000;
        long sleepStartTime;
        long sleepEndTime;

        test(1);
        test(10);
        test(100);
        test(101);
        test(200);

        System.out.println("[Application - Text display] Text display is going to sleep for " + sleepTime + " ms");
        sleepStartTime = System.nanoTime();
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sleepEndTime = System.nanoTime();
        System.out.println("[Application - Text display] Text display wakes up");
        System.out.println("[Application - Text display] Text display sleep time: "
                + ((sleepEndTime - sleepStartTime) / 1000000) + " ms");
        System.out.println("[Application - Text display] Output: " + text);

    }
}
