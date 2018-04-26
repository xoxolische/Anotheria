package restful.dto;

import model.MagicSquareEntity;

public class DTOMapper {

	private DTOMapper() {
	}

	public static MagicSquareEntity fromDTO(MagicSquareDTO dto) {
		MagicSquareEntity e = new MagicSquareEntity();
		e.setId(dto.getId());
		e.setSquareView(dto.getSquareView());
		return e;
	}

	public static MagicSquareDTO fromDTO(MagicSquareEntity e) {
		MagicSquareDTO dto = new MagicSquareDTO();
		dto.setId(e.getId());
		dto.setSquareView(e.getSquareView());
		return dto;
	}

}
