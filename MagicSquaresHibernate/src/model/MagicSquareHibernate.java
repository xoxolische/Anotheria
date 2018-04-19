package model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import magic.MagicSquare;

/**
 * Model class (Entity)
 * 
 * @author Nikita Pavlov
 *
 */
@Entity
@Table(name = "magic_squares")
public class MagicSquareHibernate extends MagicSquare {
	@Id
	@Column(name = "id", insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "created_at", updatable = false)
	@CreationTimestamp
	private Timestamp createdAt;

	@Column(name = "square")
	private String squareView;

	public MagicSquareHibernate() {
		super();
	}

	public MagicSquareHibernate(int size) {
		super(size);
	}

	public long getId() {
		return id;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	/**
	 * This method must be called before save MagicSquare to DB.
	 * 
	 * @return String and initializes squareView field.
	 */
	public String getSquareView() {
		if (squareView == null) {
			squareView = super.squareToDb();
		}
		return squareView;
	}


	public void setId(long id) {
		this.id = id;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public void setSquareView(String squareView) {
		this.squareView = squareView;
	}

	@Override
	public String toString() {
		return "MagicSquareHibernate [id=" + id + ", createdAt=" + createdAt + ", squareView=" + squareView + "]";
	}

}
