use chrono::{DateTime, Duration, Utc};

// Returns a Utc DateTime one billion seconds after start.
pub fn after(start: DateTime<Utc>) -> DateTime<Utc> {
    //     let ten: i64 = 10;
    start + Duration::seconds(10_i64.pow(9))
}
