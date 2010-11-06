package com.android.notification.common;

/**
 * Status Enum
 * @author marciocamurati
 *
 */
public enum ParseValidateEnum {
	COMPLETE(1L, "Parse complete successful"),
	ERROR(0L, "Problem in parser");
	
	private final Long id;
	
	private final String description;
	
	ParseValidateEnum(Long id, String description)	{
		this.id  = id;
		this.description = description;
	}

	/**
	 * Get the id
	 * @return <code>Long</code> id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Get the description
	 * @return <code>String</code> description value
	 */
	public String getDescription() {
		return description;
	}
}
