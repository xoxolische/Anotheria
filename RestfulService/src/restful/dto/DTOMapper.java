package restful.dto;

import model.MagicSquareEntity;

/**
 * Class designed with purpose to convert DTO instances to Entity and vice versa
 * 
 * @author Nikita Pavlov
 *
 */
public class DTOMapper {

	private DTOMapper() {
	}

	/**
	 * Converts MagicSquareCreateDTO to MagicSquareEntity.
	 * 
	 * @param dto
	 *            MagicSquareCreateDTO instance
	 * @return MagicSquareEntity instance
	 */
	public static MagicSquareEntity fromCreateDTOtoEntity(MagicSquareCreateDTO dto) {
		MagicSquareEntity e = new MagicSquareEntity();
		e.setSquareView(dto.getSquareView());
		return e;
	}

	/**
	 * Converts MagicSquareEntity to MagicSquareToUserDTO.
	 * 
	 * @param e
	 *            MagicSquareEntity instance
	 * @return MagicSquareToUserDTO instance
	 */
	public static MagicSquareToUserDTO fromEntityToUserDTO(MagicSquareEntity e) {
		MagicSquareToUserDTO dto = new MagicSquareToUserDTO();
		dto.setCreatedAt(e.getCreatedAt());
		dto.setEditedAt(e.getEditedAt());
		dto.setSquareView(e.getSquareView());
		return dto;
	}

	/**
	 * Converts MagicSquareUpdateDTO to MagicSquareEntity.
	 * 
	 * @param dto
	 *            MagicSquareUpdateDTO instance
	 * @return MagicSquareEntity instance
	 */
	public static MagicSquareEntity fromUpdateDTOtoEntity(MagicSquareUpdateDTO dto) {
		MagicSquareEntity e = new MagicSquareEntity();
		e.setId(dto.getId());
		e.setSquareView(dto.getSquareView());
		return e;
	}

	/**
	 * Converts MagicSquareSearchDTO to MagicSquareEntity.
	 * 
	 * @param dto
	 *            MagicSquareSearchDTO instance
	 * @return MagicSquareEntity instance
	 */
	public static MagicSquareEntity fromSearchDtoToEntity(MagicSquareSearchDTO dto) {
		MagicSquareEntity e = new MagicSquareEntity();
		e.setSquareView(dto.getSquarePattern());
		return e;
	}

}
