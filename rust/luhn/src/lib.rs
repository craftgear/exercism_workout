/// Check a Luhn checksum.
fn calc_number(index: usize, x: u32) -> u32 {
    if index % 2 == 0 {
        x
    } else {
        let doubled = x * 2;
        if doubled > 9 {
            doubled - 9
        } else {
            doubled
        }
    }
}
pub fn is_valid(code: &str) -> bool {
    if code.trim().len() < 2 {
        return false;
    }

    let sum = code
        .chars()
        .into_iter()
        .filter(|x| !x.is_whitespace())
        .rev()
        .enumerate()
        .try_fold(0, |acc, (index, x)| {
            x.to_digit(10)
                .map(|x_digit| acc + calc_number(index, x_digit))
        });

    if let Some(x) = sum {
        x % 10 == 0
    } else {
        false
    }
}
