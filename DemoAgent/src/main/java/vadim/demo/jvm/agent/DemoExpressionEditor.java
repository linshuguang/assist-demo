package vadim.demo.jvm.agent;

/**
 * Created by kenya on 2018/1/26.
 */
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
public class DemoExpressionEditor extends ExprEditor {

    private String newFieldName;

    DemoExpressionEditor(String newFieldName){
        this.newFieldName = newFieldName;
    }



    @Override
    public void edit(MethodCall method) throws CannotCompileException {



        if (method.getMethodName().equals("compute")) {

            //method.replace("{if($1>100 && "+newFieldName+" >0) { $_="+newFieldName+" +1;} else{ $_ = $proceed($$);};" +
             //       "  "+newFieldName+"=$_;}");
            //method.replace("{$_= "+newFieldName+"+1;}");
            method.replace("{ if($1 > 100 ) { $_ = "+newFieldName+" + 1; } else { $_ = $proceed($$); } "+newFieldName+" = $_;  }");

            //method.replace("{$_ = 2222;}");
        }


    }
}