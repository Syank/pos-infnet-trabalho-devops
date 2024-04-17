package rgba.SkillShare.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import org.json.JSONObject;

@Converter
public class JSONObjectConverter implements AttributeConverter<JSONObject, String> {

	@Override
	public String convertToDatabaseColumn(JSONObject attribute) {
		return attribute.toString();
	}

	@Override
	public JSONObject convertToEntityAttribute(String dbData) {
		return new JSONObject(dbData);
	}

}
