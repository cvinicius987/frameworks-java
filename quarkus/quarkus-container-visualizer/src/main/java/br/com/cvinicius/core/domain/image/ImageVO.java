package br.com.cvinicius.core.domain.image;

/**
 * Contrato para exibição das images
 * 
 * @author cvinicius
 * @since 11/09/2020
 * @version 1.0
 */
public final class ImageVO {

	private String id, parentId, name, size;

	public ImageVO(String id, String parentId, String name, String size) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.size = size;
	}

	public String getId() {
		return id;
	}

	public String getParentId() {
		return parentId;
	}

	public String getName() {
		return name;
	}

	public String getSize() {
		return size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ImageVO other = (ImageVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}