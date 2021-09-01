use crossbeam_utils::thread;
use std::collections::HashMap;

fn count_chars(input: &[&str]) -> HashMap<char, usize> {
    input.iter().fold(HashMap::new(), |acc, curr| {
        curr.to_lowercase()
            .chars()
            .filter(|x| x.is_alphabetic())
            .fold(acc, |mut acc2, ch| {
                let counter = acc2.entry(ch).or_insert(0);
                *counter += 1;
                acc2
            })
    })
}

pub fn frequency(input: &[&str], worker_count: usize) -> HashMap<char, usize> {
    thread::scope(move |s| {
        input
            .chunks(input.len() / worker_count + 1)
            .map(|chunk| s.spawn(move |_| count_chars(chunk)))
            .fold(HashMap::new(), |acc, curr| {
                let curr = curr.join().unwrap();
                curr.keys().fold(acc, |mut acc2, key| {
                    let val = curr.get(key).unwrap();
                    let counter = acc2.entry(*key).or_insert(0);
                    *counter += *val;
                    acc2
                })
            })
    })
    .unwrap()
}
