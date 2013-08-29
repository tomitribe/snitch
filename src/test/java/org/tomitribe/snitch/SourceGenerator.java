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
import java.io.FileNotFoundException;
import java.io.IOException;
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
public class SourceGenerator {

    public static void main(String[] args) throws Exception {
        final SourceGenerator generator = new SourceGenerator();
        generator.testGenerateBlue();
        generator.testGenerateGreen();
        generator.testGenerateRed();
        generator.testGenerateOrange();
        generator.testGenerateYellow();
        generator.testGeneratePurple();
        generator.testGeneratePink();
        generator.testGenerateMagenta();
    }

    public void testGenerateBlue() throws Exception {
        generate("Blue", new Processor() {
            @Override
            public Map<String, String> process(List<String> parameters, Class type) {
                final Map<String, String> data = new HashMap<String, String>();
                data.put("keywords", "public");
                data.put("keywordsAfter", "public");
                data.put("parameters", "");
                data.put("args", "");
                data.put("type", type.getName());
                data.put("typeSimpleName", type.getSimpleName());

                return data;
            }
        });
    }


    public void testGenerateGreen() throws Exception {
        generate("Green", new Processor() {
            @Override
            public Map<String, String> process(List<String> parameters, Class type) {
                final Map<String, String> data = new HashMap<String, String>();
                data.put("keywords", "public");
                data.put("keywordsAfter", "public");
                data.put("parameters", Join.join(", ", new ParamCallback(), parameters));
                data.put("args", Join.join(", ", new ArgCallback(), parameters));
                data.put("type", type.getName());
                data.put("typeSimpleName", type.getSimpleName());

                return data;
            }
        });
    }

    public void testGenerateYellow() throws Exception {
        generate("Yellow", new Processor() {
            @Override
            public Map<String, String> process(List<String> parameters, Class type) {
                final Map<String, String> data = new HashMap<String, String>();
                data.put("keywords", "public synchronized");
                data.put("keywordsAfter", "public");
                data.put("parameters", Join.join(", ", new ParamCallback(), parameters));
                data.put("args", Join.join(", ", new ArgCallback(), parameters));
                data.put("type", type.getName());
                data.put("typeSimpleName", type.getSimpleName());

                return data;
            }
        });
    }

    public void testGeneratePurple() throws Exception {
        generate("Purple", new Processor() {
            @Override
            public Map<String, String> process(List<String> parameters, Class type) {
                final Map<String, String> data = new HashMap<String, String>();
                data.put("keywords", "private");
                data.put("keywordsAfter", "private");
                data.put("parameters", Join.join(", ", new ParamCallback(), parameters));
                data.put("args", Join.join(", ", new ArgCallback(), parameters));
                data.put("type", type.getName());
                data.put("typeSimpleName", type.getSimpleName());

                return data;
            }
        });
    }

    public void testGeneratePink() throws Exception {
        generate("Pink", new Processor() {
            @Override
            public Map<String, String> process(List<String> parameters, Class type) {
                final Map<String, String> data = new HashMap<String, String>();
                data.put("keywords", "protected");
                data.put("keywordsAfter", "protected");
                data.put("parameters", Join.join(", ", new ParamCallback(), parameters));
                data.put("args", Join.join(", ", new ArgCallback(), parameters));
                data.put("type", type.getName());
                data.put("typeSimpleName", type.getSimpleName());

                return data;
            }
        });
    }

    public void testGenerateMagenta() throws Exception {
        generate("Magenta", new Processor() {
            @Override
            public Map<String, String> process(List<String> parameters, Class type) {
                final Map<String, String> data = new HashMap<String, String>();
                data.put("keywords", "");
                data.put("keywordsAfter", "");
                data.put("parameters", Join.join(", ", new ParamCallback(), parameters));
                data.put("args", Join.join(", ", new ArgCallback(), parameters));
                data.put("type", type.getName());
                data.put("typeSimpleName", type.getSimpleName());

                return data;
            }
        });
    }

    public void testGenerateRed() throws Exception {
        generate("Red", new Processor() {
            @Override
            public Map<String, String> process(List<String> parameters, Class type) {
                final Map<String, String> data = new HashMap<String, String>();
                data.put("keywords", "public");
                data.put("keywordsAfter", "public");
                data.put("parameters", Join.join(", ", new ArrayParamCallback(), parameters));
                data.put("args", Join.join(", ", new ArgCallback(), parameters));
                data.put("type", type.getName() + "[]");
                data.put("typeSimpleName", type.getSimpleName());

                return data;
            }
        });
    }

    public void testGenerateOrange() throws Exception {
        generate("Orange", new Processor() {
            @Override
            public Map<String, String> process(List<String> parameters, Class type) {
                final Map<String, String> data = new HashMap<String, String>();
                data.put("keywords", "public static");
                data.put("keywordsAfter", "public static");
                data.put("parameters", Join.join(", ", new ArrayParamCallback(), parameters));
                data.put("args", Join.join(", ", new ArgCallback(), parameters));
                data.put("type", type.getName() + "[]");
                data.put("typeSimpleName", type.getSimpleName());

                return data;
            }
        });
    }

    private void generate(String name, Processor processor) throws IOException {
        final PrintStream before = start(name + "Before");
        final PrintStream after = start(name + "After");

        final Class[] types = new Class[]{byte.class, boolean.class, char.class, short.class, int.class, long.class, float.class, double.class, Date.class, URI.class};

        final List<String> parameters = new ArrayList<String>();

        final String beforeTemplate = IO.slurp(this.getClass().getResource("/before.txt"));
        final String afterTemplate = IO.slurp(this.getClass().getResource("/after.txt"));

        int count = 0;
        for (Class type : types) {
            final Map<String, String> data = processor.process(parameters, type);
            data.put("number", (count++) + "");

            before.println(Substitution.format(beforeTemplate, data));
            after.println(Substitution.format(afterTemplate, data));

            parameters.add(type.getName());
        }

        before.println("}");
        before.close();

        after.println("}");
        after.close();
    }

    public static interface Processor {
        Map<String, String> process(List<String> parameters, Class type);
    }

    private PrintStream start(String name) throws FileNotFoundException {
        final PrintStream out = IO.print(new File("/Users/dblevins/work/tomitribe/snitch/src/test/java/org/tomitribe/snitch/gen/" + name + ".java"));
        out.println("package org.tomitribe.snitch.gen;");
        out.println();
        out.println("import org.tomitribe.snitch.Tracker;");
        out.println();
        out.println("public class " + name + " {");
        return out;
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
