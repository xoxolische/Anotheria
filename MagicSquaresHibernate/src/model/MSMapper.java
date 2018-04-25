package model;

import magic.MagicSquare;

/**
 * Class mapper for MagicSquare and MagicSquareEntity
 * @author Nikita Pavlov
 *
 */
public class MSMapper {

	/**
	 * Transforms MagicSquareEntity to MagicSquare
	 * @param msHib MagicSquareEntity
	 * @return MagicSquare
	 */
	public MagicSquare getMagicSqaure(MagicSquareEntity msHib) {
		String[] a = msHib.getSquareView().split(" ");
		int size = (int) Math.sqrt(a.length);
		MagicSquare ms = new MagicSquare(size);
		int[] square = new int[a.length];
		for(int i=0; i<a.length; i++) {
			square[i] = Integer.parseInt(a[i]);
		}
		ms.setSquare(square);
		return ms;
	}
	/**
	 * Transforms MagicSquare to MagicSquareEntity
	 * @param ms MagicSquare
	 * @return MagicSquareEntity
	 */
	public MagicSquareEntity getMagicSqaure(MagicSquare ms) {
		MagicSquareEntity msHib = new MagicSquareEntity();
		msHib.setSquareView(ms.squareToDb());
		msHib.setCache(ms.hashCode());
		return msHib;
	}
	
}
