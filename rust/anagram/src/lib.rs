use std::collections::HashSet;
use unicode_segmentation::UnicodeSegmentation;

fn sort_graphemes(word: &String) -> Vec<&str> {
    let mut sorted_word = word.graphemes(true).collect::<Vec<&str>>();
    sorted_word.sort_unstable();
    sorted_word
}

pub fn anagrams_for<'a>(word: &str, possible_anagrams: &[&'a str]) -> HashSet<&'a str> {
    let lower_word = word.to_lowercase();
    let sorted_word = sort_graphemes(&lower_word);

    possible_anagrams
        .iter()
        .copied()
        .filter(|&x| {
            let lower_x = x.to_lowercase();
            if lower_x == lower_word {
                return false;
            }

            let sorted_x = sort_graphemes(&lower_x);

            if sorted_x == sorted_word {
                return true;
            }
            false
        })
        .collect::<HashSet<_>>()
}
