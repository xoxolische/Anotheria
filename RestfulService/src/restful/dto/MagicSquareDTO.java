package restful.dto;

public class MagicSquareDTO {

	private String squareView;
	private long id;

	public String getSquareView() {
		return squareView;
	}

	public void setSquareView(String squareView) {
		this.squareView = squareView;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		MagicSquareDTO other = (MagicSquareDTO) obj;
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
		return "MagicSquareDTO [squareView=" + squareView + ", id=" + id + "]";
	}

}
