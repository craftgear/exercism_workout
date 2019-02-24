let hasFactor = (x, y) => x mod y == 0;

let isLeapYear = year => {
  let yearHasFactor = hasFactor(year);
  switch (yearHasFactor(4)) {
  | true when yearHasFactor(400) => true
  | true when yearHasFactor(100) => false
  | true => true
  | _ => false
  };
};
