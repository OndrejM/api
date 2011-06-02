/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright (c) 2011 Oracle and/or its affiliates. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 * 
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 * 
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 * 
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package javax.ws.rs.client;

import java.util.Map;
import java.util.concurrent.Future;

import javax.ws.rs.core.GenericType;

/**
 *
 * @author Marek Potociar
 * @since 2.0
 */
public interface Invocation extends ClientRequest<Invocation> {

    /**
     * Get the mutable property bag.
     *
     * @return the property bag.
     */
    Map<String, Object> getProperties();
    
    /**
     * Determine if a feature is enabled.
     *
     * @param featureName the name of the feature.
     * @return {@code true} if the feature value is present in the property bag
     *     and is an instance of {@link java.lang.Boolean} and that value is {@code true},
     *     otherwise {@code false}.
     */
    boolean isEnabled(final String featureName);
//    {
//        return getPropertyAsFeature(featureName, false);
//    }
//
//    private boolean getPropertyAsFeature(final String name, final boolean defaultValue) {
//        Boolean v = (Boolean) getProperties().get(name);
//        return (v != null) ? v : defaultValue;
//    }

    /**
     * TODO javadoc.
     *
     * @param name property name.
     * @param value property value.
     * @return the updated request.
     */
    Invocation property(String name, Object value);

    /**
     * TODO javadoc.
     *
     * @param name feature name.
     * @return the updated request.
     */
    Invocation enableFeature(String name);

    /**
     * TODO javadoc.
     *
     * @param name feature name.
     * @return the updated request.
     */
    Invocation disableFeature(String name);

    /**
     * Sets properties (replaces everything previously set).
     *
     * @param properties set of properties for the client request. The content of
     *     the map will replace any existing properties set on the client request.
     * @return the updated request
     */
    Invocation properties(Map<String, Object> properties);    
    
    // Invocation methods
    // TODO: document that the request instance needs to be cloned so that the 
    // data used in the invocation processing chain are decoupled from the original
    // request data that were used to initiate the invocation to prevent accidental
    // issues caused by mutable nature of the request
    ClientResponse invoke() throws InvocationException;

    <T> T invoke(Class<T> responseType) throws InvocationException;

    <T> T invoke(GenericType<T> responseType) throws InvocationException;

    Future<ClientResponse> start();

    <T> Future<T> start(Class<T> responseType);

    <T> Future<T> start(GenericType<T> responseType);

    <T> Future<T> start(InvocationCallback<T> callback);    
}