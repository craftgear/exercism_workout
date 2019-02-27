// Generated by BUCKLESCRIPT VERSION 4.0.6, PLEASE EDIT WITH CARE
'use strict';

var $$String = require("bs-platform/lib/js/string.js");
var Caml_string = require("bs-platform/lib/js/caml_string.js");

function lastChar(words) {
  return Caml_string.get(words, words.length - 1 | 0);
}

function isYelling(words) {
  if (words === $$String.uppercase(words)) {
    return (/[a-zA-Z]/).test(words);
  } else {
    return false;
  }
}

function hey(words) {
  if (isYelling(words) && lastChar(words) === /* "?" */63) {
    return "Calm down, I know what I'm doing!";
  } else if (words.trim() === "") {
    return "Fine. Be that way!";
  } else if (isYelling(words)) {
    return "Whoa, chill out!";
  } else {
    var words$1 = words.trim();
    if (lastChar(words$1) === /* "?" */63) {
      return "Sure.";
    } else {
      return "Whatever.";
    }
  }
}

exports.hey = hey;
/* No side effect */