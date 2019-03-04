let raindrops = (x: int) => {
  let drops =
    List.map(fn => fn(x), [three, five, seven])
    |> List.fold_left((accum, curr) => accum ++ curr, "");
  if (drops == "") {
    string_of_int(x);
  } else {
    drops;
  };
};

let isDivisibleByN = (factor: int, response: string, x: int): string =>
  switch (x mod factor) {
  | 0 => response
  | _ => ""
  };

let three = isDivisibleByN(3, "Pling");
let five = isDivisibleByN(5, "Plang");
let seven = isDivisibleByN(7, "Plong");
