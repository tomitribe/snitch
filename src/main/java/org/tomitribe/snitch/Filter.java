/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;

/**
* @version $Revision$ $Date$
*/
public interface Filter {
    public String accept(Method method);
    public void end();
}
