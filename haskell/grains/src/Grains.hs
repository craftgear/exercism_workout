module Grains (square,  total) where

square :: Integer -> Maybe Integer
square n | n <= 0    = Nothing
         | n > 64    = Nothing
         | otherwise = Just (2 ^ (n - 1))

total :: Integer
total = case run of
  Just a  -> a
  Nothing -> 0
  where run = fmap sum . sequence $ map square [1 .. 64]


