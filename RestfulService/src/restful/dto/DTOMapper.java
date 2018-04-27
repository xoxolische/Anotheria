package restful.dto;

import model.MagicSquareEntity;

public class DTOMapper {

	private DTOMapper() {
	}

	public static MagicSquareEntity fromCreateDTOtoEntity(MagicSquareCreateDTO dto) {
		MagicSquareEntity e = new MagicSquareEntity();
		e.setId(dto.getId());
		e.setSquareView(dto.getSquareView());
		return e;
	}

	public static MagicSquareToUserDTO fromEntityToUserDTO(MagicSquareEntity e) {
		MagicSquareToUserDTO dto = new MagicSquareToUserDTO();
		dto.setCreatedAt(e.getCreatedAt());
		dto.setEditedAt(e.getEditedAt());
		dto.setSquareView(e.getSquareView());
		return dto;
	}

	public static MagicSquareEntity fromUpdateDTOtoEntity(MagicSquareUpdateDTO dto) {
		MagicSquareEntity e = new MagicSquareEntity();
		e.setId(dto.getId());
		e.setSquareView(dto.getSquareView());
		return e;
	}

	public static MagicSquareEntity fromSearchDtoToEntity(MagicSquareSearchDTO dto) {
		MagicSquareEntity e = new MagicSquareEntity();
		e.setSquareView(dto.getSquarePattern());
		return e;
	}

}
