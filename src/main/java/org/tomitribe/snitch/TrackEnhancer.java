/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Map;

/**
 * @version $Revision$ $Date$
 */
public class TrackEnhancer extends GenericEnhancer {
    public TrackEnhancer(ClassVisitor classVisitor, Clazz clazz) {
        super(classVisitor, new MethodFilter(clazz.getTrack()), true);
    }
}

