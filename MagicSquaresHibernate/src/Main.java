
import org.hibernate.SessionFactory;

import dao.impl.MagicSquareDaoImpl;
import hibernate.HibernateUtil;
import model.MagicSquareHibernate;

/**
 * Main class for interaction with database.
 * 
 * @author Nikita Pavlov
 *
 */
public class Main {
	public static void main(String[] args) throws Exception {

		MagicSquareDaoImpl dao = new MagicSquareDaoImpl();

		MagicSquareHibernate ms = new MagicSquareHibernate(5);
		ms.setRow(0, new int[] { 8, 8, 8, 8, 8 });
		System.out.println("Before save " + ms.getSquareView());
		dao.create(ms);
		for (MagicSquareHibernate m : dao.getAll()) {
			System.out.println(m.getSquareView());
			dao.delete(m.getId());
		}

		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		sessFact.close();
	}

}