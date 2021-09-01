// The code below is a stub. Just enough to satisfy the compiler.
// In order to pass the tests you can add-to or change any of this code.

#[derive(Debug)]
pub struct Duration {
    seconds: u64,
}

impl From<u64> for Duration {
    fn from(s: u64) -> Self {
        Duration { seconds: s }
    }
}

pub trait Planet {
    fn years_during(d: &Duration) -> f64;
}

const SECONDS_IN_YEAR: f64 = 31557600.0;

macro_rules! planet {
    ($struct: ident, $one_year_in_earth: expr) => {
        pub struct $struct
        impl Planet for $struct {
            fn years_during(d: &Duration) -> f64 {
                (d.seconds as f64 / SECONDS_IN_YEAR * 100.0).ceil() / 100.0 / $one_year_in_earth
            }
        }
    };
}

planet!(Mercury, 0.2408467);
planet!(Venus, 0.61519726);
planet!(Earth, 1.0);
planet!(Mars, 1.8808158);
planet!(Jupiter, 11.862615);
planet!(Saturn, 29.447498);
planet!(Uranus, 84.016846);
planet!(Neptune, 164.79132);
