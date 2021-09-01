pub fn sum_of_multiples(limit: u32, factors: &[u32]) -> u32 {
    (0..limit)
        .filter(|x| factors.into_iter().filter(|&v| v > &0).any(|v| x % v == 0))
        .sum()
}
