let lastChar = words => words.[String.length(words) - 1];

let isYelling = words =>
  words == String.uppercase(words)
  && Js.Re.test(words, [%bs.re "/[a-zA-Z]/"]);
let isQuestion = words => lastChar(words) == '?';

let hey = words =>
  if (isYelling(words) && isQuestion(words)) {
    "Calm down, I know what I'm doing!";
  } else if (Js.String.trim(words) == "") {
    "Fine. Be that way!";
  } else if (isYelling(words)) {
    "Whoa, chill out!";
  } else if (isQuestion(Js.String.trim(words))) {
    "Sure.";
  } else {
    "Whatever.";
  };
