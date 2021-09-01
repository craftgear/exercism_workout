fn is_prime(n: u32, primes: &Vec<u32>) -> bool {
    primes.iter().all(|x| n % x != 0)
}

pub fn nth(n: u32) -> u32 {
    (2..)
        .scan(vec![], |primes, x| {
            if is_prime(x, &primes) {
                primes.push(x);
                return Some(Some(x));
            }
            Some(None)
        })
        .filter_map(|x| x)
        .nth(n as usize)
        .unwrap()
}
