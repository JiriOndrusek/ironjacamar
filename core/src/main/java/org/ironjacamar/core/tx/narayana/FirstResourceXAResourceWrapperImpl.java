/*
 * IronJacamar, a Java EE Connector Architecture implementation
 * Copyright 2015, Red Hat Inc, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 1.0 as
 * published by the Free Software Foundation.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Eclipse
 * Public License for more details.
 *
 * You should have received a copy of the Eclipse Public License 
 * along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.ironjacamar.core.tx.narayana;

import org.ironjacamar.core.spi.transaction.FirstResource;

import javax.transaction.xa.XAResource;

/**
 * A first resource XAResourceWrapper.
 * 
 * @author <a href="wprice@redhat.com">Weston Price</a>
 * @author <a href="jesper.pedersen@ironjacamar.org">Jesper Pedersen</a>
 */
public class FirstResourceXAResourceWrapperImpl extends XAResourceWrapperImpl
   implements FirstResource,
              org.jboss.tm.FirstResource
{
   /**
    * Creates a new wrapper instance.
    * @param resource xaresource
    * @param productName product name
    * @param productVersion product version
    */   
   public FirstResourceXAResourceWrapperImpl(XAResource resource,
                                             String productName, String productVersion)
   {
      this(resource, false, null, productName, productVersion, null);
   }

   /**
    * Creates a new wrapper instance.
    * @param resource xaresource
    * @param pad pad
    * @param override override
    * @param productName product name
    * @param productVersion product version
    * @param jndiName jndi name
    */   
   public FirstResourceXAResourceWrapperImpl(XAResource resource, boolean pad, Boolean override, 
                                             String productName, String productVersion,
                                             String jndiName)
   {
      super(resource, pad, override, productName, productVersion, jndiName);
   }

   /**
    * {@inheritDoc}
    */
   public boolean equals(Object object)
   {
      if (object == this)
         return true;

      if (object == null || !(object instanceof FirstResourceXAResourceWrapperImpl))
         return false;  

      FirstResourceXAResourceWrapperImpl other =
         (FirstResourceXAResourceWrapperImpl)object;

      if (!super.equals(other))
         return false;

      return true;
   }

   /**
    * {@inheritDoc}
    */
   public int hashCode()
   {
      return super.hashCode();
   }
}
