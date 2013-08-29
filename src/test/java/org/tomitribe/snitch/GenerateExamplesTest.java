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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version $Revision$ $Date$
 */
public class GenerateExamplesTest {

    @Test
    public void testGenerateBlue() throws Exception {
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
    }

    @Test
    public void testGenerateGreen() throws Exception {
        final PrintStream out = IO.print(new File("/Users/dblevins/work/tomitribe/snitch/src/test/java/org/tomitribe/snitch/Green.java"));
        out.println("package org.tomitribe.snitch;");
        out.println();
        out.println("import org.tomitribe.snitch.Tracker;");
        out.println();
        out.println("public class Green {");

        final Class[] types = new Class[]{byte.class, boolean.class, char.class, short.class, int.class, long.class, float.class, double.class, Date.class, URI.class};

        final List<String> parameters = new ArrayList<String>();

        final String template = IO.slurp(this.getClass().getResource("/return-types.txt"));

        int count = 0;
        for (Class type : types) {
            final Map<String, String> data = new HashMap<String, String>();
            data.put("parameters", Join.join(", ", new ParamCallback(), parameters));
            data.put("args", Join.join(", ", new ArgCallback(), parameters));
            data.put("type", type.getName());
            data.put("typeSimpleName", type.getSimpleName());
            data.put("number", (count++) + "");
            out.println(Substitution.format(template, data));

            parameters.add(type.getName());
        }

        out.println("}");
        out.close();
    }

    @Test
    public void testGenerateRed() throws Exception {
        final PrintStream out = IO.print(new File("/Users/dblevins/work/tomitribe/snitch/src/test/java/org/tomitribe/snitch/Red.java"));
        out.println("package org.tomitribe.snitch;");
        out.println();
        out.println("import org.tomitribe.snitch.Tracker;");
        out.println();
        out.println("public class Red {");

        final Class[] types = new Class[]{byte.class, boolean.class, char.class, short.class, int.class, long.class, float.class, double.class, Date.class, URI.class};

        final List<String> parameters = new ArrayList<String>();

        final String template = IO.slurp(this.getClass().getResource("/return-types.txt"));

        int count = 0;
        for (Class type : types) {
            final Map<String, String> data = new HashMap<String, String>();
            data.put("parameters", Join.join(", ", new ArrayParamCallback(), parameters));
            data.put("args", Join.join(", ", new ArgCallback(), parameters));
            data.put("type", type.getName()+"[]");
            data.put("typeSimpleName", type.getSimpleName());
            data.put("number", (count++) + "");
            out.println(Substitution.format(template, data));

            parameters.add(type.getName());
        }

        out.println("}");
        out.close();
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

    private static class ArrayParamCallback implements Join.NameCallback<String> {
        int i;

        @Override
        public String getName(String object) {
            return object + "[] a" + (i++);
        }
    }
}
