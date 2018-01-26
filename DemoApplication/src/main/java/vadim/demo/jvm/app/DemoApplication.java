package vadim.demo.jvm.app;

/**
 * Created by kenya on 2018/1/26.
 */
public class DemoApplication {
    public static void main(String[] args) {
        System.out.println("[Application - Main] Start application");
        String value = "Demonstration of Java bytecode manipulation capabilities";
        Text text = new Text();
        System.out.println("[Application - Main] Value passed to text display: " + value);
        text.display(value);
        System.out.println("[Application - Main] Complete application");
    }
}
