package io.javamasters.spring.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {
	
	@NotNull
	@Size(min = 1, message = "Min length of firstName should be 1 character")
	private String firstName;

	@NotNull
	@Size(min = 1, message = "Min length of lastName should be 1 character")
	private String lastName;

	private String password;

	@Email(message = "Email should not be invalid")
	@NotNull
	@Size(min = 1, message = "Min length of email should be 1 character")
	private String email;

	private boolean isUsing2FA;

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	private Integer role;

	public Integer getRole() {
		return role;
	}

	public void setRole(final Integer role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public boolean isUsing2FA() {
		return isUsing2FA;
	}

	public void setUsing2FA(boolean isUsing2FA) {
		this.isUsing2FA = isUsing2FA;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("UserDto [firstName=").append(firstName).append(", lastName=").append(lastName)
				.append(", email=").append(email).append(", isUsing2FA=").append(isUsing2FA).append(", role=")
				.append(role).append("]");
		return builder.toString();
	}

}
