import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * http://winterbe.com/posts/2014/04/05/java8-nashorn-tutorial/
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, ScriptException, NoSuchMethodException {

        //создаем сценарий
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        //подключаем js файл
        engine.eval(new FileReader("src/Script.js"));

        String mathFunc = "2-3+45-2*4-6*3+(2^3)";

        //для вызова функции из js должен быть подключен данный интерфейс
        Invocable invocable = (Invocable) engine;

        //вызываем функцию из файла js подключенного выше
        Object result = invocable.invokeFunction("fun1", mathFunc);
        System.out.println(result);

        //напрямую без подключения js файла, можно выполнить js
        try {
            engine.eval("print(2-3+45-2*4-6*3+(2^3));");
        } catch (final ScriptException se) { se.printStackTrace(); }

    }

}
