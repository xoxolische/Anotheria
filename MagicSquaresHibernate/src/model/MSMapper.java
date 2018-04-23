package model;

import magic.MagicSquare;

public class MSMapper {

	public MagicSquare getMagicSqaure(MagicSquareHibernate msHib) {
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
	
	public MagicSquareHibernate getMagicSqaure(MagicSquare ms) {
		MagicSquareHibernate msHib = new MagicSquareHibernate();
		msHib.setSquareView(ms.squareToDb());
		msHib.setCache(ms.hashCode());
		return msHib;
	}
	
}
