/*
 * IronJacamar, a Java EE Connector Architecture implementation
 * Copyright 2014, Red Hat Inc, and individual contributors
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
package org.ironjacamar.common.metadata.ds;

import org.ironjacamar.common.api.metadata.Defaults;
import org.ironjacamar.common.api.metadata.common.Capacity;
import org.ironjacamar.common.api.metadata.common.Extension;
import org.ironjacamar.common.api.metadata.common.FlushStrategy;
import org.ironjacamar.common.api.metadata.ds.DsXaPool;
import org.ironjacamar.common.api.validator.ValidateException;

import java.util.Iterator;
import java.util.Map;

/**
 * An XA pool implementation
 *
 * @author <a href="jesper.pedersen@ironjacamar.org">Jesper Pedersen</a>
 */
public class DsXaPoolImpl extends org.ironjacamar.common.metadata.common.XaPoolImpl implements DsXaPool
{
   /** The serialVersionUID */
   private static final long serialVersionUID = 1L;

   /** allow-multiple-users */
   protected final Boolean allowMultipleUsers;

   /**
    * connection-listener
    */
   protected final Extension connectionListener;

   /**
    * Create a new XaPoolImpl.
    *
    * @param minPoolSize minPoolSize
    * @param initialPoolSize initialPoolSize
    * @param maxPoolSize maxPoolSize
    * @param prefill prefill
    * @param useStrictMin useStrictMin
    * @param flushStrategy flushStrategy
    * @param isSameRmOverride isSameRmOverride
    * @param interleaving interleaving
    * @param padXid padXid
    * @param wrapXaResource wrapXaResource
    * @param noTxSeparatePool noTxSeparatePool
    * @param allowMultipleUsers allowMultipleUsers
    * @param capacity capacity
    * @param connectionListener connectionListener
    * @param expressions expressions
    * @throws ValidateException ValidateException
    */
   public DsXaPoolImpl(Integer minPoolSize, Integer initialPoolSize, Integer maxPoolSize,
                       Boolean prefill, Boolean useStrictMin,
                       FlushStrategy flushStrategy,
                       Boolean isSameRmOverride, Boolean interleaving, 
                       Boolean padXid, Boolean wrapXaResource,
                       Boolean noTxSeparatePool,
                       Boolean allowMultipleUsers,
                       Capacity capacity, Extension connectionListener,
                       Map<String, String> expressions) throws ValidateException
   {
      super(minPoolSize, initialPoolSize, maxPoolSize, prefill, useStrictMin, flushStrategy, capacity,
            isSameRmOverride, interleaving, padXid, wrapXaResource, noTxSeparatePool, expressions);

      this.allowMultipleUsers = allowMultipleUsers;
      this.connectionListener = connectionListener;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public final Boolean isAllowMultipleUsers()
   {
      return allowMultipleUsers;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public Extension getConnectionListener()
   {
      return connectionListener;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + ((allowMultipleUsers == null) ? 0 : allowMultipleUsers.hashCode());
      result = prime * result + ((connectionListener == null) ? 7 : 7 * connectionListener.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (!(obj instanceof DsXaPoolImpl))
         return false;
      if (!super.equals(obj))
         return false;

      DsXaPoolImpl other = (DsXaPoolImpl) obj;
      if (allowMultipleUsers == null)
      {
         if (other.allowMultipleUsers != null)
            return false;
      }
      else if (!allowMultipleUsers.equals(other.allowMultipleUsers))
         return false;
      if (connectionListener == null)
      {
         if (other.connectionListener != null)
            return false;
      }
      else if (!connectionListener.equals(other.connectionListener))
         return false;
      return true;
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String toString()
   {
      StringBuilder sb = new StringBuilder(1024);

      sb.append("<xa-pool>");

      if (minPoolSize != null && !Defaults.MIN_POOL_SIZE.equals(minPoolSize))
      {
         sb.append("<").append(XML.ELEMENT_MIN_POOL_SIZE).append(">");
         sb.append(minPoolSize);
         sb.append("</").append(XML.ELEMENT_MIN_POOL_SIZE).append(">");
      }

      if (initialPoolSize != null)
      {
         sb.append("<").append(XML.ELEMENT_INITIAL_POOL_SIZE).append(">");
         sb.append(initialPoolSize);
         sb.append("</").append(XML.ELEMENT_INITIAL_POOL_SIZE).append(">");
      }

      if (maxPoolSize != null && !Defaults.MAX_POOL_SIZE.equals(maxPoolSize))
      {
         sb.append("<").append(XML.ELEMENT_MAX_POOL_SIZE).append(">");
         sb.append(maxPoolSize);
         sb.append("</").append(XML.ELEMENT_MAX_POOL_SIZE).append(">");
      }

      if (prefill != null && !Defaults.PREFILL.equals(prefill))
      {
         sb.append("<").append(XML.ELEMENT_PREFILL).append(">");
         sb.append(prefill);
         sb.append("</").append(XML.ELEMENT_PREFILL).append(">");
      }

      if (useStrictMin != null && !Defaults.USE_STRICT_MIN.equals(useStrictMin))
      {
         sb.append("<").append(XML.ELEMENT_USE_STRICT_MIN).append(">");
         sb.append(useStrictMin);
         sb.append("</").append(XML.ELEMENT_USE_STRICT_MIN).append(">");
      }

      if (flushStrategy != null && !Defaults.FLUSH_STRATEGY.equals(flushStrategy))
      {
         sb.append("<").append(XML.ELEMENT_FLUSH_STRATEGY).append(">");
         sb.append(flushStrategy);
         sb.append("</").append(XML.ELEMENT_FLUSH_STRATEGY).append(">");
      }

      if (allowMultipleUsers != null && allowMultipleUsers.booleanValue())
      {
         sb.append("<").append(XML.ELEMENT_ALLOW_MULTIPLE_USERS).append("/>");
      }

      if (capacity != null)
         sb.append(capacity);

      if (connectionListener != null)
      {
         sb.append("<").append(XML.ELEMENT_CONNECTION_LISTENER);
         sb.append(" ").append(XML.ATTRIBUTE_CLASS_NAME).append("=\"");
         sb.append(connectionListener.getClassName()).append("\"");
         sb.append(">");

         if (connectionListener.getConfigPropertiesMap().size() > 0)
         {
            Iterator<Map.Entry<String, String>> it = connectionListener.getConfigPropertiesMap().entrySet().iterator();
            
            while (it.hasNext())
            {
               Map.Entry<String, String> entry = it.next();

               sb.append("<").append(XML.ELEMENT_CONFIG_PROPERTY);
               sb.append(" name=\"").append(entry.getKey()).append("\">");
               sb.append(entry.getValue());
               sb.append("</").append(XML.ELEMENT_CONFIG_PROPERTY).append(">");
            }
         }

         sb.append("</").append(XML.ELEMENT_CONNECTION_LISTENER).append(">");
      }

      if (isSameRmOverride != null)
      {
         sb.append("<").append(XML.ELEMENT_IS_SAME_RM_OVERRIDE).append(">");
         sb.append(isSameRmOverride);
         sb.append("</").append(XML.ELEMENT_IS_SAME_RM_OVERRIDE).append(">");
      }

      if (interleaving != null && Boolean.TRUE.equals(interleaving))
      {
         sb.append("<").append(XML.ELEMENT_INTERLEAVING).append("/>");
      }

      if (noTxSeparatePool != null && Boolean.TRUE.equals(noTxSeparatePool))
      {
         sb.append("<").append(XML.ELEMENT_NO_TX_SEPARATE_POOLS).append("/>");
      }

      if (padXid != null && !Defaults.PAD_XID.equals(padXid))
      {
         sb.append("<").append(XML.ELEMENT_PAD_XID).append(">");
         sb.append(padXid);
         sb.append("</").append(XML.ELEMENT_PAD_XID).append(">");
      }

      if (wrapXaResource != null && !Defaults.WRAP_XA_RESOURCE.equals(wrapXaResource))
      {
         sb.append("<").append(XML.ELEMENT_WRAP_XA_RESOURCE).append(">");
         sb.append(wrapXaResource);
         sb.append("</").append(XML.ELEMENT_WRAP_XA_RESOURCE).append(">");
      }

      sb.append("</xa-pool>");
      
      return sb.toString();
   }
}
