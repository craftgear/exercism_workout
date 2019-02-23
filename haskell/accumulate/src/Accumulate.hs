module Accumulate (accumulate) where

accumulate :: (a -> b) -> [a] -> [b]
accumulate f xs = map f xs
