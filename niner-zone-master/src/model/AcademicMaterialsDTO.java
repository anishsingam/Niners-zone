package model;

public class AcademicMaterialsDTO {
	
	private int material_id;
	private int academic_board_id;
	private String title;
	private String description;
	private String link;
	private String type;
	private String file_key;
	
	
	public AcademicMaterialsDTO(int material_id, int academic_board_id,
			String title, String description, String link, String type) {
		super();
		this.material_id = material_id;
		this.academic_board_id = academic_board_id;
		this.title = title;
		this.description = description;
		this.link = link;
		this.type = type;
	}
	public AcademicMaterialsDTO(int material_id, int academic_board_id,
			String title, String description, String link, String type,String file_key) {
		super();
		this.material_id = material_id;
		this.academic_board_id = academic_board_id;
		this.title = title;
		this.description = description;
		this.link = link;
		this.type = type;
		this.file_key = file_key;
	}
	public int getMaterial_id() {
		return material_id;
	}
	public void setMaterial_id(int material_id) {
		this.material_id = material_id;
	}
	public int getAcademic_board_id() {
		return academic_board_id;
	}
	public void setAcademic_board_id(int academic_board_id) {
		this.academic_board_id = academic_board_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFile_key() {
		return file_key;
	}
	public void setFile_key(String file_key) {
		this.file_key = file_key;
	}
	@Override
	public String toString() {
		return "AcademicMaterialsDTO [material_id=" + material_id
				+ ", academic_board_id=" + academic_board_id + ", title="
				+ title + ", description=" + description + ", link=" + link
				+ ", type=" + type + ", file_key=" + file_key + "]";
	}
	
	
}
