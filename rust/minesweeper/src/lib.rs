pub fn annotate(minefield: &[&str]) -> Vec<String> {
    (0..minefield.len()).map(|row_index| {
        let row = minefield.get(row_index).unwrap();
        (0..row.len()).map(move |col_index| {
            let cell = &row.as_bytes()[col_index];
            println!("*** {:?}", cell);

            //                     match cell {
            //                         b'*' => '*',
            //                         _ => minefield
            //                             .get(row_index - 1..row_index + 1)
            //                             .map(|row| row.get(col_index - 1..col_index + 1))
            //                             .filter(|cells| cells.as_bytes().filter(|x| x == '*').count()),
            //                     }
        })
    });

    vec![]
}
