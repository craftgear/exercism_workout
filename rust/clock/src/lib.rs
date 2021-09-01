use std::fmt::{Display, Formatter, Result};

#[derive(Debug, PartialEq)]
pub struct Clock {
    minutes: i32,
}

const HOURS_IN_DAY: i32 = 24;
const MINS_IN_HOUR: i32 = 60;

impl Clock {
    pub fn new(hours: i32, minutes: i32) -> Clock {
        Clock {
            minutes: (hours * MINS_IN_HOUR + minutes).rem_euclid(MINS_IN_HOUR * HOURS_IN_DAY),
        }
    }

    pub fn add_minutes(&mut self, minutes: i32) -> Self {
        Clock::new(0, self.minutes + minutes)
    }
}

// impl ToString for Clock {
//     fn to_string(&self) -> String {
//         let hours = self.minutes.div_euclid(MINS_IN_HOUR);
//         let minutes = self.minutes.rem_euclid(MINS_IN_HOUR);

//         let h = if hours == HOURS_IN_DAY { 0 } else { hours };

//         let m = if minutes == MINS_IN_HOUR { 0 } else { minutes };

//         String::from(format!("{:02}:{:02}", h, m))
//     }
// }

impl Display for Clock {
    fn fmt(&self, f: &mut Formatter<'_>) -> Result {
        let hours = self.minutes.div_euclid(MINS_IN_HOUR);
        let minutes = self.minutes.rem_euclid(MINS_IN_HOUR);

        let h = if hours == HOURS_IN_DAY { 0 } else { hours };

        let m = if minutes == MINS_IN_HOUR { 0 } else { minutes };
        write!(f, "{:02}:{:02}", h, m)
    }
}
