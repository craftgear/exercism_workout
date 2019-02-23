module Hamming (distance) where

distance :: String -> String -> Maybe Int
distance xs ys | length xs /= length ys = Nothing
               | otherwise = lengthMaybe . filter (== True) $ zipWith (/=) xs ys
  where lengthMaybe = Just . length
