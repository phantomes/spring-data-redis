/*
 * Copyright 2011-2014 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.redis.core;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.connection.DataType;

/**
 * Operations over a Redis key. Useful for executing common key-'bound' operations to all implementations.
 * <p>
 * As the rest of the APIs, if the underlying connection is pipelined or queued/in multi mode, all methods will return
 * null.
 * </p>
 * 
 * @author Costin Leau
 * @author Christoph Strobl
 */
public interface BoundKeyOperations<K> {

	/**
	 * Returns the key associated with this entity.
	 * 
	 * @return key associated with the implementing entity
	 */
	K getKey();

	/**
	 * Returns the associated Redis type.
	 * 
	 * @return key type
	 */
	DataType getType();

	/**
	 * Returns the expiration of this key.
	 * 
	 * @return expiration value (in seconds)
	 */
	Long getExpire();

	/**
	 * Sets the key time-to-live/expiration.
	 * 
	 * @param timeout expiration value
	 * @param unit expiration unit
	 * @return true if expiration was set, false otherwise
	 */
	Boolean expire(long timeout, TimeUnit unit);

	/**
	 * Sets the key time-to-live/expiration.
	 * 
	 * @param date expiration date
	 * @return true if expiration was set, false otherwise
	 */
	Boolean expireAt(Date date);

	/**
	 * Removes the expiration (if any) of the key.
	 * 
	 * @return true if expiration was removed, false otherwise
	 */
	Boolean persist();

	/**
	 * Renames the key. <br />
	 * <b>Note:</b> The new name for empty collections will be propagated on add of first element.
	 * 
	 * @param newKey new key
	 */
	void rename(K newKey);
}
