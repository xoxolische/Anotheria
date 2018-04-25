package model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Model class (Entity)
 * 
 * @author Nikita Pavlov
 *
 */
@Entity
@Table(name = "magic_squares")
public class MagicSquareEntity {
	@Id
	@Column(name = "id", insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "created_at", updatable = false)
	@CreationTimestamp
	private Timestamp createdAt;

	@Column(name = "edited_at")
	@UpdateTimestamp
	private Timestamp editedAt;

	@Column(name = "square")
	private String squareView;

	@Column(name = "square_cache", unique = true)
	private int cache;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getEditedAt() {
		return editedAt;
	}

	public void setEditedAt(Timestamp editedAt) {
		this.editedAt = editedAt;
	}

	public String getSquareView() {
		return squareView;
	}

	public void setSquareView(String squareView) {
		this.squareView = squareView;
		this.cache = squareView.hashCode();
	}

	public int getCache() {
		return cache;
	}

	public void setCache(int cache) {
		this.cache = cache;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cache;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((editedAt == null) ? 0 : editedAt.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((squareView == null) ? 0 : squareView.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MagicSquareEntity other = (MagicSquareEntity) obj;
		if (cache != other.cache)
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (editedAt == null) {
			if (other.editedAt != null)
				return false;
		} else if (!editedAt.equals(other.editedAt))
			return false;
		if (id != other.id)
			return false;
		if (squareView == null) {
			if (other.squareView != null)
				return false;
		} else if (!squareView.equals(other.squareView))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MagicSquareHibernate [id=" + id + ", createdAt=" + createdAt + ", editedAt=" + editedAt
				+ ", squareView=" + squareView + ", cache=" + cache + "]";
	}
}
