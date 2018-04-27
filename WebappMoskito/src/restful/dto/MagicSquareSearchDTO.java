package restful.dto;
/**
 * DTO for MagicSquareEnity to accept data for search in DB.
 * <p>
 * Properties must be set:
 * <p>
 * {@link MagicSquareSearchDTO#squarePattern} that is String with numbers separated
 * by whitespace to search in DB according to principle of SQL operator LIKE "%squarePattern%"
 * 
 * @author Nikita Pavlov
 *
 */
public class MagicSquareSearchDTO {

	private String squarePattern;

	public String getSquarePattern() {
		return squarePattern;
	}

	public void setSquarePattern(String squarePattern) {
		this.squarePattern = squarePattern;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((squarePattern == null) ? 0 : squarePattern.hashCode());
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
		MagicSquareSearchDTO other = (MagicSquareSearchDTO) obj;
		if (squarePattern == null) {
			if (other.squarePattern != null)
				return false;
		} else if (!squarePattern.equals(other.squarePattern))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MagicSquareSearchDTO [squarePattern=" + squarePattern + "]";
	}

}
