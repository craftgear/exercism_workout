module SumOfMultiples (sumOfMultiples) where

sumOfMultiples :: [Integer] -> Integer -> Integer
sumOfMultiples []      _     = 0
sumOfMultiples factors limit = foldl plusWith 0 [0 .. limit - 1]
 where
  plusWith accum x
    | any (\factor -> factor /= 0 && x `mod` factor == 0) factors = accum + x
    | otherwise = accum
