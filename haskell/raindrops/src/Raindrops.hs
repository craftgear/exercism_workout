module Raindrops (convert) where

convert :: Int -> String
convert n = case result of
  "" -> show n
  _  -> result
  where result = concat $ [factor3, factor5, factor7] <*> [n]

factorN :: Int -> String -> Int -> String
factorN factor response n | n `mod` factor == 0 = response
                          | otherwise           = ""

factor3 :: Int -> String
factor3 = factorN 3 "Pling"

factor5 :: Int -> String
factor5 = factorN 5 "Plang"

factor7 :: Int -> String
factor7 = factorN 7 "Plong"
