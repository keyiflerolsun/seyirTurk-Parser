package com.swenauk.mainmenu.Classes;

import com.swenauk.mainmenu.Statics;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/* loaded from: classes3.dex */
public class JSUnpacker {
    public static String Unpack(String str) {
        ScriptEngine engineByName = new ScriptEngineManager().getEngineByName("js");
        try {
            engineByName.eval("function unPack (code) {\n\tfunction indent (code) {\n\t\ttry {\n\t\tvar tabs = 0, old=-1, add='';\n\t\tfor(var i=0;i<code.length;i++) {\n\t\t\tif(code[i].indexOf(\"{\") != -1) tabs++;\n\t\t\tif(code[i].indexOf(\"}\") != -1) tabs--;\n\t\t\t\n\t\t\tif(old != tabs) {\n\t\t\t\told = tabs;\n\t\t\t\tadd = \"\";\n\t\t\t\twhile (old > 0) {\n\t\t\t\t\tadd += \"\\t\";\n\t\t\t\t\told--;\n\t\t\t\t}\n\t\t\t\told = tabs;\n\t\t\t}\n\t\t\t\n\t\t\tcode[i] = add + code[i];\n\t\t}\n\t\t} finally {\n\t\t\ttabs = null;\n\t\t\told = null;\n\t\t\tadd = null;\n\t\t}\n\t\treturn code;\n\t}\n    \n    var env = {\n        eval: function (c) {\n            code = c;\n        },\n        window: {},\n        document: {}\n    };\n    \n    eval(\"with(env) {\" + code + \"}\");\n\t\n\tcode = (code+\"\").replace(/;/g, \";\\n\").replace(/{/g, \"\\n{\\n\").replace(/}/g, \"\\n}\\n\").replace(/\\n;\\n/g, \";\\n\").replace(/\\n\\n/g, \"\\n\");\n\t\n    code = code.split(\"\\n\");\n    code = indent(code);\n    \n    code = code.join(\"\\n\");\n    return code;\n} ");
            return (String) ((Invocable) engineByName).invokeFunction("unPack", str);
        } catch (NoSuchMethodException | ScriptException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String DecryptDBox(String str, String str2) {
        ScriptEngine engineByName = new ScriptEngineManager().getEngineByName("js");
        try {
            engineByName.eval(Statics.testJS);
            return (String) ((Invocable) engineByName).invokeFunction("parser", str, str2);
        } catch (NoSuchMethodException | ScriptException e) {
            e.printStackTrace();
            return "";
        }
    }
}
