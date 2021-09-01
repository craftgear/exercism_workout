pub fn raindrops(n: u32) -> String {
    match (n % 3 == 0, n % 5 == 0, n % 7 == 0) {
        (true, true, true) => "PlingPlangPlong".to_string(),
        (true, true, false) => "PlingPlang".to_string(),
        (false, true, true) => "PlangPlong".to_string(),
        (true, false, true) => "PlingPlong".to_string(),
        (true, false, false) => "Pling".to_string(),
        (false, true, false) => "Plang".to_string(),
        (false, false, true) => "Plong".to_string(),
        _ => n.to_string(),
    }
}
