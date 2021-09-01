pub fn is_leap_year(year: u64) -> bool {
    let result = match (
        year.rem_euclid(4),
        year.rem_euclid(100),
        year.rem_euclid(400),
    ) {
        (0, 0, 0) => true,
        (0, 0, _) => false,
        (0, _, _) => true,
        _ => false,
    };

    result
}
