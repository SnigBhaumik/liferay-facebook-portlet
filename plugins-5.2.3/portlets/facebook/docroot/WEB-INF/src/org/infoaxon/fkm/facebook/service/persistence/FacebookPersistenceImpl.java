/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.infoaxon.fkm.facebook.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.infoaxon.fkm.facebook.NoSuchFacebookException;
import org.infoaxon.fkm.facebook.model.Facebook;
import org.infoaxon.fkm.facebook.model.impl.FacebookImpl;
import org.infoaxon.fkm.facebook.model.impl.FacebookModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="FacebookPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author 
		apoorva.prakash
	
 *
 */
public class FacebookPersistenceImpl extends BasePersistenceImpl
	implements FacebookPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = FacebookImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_ACCESSTOKEN = new FinderPath(FacebookModelImpl.ENTITY_CACHE_ENABLED,
			FacebookModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByaccesstoken", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_ACCESSTOKEN = new FinderPath(FacebookModelImpl.ENTITY_CACHE_ENABLED,
			FacebookModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByaccesstoken",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCESSTOKEN = new FinderPath(FacebookModelImpl.ENTITY_CACHE_ENABLED,
			FacebookModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByaccesstoken", new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(FacebookModelImpl.ENTITY_CACHE_ENABLED,
			FacebookModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FacebookModelImpl.ENTITY_CACHE_ENABLED,
			FacebookModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(Facebook facebook) {
		EntityCacheUtil.putResult(FacebookModelImpl.ENTITY_CACHE_ENABLED,
			FacebookImpl.class, facebook.getPrimaryKey(), facebook);
	}

	public void cacheResult(List<Facebook> facebooks) {
		for (Facebook facebook : facebooks) {
			if (EntityCacheUtil.getResult(
						FacebookModelImpl.ENTITY_CACHE_ENABLED,
						FacebookImpl.class, facebook.getPrimaryKey(), this) == null) {
				cacheResult(facebook);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(FacebookImpl.class.getName());
		EntityCacheUtil.clearCache(FacebookImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public Facebook create(long userid) {
		Facebook facebook = new FacebookImpl();

		facebook.setNew(true);
		facebook.setPrimaryKey(userid);

		return facebook;
	}

	public Facebook remove(long userid)
		throws NoSuchFacebookException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Facebook facebook = (Facebook)session.get(FacebookImpl.class,
					new Long(userid));

			if (facebook == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No Facebook exists with the primary key " +
						userid);
				}

				throw new NoSuchFacebookException(
					"No Facebook exists with the primary key " + userid);
			}

			return remove(facebook);
		}
		catch (NoSuchFacebookException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public Facebook remove(Facebook facebook) throws SystemException {
		for (ModelListener<Facebook> listener : listeners) {
			listener.onBeforeRemove(facebook);
		}

		facebook = removeImpl(facebook);

		for (ModelListener<Facebook> listener : listeners) {
			listener.onAfterRemove(facebook);
		}

		return facebook;
	}

	protected Facebook removeImpl(Facebook facebook) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (facebook.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(FacebookImpl.class,
						facebook.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(facebook);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(FacebookModelImpl.ENTITY_CACHE_ENABLED,
			FacebookImpl.class, facebook.getPrimaryKey());

		return facebook;
	}

	public Facebook update(Facebook facebook) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(Facebook facebook) method. Use update(Facebook facebook, boolean merge) instead.");
		}

		return update(facebook, false);
	}

	public Facebook update(Facebook facebook, boolean merge)
		throws SystemException {
		boolean isNew = facebook.isNew();

		for (ModelListener<Facebook> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(facebook);
			}
			else {
				listener.onBeforeUpdate(facebook);
			}
		}

		facebook = updateImpl(facebook, merge);

		for (ModelListener<Facebook> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(facebook);
			}
			else {
				listener.onAfterUpdate(facebook);
			}
		}

		return facebook;
	}

	public Facebook updateImpl(
		org.infoaxon.fkm.facebook.model.Facebook facebook, boolean merge)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, facebook, merge);

			facebook.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(FacebookModelImpl.ENTITY_CACHE_ENABLED,
			FacebookImpl.class, facebook.getPrimaryKey(), facebook);

		return facebook;
	}

	public Facebook findByPrimaryKey(long userid)
		throws NoSuchFacebookException, SystemException {
		Facebook facebook = fetchByPrimaryKey(userid);

		if (facebook == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No Facebook exists with the primary key " + userid);
			}

			throw new NoSuchFacebookException(
				"No Facebook exists with the primary key " + userid);
		}

		return facebook;
	}

	public Facebook fetchByPrimaryKey(long userid) throws SystemException {
		Facebook facebook = (Facebook)EntityCacheUtil.getResult(FacebookModelImpl.ENTITY_CACHE_ENABLED,
				FacebookImpl.class, userid, this);

		if (facebook == null) {
			Session session = null;

			try {
				session = openSession();

				facebook = (Facebook)session.get(FacebookImpl.class,
						new Long(userid));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (facebook != null) {
					cacheResult(facebook);
				}

				closeSession(session);
			}
		}

		return facebook;
	}

	public List<Facebook> findByaccesstoken(String accesstoken)
		throws SystemException {
		Object[] finderArgs = new Object[] { accesstoken };

		List<Facebook> list = (List<Facebook>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ACCESSTOKEN,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM org.infoaxon.fkm.facebook.model.Facebook WHERE ");

				if (accesstoken == null) {
					query.append("accesstoken IS NULL");
				}
				else {
					query.append("accesstoken = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (accesstoken != null) {
					qPos.add(accesstoken);
				}

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Facebook>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ACCESSTOKEN,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<Facebook> findByaccesstoken(String accesstoken, int start,
		int end) throws SystemException {
		return findByaccesstoken(accesstoken, start, end, null);
	}

	public List<Facebook> findByaccesstoken(String accesstoken, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				accesstoken,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<Facebook> list = (List<Facebook>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_ACCESSTOKEN,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append(
					"FROM org.infoaxon.fkm.facebook.model.Facebook WHERE ");

				if (accesstoken == null) {
					query.append("accesstoken IS NULL");
				}
				else {
					query.append("accesstoken = ?");
				}

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (accesstoken != null) {
					qPos.add(accesstoken);
				}

				list = (List<Facebook>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Facebook>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_ACCESSTOKEN,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public Facebook findByaccesstoken_First(String accesstoken,
		OrderByComparator obc) throws NoSuchFacebookException, SystemException {
		List<Facebook> list = findByaccesstoken(accesstoken, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Facebook exists with the key {");

			msg.append("accesstoken=" + accesstoken);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFacebookException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Facebook findByaccesstoken_Last(String accesstoken,
		OrderByComparator obc) throws NoSuchFacebookException, SystemException {
		int count = countByaccesstoken(accesstoken);

		List<Facebook> list = findByaccesstoken(accesstoken, count - 1, count,
				obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Facebook exists with the key {");

			msg.append("accesstoken=" + accesstoken);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFacebookException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Facebook[] findByaccesstoken_PrevAndNext(long userid,
		String accesstoken, OrderByComparator obc)
		throws NoSuchFacebookException, SystemException {
		Facebook facebook = findByPrimaryKey(userid);

		int count = countByaccesstoken(accesstoken);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM org.infoaxon.fkm.facebook.model.Facebook WHERE ");

			if (accesstoken == null) {
				query.append("accesstoken IS NULL");
			}
			else {
				query.append("accesstoken = ?");
			}

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			if (accesstoken != null) {
				qPos.add(accesstoken);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, facebook);

			Facebook[] array = new FacebookImpl[3];

			array[0] = (Facebook)objArray[0];
			array[1] = (Facebook)objArray[1];
			array[2] = (Facebook)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			dynamicQuery.compile(session);

			return dynamicQuery.list();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			dynamicQuery.setLimit(start, end);

			dynamicQuery.compile(session);

			return dynamicQuery.list();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Facebook> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<Facebook> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<Facebook> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<Facebook> list = (List<Facebook>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM org.infoaxon.fkm.facebook.model.Facebook ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<Facebook>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Facebook>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Facebook>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByaccesstoken(String accesstoken)
		throws SystemException {
		for (Facebook facebook : findByaccesstoken(accesstoken)) {
			remove(facebook);
		}
	}

	public void removeAll() throws SystemException {
		for (Facebook facebook : findAll()) {
			remove(facebook);
		}
	}

	public int countByaccesstoken(String accesstoken) throws SystemException {
		Object[] finderArgs = new Object[] { accesstoken };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACCESSTOKEN,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append(
					"FROM org.infoaxon.fkm.facebook.model.Facebook WHERE ");

				if (accesstoken == null) {
					query.append("accesstoken IS NULL");
				}
				else {
					query.append("accesstoken = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				if (accesstoken != null) {
					qPos.add(accesstoken);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACCESSTOKEN,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(
						"SELECT COUNT(*) FROM org.infoaxon.fkm.facebook.model.Facebook");

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.infoaxon.fkm.facebook.model.Facebook")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Facebook>> listenersList = new ArrayList<ModelListener<Facebook>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Facebook>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "org.infoaxon.fkm.facebook.service.persistence.FacebookPersistence.impl")
	protected org.infoaxon.fkm.facebook.service.persistence.FacebookPersistence facebookPersistence;
	private static Log _log = LogFactoryUtil.getLog(FacebookPersistenceImpl.class);
}