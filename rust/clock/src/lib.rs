#[derive(Debug, PartialEq)]
pub struct Clock {
    hours: i32,
    minutes: i32,
}

impl Clock {
    pub fn new(hours: i32, minutes: i32) -> Clock {
        let hours = if minutes % 60 == 0 {
            hours + (minutes / 60)
        } else if minutes > 0 {
            hours + minutes / 60
        } else {
            hours + (minutes / 60) - 1
        };

        let h = if hours % 24 == 0 {
            0
        } else if hours > 24 {
            hours % 24
        } else if hours < 0 {
            hours % 24 + 24
        } else {
            hours
        };

        let m = if minutes % 60 == 0 {
            0
        } else if minutes > 60 {
            minutes % 60
        } else if minutes < 0 {
            minutes % 60 + 60
        } else {
            minutes
        };

        Clock {
            hours: h,
            minutes: m,
        }
    }

    pub fn add_minutes(&mut self, minutes: i32) -> Self {
        Clock::new(self.hours, self.minutes + minutes)
    }

    pub fn to_string(&self) -> String {
        String::from(format!("{:02}:{:02}", self.hours, self.minutes))
    }
}
