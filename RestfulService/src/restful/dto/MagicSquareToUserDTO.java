package restful.dto;

import java.sql.Timestamp;

public class MagicSquareToUserDTO {
	private String squareView;
	private Timestamp createdAt;
	private Timestamp editedAt;

	public String getSquareView() {
		return squareView;
	}

	public void setSquareView(String squareView) {
		this.squareView = squareView;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((editedAt == null) ? 0 : editedAt.hashCode());
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
		MagicSquareToUserDTO other = (MagicSquareToUserDTO) obj;
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
		if (squareView == null) {
			if (other.squareView != null)
				return false;
		} else if (!squareView.equals(other.squareView))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MagicSquareToUserDTO [squareView=" + squareView + ", createdAt=" + createdAt + ", editedAt=" + editedAt
				+ "]";
	}

}
