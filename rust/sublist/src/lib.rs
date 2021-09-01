#[derive(Debug, PartialEq)]
pub enum Comparison {
    Equal,
    Sublist,
    Superlist,
    Unequal,
}

pub fn sublist<T: PartialEq>(_first_list: &[T], _second_list: &[T]) -> Comparison {
    match (_first_list, _second_list) {
        ([], []) => Comparison::Equal,
        ([], _y) => Comparison::Sublist,
        (_x, []) => Comparison::Superlist,
        _ => {
            let first_len = _first_list.len();
            let second_len = _second_list.len();

            let (window_size, short, long) = if first_len < second_len {
                (first_len, _first_list, _second_list)
            } else {
                (second_len, _second_list, _first_list)
            };

            let iter = long.windows(window_size);
            iter.fold(Comparison::Unequal, |accum, curr| match (short, curr) {
                (x, y) if x == y && first_len == second_len => Comparison::Equal,
                (x, y) if x == y && first_len < second_len => Comparison::Sublist,
                (x, y) if x == y && first_len > second_len => Comparison::Superlist,
                _ => accum,
            })
        }
    }
}
