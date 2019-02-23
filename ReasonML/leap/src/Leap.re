let hasFactor = (x, y) => x mod y == 0;

let isLeapYear = ( year ) => {
    let yearHasFactor = hasFactor(year);
    yearHasFactor(4) && (!yearHasFactor(100) || yearHasFactor(400));
}

