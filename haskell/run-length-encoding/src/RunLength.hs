module RunLength (decode, encode) where

import           Data.Char
import           Debug.Trace

strToInt :: String -> Int
strToInt x = read x :: Int

numbers :: String -> [Int]
numbers "" = [0]
numbers xs = case num of
  "" -> 1 : numbers (tail xs)
  _  -> strToInt num : (numbers $ tail rest)
 where
  num  = takeWhile isDigit xs
  rest = dropWhile isDigit xs

isAlphaOrSpace :: Char -> Bool
isAlphaOrSpace x = isAlpha x || isSpace x

chars :: String -> String
chars = filter isAlphaOrSpace

decode :: String -> String
decode xs = concat $ zipWith (\x y -> replicate x y) (numbers xs) (chars xs)


takeRepeatedChars :: String -> [String]
takeRepeatedChars ""         = [""]
takeRepeatedChars all@(x:xs) = repeated : takeRepeatedChars rest
 where
  repeated = takeWhile (== x) all
  rest     = dropWhile (== x) all

encode :: String -> String
encode ""   = ""
encode text = concat $ map
  (\xs -> (if length xs == 1 then "" else show $ length xs) ++ [(head xs)])
  (filter (\x -> length x > 0) $ takeRepeatedChars text)
