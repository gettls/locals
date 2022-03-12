package community.locals.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Embeddable
@Getter
@AllArgsConstructor
public class Location {
	
	private Double longitude;
	private Double latitude;
}
