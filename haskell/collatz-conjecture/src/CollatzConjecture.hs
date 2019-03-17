module CollatzConjecture (collatz) where


collatzHelper :: Integer -> Integer -> Maybe Integer
collatzHelper t x | x == 1    = Just t
                  | even x    = collatzHelper (t + 1) (x `div` 2)
                  | otherwise = collatzHelper (t + 1) (x * 3 + 1)

collatz :: Integer -> Maybe Integer
collatz x | x <= 0    = Nothing
          | otherwise = Just (helper x)

helper :: Integer -> Integer
helper initialValue =
  fromIntegral . length $ takeWhile (> 1) $ iterate iterateFn initialValue

iterateFn x | even x    = x `quot` 2
            | otherwise = x * 3 + 1
