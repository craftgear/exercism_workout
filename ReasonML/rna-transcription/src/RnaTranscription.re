type dna =
  | A
  | C
  | G
  | T;

type rna =
  | A
  | C
  | G
  | U;

let toRna = (xs: list(dna)) =>
  xs
  |> List.map((x: dna) =>
       switch (x) {
       | A => U
       | C => G
       | G => C
       | T => A
       }
     );
