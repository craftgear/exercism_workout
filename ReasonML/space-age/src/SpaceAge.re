/* I don't get why I need to re-declare planet type here again when I have it in the SpaceAge.rei file. */
type planet =
  | Mercury
  | Venus
  | Earth
  | Mars
  | Jupiter
  | Saturn
  | Neptune
  | Uranus;

let ageOn = (planet, secs) => {
  let earthYearSecs = 31557600;
  let ratio =
    switch (planet) {
    | Mercury => 0.2408467
    | Venus => 0.61519726
    | Mars => 1.8808158
    | Jupiter => 11.862615
    | Saturn => 29.447498
    | Uranus => 84.016846
    | Neptune => 164.79132
    | otherwise => 1.0
    };
  secs /. float_of_int(earthYearSecs) /. ratio;
};
