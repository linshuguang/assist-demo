package vadim.demo.jvm.agent;

/**
 * Created by kenya on 2018/1/26.
 */
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.*;
import javassist.expr.ExprEditor;
public class DemoClassFileTransformer implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        String instrumentedClassName = "vadim.demo.jvm.app.Text";
        String instrumentedMethodName = "test";
        String newFieldName = "xxxxxxxxx";

        byte[] bytecode = classfileBuffer;
        try {
            ClassPool cPool = ClassPool.getDefault();
            CtClass ctClass = cPool.makeClass(new ByteArrayInputStream(bytecode));


            CtMethod[] ctClassMethods = ctClass.getDeclaredMethods();
            for (CtMethod ctClassMethod : ctClassMethods) {
                if (ctClassMethod.getDeclaringClass().getName().equals(instrumentedClassName)
                        && ctClassMethod.getName().equals(instrumentedMethodName)) {

                    CtField f = CtField.make("public int "+newFieldName+" = 0;", ctClass);
                    ctClass.addField(f);    // initial value is 0.

                    ExprEditor instrumentationExpressionEditor = new DemoExpressionEditor(newFieldName);
                    ctClassMethod.instrument(instrumentationExpressionEditor);


                    bytecode = ctClass.toBytecode();


                }
            }
        } catch (IOException e) {
            throw new IllegalClassFormatException(e.getMessage());
        } catch (RuntimeException e) {
            throw new IllegalClassFormatException(e.getMessage());
        } catch (CannotCompileException e) {
            throw new IllegalClassFormatException(e.getMessage());
        }
        return bytecode;
    }
}
