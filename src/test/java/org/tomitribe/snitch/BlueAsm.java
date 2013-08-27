/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

/**
 * @version $Revision$ $Date$
 */
public class BlueAsm implements Opcodes {

    public void doit(ClassWriter cw) {
        GeneratorAdapter mg = null;

        {
            final Method method = Method.getMethod("void voidMethod9(org.tomitribe.snitch.Blue$Arg0,org.tomitribe.snitch.Blue$Arg1,org.tomitribe.snitch.Blue$Arg2,org.tomitribe.snitch.Blue$Arg3,org.tomitribe.snitch.Blue$Arg4,org.tomitribe.snitch.Blue$Arg5,org.tomitribe.snitch.Blue$Arg6,org.tomitribe.snitch.Blue$Arg7,org.tomitribe.snitch.Blue$Arg8)");
            mg = new GeneratorAdapter(1, method, null, new Type[]{Type.getObjectType("java/lang/IllegalStateException")}, cw);
            mg.visitCode();
            Label label0 = mg.newLabel();
            Label label1 = mg.newLabel();
            Label label2 = mg.newLabel();
            mg.visitTryCatchBlock(label0, label1, label2, null);
            Label label3 = mg.newLabel();
            mg.visitTryCatchBlock(label2, label3, label2, null);
            mg.invokeStatic(Type.getObjectType("java/lang/System"), Method.getMethod("long nanoTime()"));
            int local0 = mg.newLocal(Type.LONG_TYPE);
            mg.storeLocal(local0);
            mg.mark(label0);
            mg.loadThis();
            mg.loadArg(0);
            mg.loadArg(1);
            mg.loadArg(2);
            mg.loadArg(3);
            mg.loadArg(4);
            mg.loadArg(5);
            mg.loadArg(6);
            mg.loadArg(7);
            mg.loadArg(8);
            mg.invokeVirtual(Type.getObjectType("org/tomitribe/snitch/Blue"), Method.getMethod("void track$voidMethod9(org.tomitribe.snitch.Blue$Arg0,org.tomitribe.snitch.Blue$Arg1,org.tomitribe.snitch.Blue$Arg2,org.tomitribe.snitch.Blue$Arg3,org.tomitribe.snitch.Blue$Arg4,org.tomitribe.snitch.Blue$Arg5,org.tomitribe.snitch.Blue$Arg6,org.tomitribe.snitch.Blue$Arg7,org.tomitribe.snitch.Blue$Arg8)"));
            mg.mark(label1);
            mg.push("theTag");
            mg.loadLocal(local0);
            mg.invokeStatic(Type.getObjectType("org/tomitribe/snitch/Tracker"), Method.getMethod("void track(java.lang.String,long)"));
            Label label4 = mg.newLabel();
            mg.goTo(label4);
            mg.mark(label2);
            mg.visitFrame(Opcodes.F_FULL
                    , 11
                    , new Object[]{"org/tomitribe/snitch/Blue"
                    , "org/tomitribe/snitch/Blue$Arg0"
                    , "org/tomitribe/snitch/Blue$Arg1"
                    , "org/tomitribe/snitch/Blue$Arg2"
                    , "org/tomitribe/snitch/Blue$Arg3"
                    , "org/tomitribe/snitch/Blue$Arg4"
                    , "org/tomitribe/snitch/Blue$Arg5"
                    , "org/tomitribe/snitch/Blue$Arg6"
                    , "org/tomitribe/snitch/Blue$Arg7"
                    , "org/tomitribe/snitch/Blue$Arg8"
                    , Opcodes.LONG}
                    , 1
                    , new Object[]{"java/lang/Throwable"});
            int local1 = mg.newLocal(Type.getObjectType("java/lang/Object"));
            mg.storeLocal(local1);
            mg.mark(label3);
            mg.push("theTag");
            mg.loadLocal(local0);
            mg.invokeStatic(Type.getObjectType("org/tomitribe/snitch/Tracker"), Method.getMethod("void track(java.lang.String,long)"));
            mg.loadLocal(local1);
            mg.throwException();
            mg.mark(label4);
            mg.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mg.returnValue();
            mg.endMethod();
            mg.visitEnd();
        }
        {
            mg = new GeneratorAdapter(1, Method.getMethod("boolean booleanMethod9(org.tomitribe.snitch.Blue$Arg0,org.tomitribe.snitch.Blue$Arg1,org.tomitribe.snitch.Blue$Arg2,org.tomitribe.snitch.Blue$Arg3,org.tomitribe.snitch.Blue$Arg4,org.tomitribe.snitch.Blue$Arg5,org.tomitribe.snitch.Blue$Arg6,org.tomitribe.snitch.Blue$Arg7,org.tomitribe.snitch.Blue$Arg8)"), null, new Type[]{Type.getObjectType("java/lang/IllegalStateException")}, cw);
            mg.visitCode();
            Label label0 = mg.newLabel();
            Label label1 = mg.newLabel();
            Label label2 = mg.newLabel();
            mg.visitTryCatchBlock(label0, label1, label2, null);
            Label label3 = mg.newLabel();
            mg.visitTryCatchBlock(label2, label3, label2, null);
            mg.invokeStatic(Type.getObjectType("java/lang/System"), Method.getMethod("long nanoTime()"));
            int local0 = mg.newLocal(Type.LONG_TYPE);
            mg.storeLocal(local0);
            mg.mark(label0);
            mg.loadThis();
            mg.loadArg(0);
            mg.loadArg(1);
            mg.loadArg(2);
            mg.loadArg(3);
            mg.loadArg(4);
            mg.loadArg(5);
            mg.loadArg(6);
            mg.loadArg(7);
            mg.loadArg(8);
            mg.invokeVirtual(Type.getObjectType("org/tomitribe/snitch/Blue"), Method.getMethod("boolean track$booleanMethod9(org.tomitribe.snitch.Blue$Arg0,org.tomitribe.snitch.Blue$Arg1,org.tomitribe.snitch.Blue$Arg2,org.tomitribe.snitch.Blue$Arg3,org.tomitribe.snitch.Blue$Arg4,org.tomitribe.snitch.Blue$Arg5,org.tomitribe.snitch.Blue$Arg6,org.tomitribe.snitch.Blue$Arg7,org.tomitribe.snitch.Blue$Arg8)"));
            int local1 = mg.newLocal(Type.INT_TYPE);
            mg.storeLocal(local1);
            mg.mark(label1);
            mg.push("theTag");
            mg.loadLocal(local0);
            mg.invokeStatic(Type.getObjectType("org/tomitribe/snitch/Tracker"), Method.getMethod("void track(java.lang.String,long)"));
            mg.loadLocal(local1);
            mg.returnValue();
            mg.mark(label2);
            mg.visitFrame(Opcodes.F_FULL
                    , 11
                    , new Object[]{"org/tomitribe/snitch/Blue"
                    , "org/tomitribe/snitch/Blue$Arg0"
                    , "org/tomitribe/snitch/Blue$Arg1"
                    , "org/tomitribe/snitch/Blue$Arg2"
                    , "org/tomitribe/snitch/Blue$Arg3"
                    , "org/tomitribe/snitch/Blue$Arg4"
                    , "org/tomitribe/snitch/Blue$Arg5"
                    , "org/tomitribe/snitch/Blue$Arg6"
                    , "org/tomitribe/snitch/Blue$Arg7"
                    , "org/tomitribe/snitch/Blue$Arg8"
                    , Opcodes.LONG}
                    , 1
                    , new Object[]{"java/lang/Throwable"});
            int local2 = mg.newLocal(Type.getObjectType("java/lang/Object"));
            mg.storeLocal(local2);
            mg.mark(label3);
            mg.push("theTag");
            mg.loadLocal(local0);
            mg.invokeStatic(Type.getObjectType("org/tomitribe/snitch/Tracker"), Method.getMethod("void track(java.lang.String,long)"));
            mg.loadLocal(local2);
            mg.throwException();
            mg.endMethod();
            mg.visitEnd();
        }
    }
}
