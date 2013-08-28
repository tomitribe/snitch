/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;

import org.junit.Test;
import org.tomitribe.snitch.util.IO;
import org.tomitribe.snitch.util.Join;

import java.io.File;
import java.io.PrintStream;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @version $Revision$ $Date$
 */
public class GenerateExamplesTest {

    @Test
    public void testGenerate() throws Exception {
        final PrintStream out = IO.print(new File("/Users/dblevins/work/tomitribe/snitch/src/test/java/org/tomitribe/snitch/Blue.java"));
        out.println("package org.tomitribe.snitch;");
        out.println();
        out.println("import org.tomitribe.snitch.Tracker;");
        out.println();
        out.println("public class Blue {");

        final Class[] types = new Class[]{byte.class, boolean.class, char.class, short.class, int.class, long.class, float.class, double.class, Date.class, URI.class};

        final Map<String, String> data = new HashMap<String, String>();
        data.put("parameters", "");
        data.put("args", "");

        final String template = IO.slurp(this.getClass().getResource("/return-types.txt"));

        int count = 0;
        for (Class type : types) {
            data.put("type", type.getName());
            data.put("typeSimpleName", type.getSimpleName());
            data.put("number", (count++) + "");
            out.println(Substitution.format(template, data));
        }

        out.println("}");
        out.close();

//        Asmifier.asmify(Blue.class, "modified");
    }

    private static class ArgCallback implements Join.NameCallback<String> {
        int i;

        @Override
        public String getName(String object) {
            return "a" + (i++);
        }
    }

    private static class ParamCallback implements Join.NameCallback<String> {
        int i;

        @Override
        public String getName(String object) {
            return object + " a" + (i++);
        }
    }
}