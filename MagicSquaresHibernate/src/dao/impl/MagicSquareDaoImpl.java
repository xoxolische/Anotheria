package dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hibernate.HibernateUtil;
import model.MagicSquareEntity;

/**
 * Dao implementation for MagicSquareHibernate entity
 * 
 * @author Nikita Pavlov
 *
 */
public class MagicSquareDaoImpl extends DaoImpl<MagicSquareEntity> {
	private static final Logger LOGGER = LoggerFactory.getLogger(MagicSquareDaoImpl.class);

	public MagicSquareDaoImpl() {
		super(MagicSquareEntity.class);
	}

	/**
	 * Search by pattern implementation
	 * 
	 * @param pattern
	 *            MagicSquareHibernate entity with squareView String set with search
	 *            pattern
	 * @return
	 */
	public List<MagicSquareEntity> search(MagicSquareEntity pattern) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<MagicSquareEntity> query = builder.createQuery(MagicSquareEntity.class);
			Root<MagicSquareEntity> root = query.from(MagicSquareEntity.class);
			//System.out.println(pattern.getSquareView());
			query.select(root).where(builder.like(root.get("squareView"), "%" + pattern.getSquareView() + "%"));
			Query<MagicSquareEntity> q = session.createQuery(query);
			List<MagicSquareEntity> l = q.getResultList();
			session.getTransaction().commit();
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			LOGGER.warn(e.getMessage());
			return null;
		}
	}

}
