/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;

import org.objectweb.asm.ClassVisitor;

/**
 * @version $Revision$ $Date$
 */
public class TimingEnhancer extends GenericEnhancer {
    public TimingEnhancer(ClassVisitor classVisitor, Clazz clazz) {
        super(classVisitor, new MethodFilter(clazz.getTime()));
    }
}
