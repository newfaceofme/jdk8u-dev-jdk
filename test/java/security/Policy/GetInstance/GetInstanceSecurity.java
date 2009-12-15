/*
 * Copyright 2005 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */

/*
 * @test 1.1, 00/12/07
 * @bug 5100561
 * @summary Can not explicitly create a java.security.Policy object from a file
 * @run main/othervm/policy=GetInstanceSecurity.policy GetInstanceSecurity
 */

import java.security.*;

public class GetInstanceSecurity {

    private static final String JAVA_POLICY = "JavaPolicy";

    public static void main(String[] args) throws Exception {
        try {
            Policy p = Policy.getInstance(JAVA_POLICY, null);
            throw new RuntimeException("did not catch security exception");
        } catch (SecurityException se) {
            // good
        }

        try {
            Policy p = Policy.getInstance(JAVA_POLICY, null, "SUN");
            throw new RuntimeException("did not catch security exception");
        } catch (SecurityException se) {
            // good
        }

        try {
            Policy p = Policy.getInstance(JAVA_POLICY, null,
                                        Security.getProvider("SUN"));
            throw new RuntimeException("did not catch security exception");
        } catch (SecurityException se) {
            // good
        }

        System.out.println("test passed");
    }
}
