package co.skepper.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;

public enum Genre {

    POETRY("Poetry"),

    ROMANCE("Romance"),

    DRAMA("Drama"),

    PLAY("Play"),

    TERROR("Terror"),

    SCIFI("SciFi"),

    RELIGIOUS("Religious"),

    HISTORY("History"),

    PHILOSOPHY("Philosophy");

    private String category;

    Genre(String category){
        this.category = category;
    }

    @JsonCreator
    public static Genre decode(final String code) {
        return Stream.of(Genre.values()).filter(targetEnum -> targetEnum.category.equalsIgnoreCase(code))
                .findFirst().orElse(null);
    }

    @JsonValue
    public String getCategory() {
        return category;
    }

}
