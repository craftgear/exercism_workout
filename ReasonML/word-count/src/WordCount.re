let wordCount = phrase =>
  phrase
  |> Js.String.splitByRe([%bs.re "/,|\'\\s|\\s\'|\\s/"])
  |> Array.to_list
  |> List.filter(x => Js.String.trim(x) != "")
  |> List.map(x =>
       Js.String.toLowerCase(
         Js.String.replaceByRe([%bs.re "/[\\.:&^!#@$%]/g"], "", x),
       )
     )
  |> List.fold_left(
       (dict, x) =>
         switch (Js.Dict.get(dict, x)) {
         | Some(a) =>
           Js.Dict.set(dict, x, a + 1);
           dict;
         | None =>
           Js.Dict.set(dict, x, 1);
           dict;
         },
       Js.Dict.empty(),
     );
