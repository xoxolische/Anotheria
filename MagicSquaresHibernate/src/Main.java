
import org.hibernate.SessionFactory;

import dao.impl.MagicSquareDaoImpl;
import hibernate.HibernateUtil;
import magic.MagicSquare;
import magic.finder.MainMagicFinder;
import model.MSMapper;
import model.MagicSquareEntity;

/**
 * Main class for interaction with database.
 * 
 * @author Nikita Pavlov
 *
 */
public class Main {
	public static void main(String[] args) throws Exception {

		MSMapper m = new MSMapper();
		MagicSquareDaoImpl dao = new MagicSquareDaoImpl();
		MainMagicFinder.generateMagicVectors(3, 4);
		for(MagicSquare ms : MainMagicFinder.getResult()) {
			MagicSquareEntity msh = m.getMagicSqaure(ms);
			dao.create(msh);			
		}
		
		for(MagicSquareEntity db : dao.getAll()) {
			m.getMagicSqaure(db).print();
		}

		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		sessFact.close();
	}

}