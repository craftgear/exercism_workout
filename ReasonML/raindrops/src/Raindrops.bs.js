// Generated by BUCKLESCRIPT VERSION 4.0.6, PLEASE EDIT WITH CARE
'use strict';

var List = require("bs-platform/lib/js/list.js");
var Curry = require("bs-platform/lib/js/curry.js");
var Caml_int32 = require("bs-platform/lib/js/caml_int32.js");

function isDivisibleByN(factor, response, x) {
  var match = Caml_int32.mod_(x, factor);
  if (match !== 0) {
    return "";
  } else {
    return response;
  }
}

function three(param) {
  return isDivisibleByN(3, "Pling", param);
}

function five(param) {
  return isDivisibleByN(5, "Plang", param);
}

function seven(param) {
  return isDivisibleByN(7, "Plong", param);
}

function raindrops(x) {
  var drops = List.fold_left((function (accum, curr) {
          return accum + curr;
        }), "", List.map((function (fn) {
              return Curry._1(fn, x);
            }), /* :: */[
            three,
            /* :: */[
              five,
              /* :: */[
                seven,
                /* [] */0
              ]
            ]
          ]));
  if (drops === "") {
    return String(x);
  } else {
    return drops;
  }
}

exports.raindrops = raindrops;
/* No side effect */
