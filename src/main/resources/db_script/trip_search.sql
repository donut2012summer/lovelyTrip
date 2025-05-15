SELECT DISTINCT ON (trip.id)
    trip.id,
    trip.title,
    tourGroup.unit_price,
    image.image_url,
    tripLocation.location_id,
    location.name AS location_name
FROM lovely_trip.trip trip
         INNER JOIN lovely_trip.tour_group tourGroup
                    ON trip.id = tourGroup.trip_id AND tourGroup.status = 1
         LEFT JOIN lovely_trip.trip_location tripLocation
                   ON trip.id = tripLocation.trip_id
         LEFT JOIN lovely_trip.location location
                   ON tripLocation.location_id = location.id
         LEFT JOIN lovely_trip.image image
                   ON trip.id = image.reference_id AND image.reference_table = 1 AND image_zone = 0
WHERE 1 = 1
  AND (:keyword IS NULL OR LOWER(trip.title) LIKE LOWER(CONCAT('%', :keyword, '%')))
  AND (:destinationId IS NULL OR tripLocation.location_id = :destinationId)
  AND (:priceMin IS NULL OR tourGroup.unit_price >= :priceMin)
  AND (:priceMax IS NULL OR tourGroup.unit_price <= :priceMax)
-- duration filter skipped unless needed
ORDER BY trip.id, tourGroup.unit_price ASC
LIMIT :size OFFSET :offset
