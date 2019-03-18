module CollatzConjecture (collatz) where

collatz :: Integer -> Maybe Integer
collatz x | x <= 0    = Nothing
          | otherwise = Just (helper x)

helper :: Integer -> Integer
helper initialValue =
  fromIntegral . length $ takeWhile (> 1) $ iterate iterateFn initialValue

iterateFn x | even x    = x `quot` 2
            | otherwise = x * 3 + 1
