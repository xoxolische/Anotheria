package restful.dto;

/**
 * DTO for MagicSquareEnity to create new entry in DB.
 * <p>
 * Properties must be set:
 * <p>
 * {@link MagicSquareCreateDTO#squareView} that is String with numbers separated
 * by whitespace.
 * 
 * @author Nikita Pavlov
 *
 */
public class MagicSquareCreateDTO {

	private String squareView;

	public String getSquareView() {
		return squareView;
	}

	public void setSquareView(String squareView) {
		this.squareView = squareView;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		MagicSquareCreateDTO other = (MagicSquareCreateDTO) obj;
		if (squareView == null) {
			if (other.squareView != null)
				return false;
		} else if (!squareView.equals(other.squareView))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MagicSquareCreateDTO [squareView=" + squareView + "]";
	}

}
