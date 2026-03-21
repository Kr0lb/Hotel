package org.example.repository.specification;

import org.example.entity.Hotel;
import org.springframework.data.jpa.domain.Specification;

public class HotelSpecification {

    public static Specification<Hotel> hasName(String name) {
        return (root, query, cb) -> name == null ?
                null : cb.equal(root.get("name"), name);
    }

    public static Specification<Hotel> hasBrand(String brand) {
        return (root, query, cb) -> brand == null ?
                null : cb.like(root.get("brand"), brand.concat("%"));
    }

    public static Specification<Hotel> hasCity(String city) {
        return (root, query, cb) -> city == null ?
                null : cb.equal(root.get("address").get("city"), city);
    }

    public static Specification<Hotel> hasCountry(String country) {
        return (root, query, cb) -> country == null ?
                null : cb.equal(root.get("address").get("country"), country);
    }

    public static Specification<Hotel> hasAmenities(String amenities) {
        return (root, query, cb) -> amenities == null ?
                null : cb.equal(root.join("amenities").get("name"), amenities);
    }

}
